package com.DT170G.G3.android_app_3;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.TooltipCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.DT170G.G3.android_app_3.employees.Employee;
import com.DT170G.G3.android_app_3.employees.EmployeesRepository;
import com.DT170G.G3.android_app_3.shifts.Shift;
import com.DT170G.G3.android_app_3.shifts.ShiftSwap;
import com.DT170G.G3.android_app_3.shifts.ShiftUpdate;
import com.DT170G.G3.android_app_3.shifts.ShiftsRepository;

import org.jspecify.annotations.NonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List; import java.util.Locale;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EmployeesRepository employeesRepo = new EmployeesRepository();
    ShiftsRepository shiftsRepo = new ShiftsRepository();

    private List<String> allEmployees = new ArrayList<>();
    private LocalDate monday;
    private Button selectedButton;
    private LocalDate selectedDate;
    private String currentUserName;
    private Map<LocalDate, List<String>> dayWork = new HashMap<>();
    private Map<LocalDate, List<String>> nightWork = new HashMap<>();

    private String androidId;

    private @NonNull Insets systemBars;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);

        LocalDate today = LocalDate.now();
        monday = today.with(DayOfWeek.MONDAY);

        selectedDate = monday;
        changeWeek(monday);
        asyncLoadEmployees();


        ImageButton prevWeekButton = findViewById(R.id.prevWeekButton);
        ImageButton nextWeekButton = findViewById(R.id.nextWeekButton);
        prevWeekButton.setOnClickListener(v-> {
            if(monday.isAfter(today)) {
                monday = monday.minusWeeks(1);
                changeWeek(monday);
            }
        });

        nextWeekButton.setOnClickListener(v-> {
            monday = monday.plusWeeks(1);
            changeWeek(monday);
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


        //--------GET---------------------
        //asyncLoadEmployees();
        //asyncLoadShifts();
        String testDate = "2026-02-28";
        //asyncLoadShiftsByDate(testDate);

        //--------POST---------------------
        //exampleCreateShiftSwap();

        //--------PUT-(EDIT)---------------
        //exampleAcceptShiftChangeStatus();
    }


    private void addHeader(LinearLayout container, String text, boolean dayOrNot) {
        LinearLayout header = new LinearLayout(this);
        header.setOrientation(LinearLayout.HORIZONTAL);
        header.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        TextView title= new TextView(this);
        title.setText(text);
        title.setTextSize(25f);

        ImageButton editButton = new ImageButton(this);
        editButton.setImageResource(R.drawable.outline_edit_24);
        editButton.setBackgroundColor(getResources().getColor(R.color.TextContainerColor));
        TooltipCompat.setTooltipText(editButton, "Redigera schemat");

        LinearLayout.LayoutParams titleP = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        title.setLayoutParams(titleP);

        editButton.setOnClickListener(v -> {
            sendChangeRequest(dayOrNot);
        });

        header.addView(title);
        header.addView(editButton);
        container.addView(header);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void changeWeek(LocalDate monday) {
        selectedDate = monday;
        dayWork.clear();
        nightWork.clear();

        for(int i = 0; i < 6; i++) {
            LocalDate date = monday.plusDays(i);
            asyncLoadShiftsByDate(date.toString());
        }
        TextView monthTextView = findViewById(R.id.monthTextView);
        TextView weekTextView = findViewById(R.id.weekTextView);
        monthTextView.setText(monday.getMonth().getDisplayName(TextStyle.FULL, new Locale("sv", "SE")));
        weekTextView.setText("Vecka " + monday.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
        findDateToButtons(monday);
        clickedDay(monday);

        if(selectedButton == null) {
            Button mondayButton = findViewById(R.id.mondayButton);
            mondayButton.setBackgroundTintList(getColorStateList(R.color.markedButtonColor));
            selectedButton = mondayButton;
            selectedDate = monday;
        }
        updateSchedule();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void findDateToButtons(LocalDate monday) {
        Button mondayButton = findViewById(R.id.mondayButton);
        Button tuesdayButton = findViewById(R.id.tuesdayButton);
        Button wednesdayButton = findViewById(R.id.wednesdayButton);
        Button thursdayButton = findViewById(R.id.thursdayButton);
        Button fridayButton = findViewById(R.id.fridayButton);
        Button saturdayButton = findViewById(R.id.saturdayButton);
        mondayButton.setText("M\n" + monday.getDayOfMonth());
        tuesdayButton.setText("T\n" + monday.plusDays(1).getDayOfMonth());
        wednesdayButton.setText("O\n" + monday.plusDays(2).getDayOfMonth());
        thursdayButton.setText("T\n" + monday.plusDays(3).getDayOfMonth());
        fridayButton.setText("F\n" + monday.plusDays(4).getDayOfMonth());
        saturdayButton.setText("L\n" + monday.plusDays(5).getDayOfMonth());
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void clickedDay(LocalDate monday) {
        Button mondayButton = findViewById(R.id.mondayButton);
        Button tuesdayButton = findViewById(R.id.tuesdayButton);
        Button wednesdayButton = findViewById(R.id.wednesdayButton);
        Button thursdayButton = findViewById(R.id.thursdayButton);
        Button fridayButton = findViewById(R.id.fridayButton);
        Button saturdayButton = findViewById(R.id.saturdayButton);
        mondayButton.setOnClickListener(v-> styleOnClickedDay(monday, mondayButton));
        tuesdayButton.setOnClickListener(v-> styleOnClickedDay(monday.plusDays(1), tuesdayButton));
        wednesdayButton.setOnClickListener(v-> styleOnClickedDay(monday.plusDays(2), wednesdayButton));
        thursdayButton.setOnClickListener(v-> styleOnClickedDay(monday.plusDays(3), thursdayButton));
        fridayButton.setOnClickListener(v-> styleOnClickedDay(monday.plusDays(4), fridayButton));
        saturdayButton.setOnClickListener(v-> styleOnClickedDay(monday.plusDays(5), saturdayButton));
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void styleOnClickedDay(LocalDate date, Button clickedButton) {
        selectedDate = date;
        if (selectedButton != null) {
            selectedButton.setBackgroundTintList(getResources().getColorStateList(R.color.dayButtonColor, getTheme()));
        }
        clickedButton.setBackgroundTintList(getResources().getColorStateList(R.color.markedButtonColor, getTheme()));
        selectedButton = clickedButton;
        updateSchedule();
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void updateSchedule() {
        LinearLayout dayContainer = findViewById(R.id.dayScheduleContainer);
        LinearLayout nightContainer = findViewById(R.id.nightScheduleContainer);

        if(selectedDate == null) {
            selectedDate = LocalDate.now();
        }

        dayContainer.removeAllViews();
        nightContainer.removeAllViews();

        List<String> dayList = dayWork.getOrDefault(selectedDate, new ArrayList<>());
        List<String> nightList = nightWork.getOrDefault(selectedDate, new ArrayList<>());

        addHeader(dayContainer, "Dag kl. 10-15", true);
        addPersonToSchedule(dayContainer, dayList);

        addHeader(nightContainer, "Kväll kl. 16-23", false);
        addPersonToSchedule(nightContainer, nightList);
    }


    private void addPersonToSchedule(LinearLayout container, List<String> personList) {
        for(String person : personList) {
            TextView personTextView = new TextView(this);
            personTextView.setText(person);
            personTextView.setPadding(30,25,30,25);
            personTextView.setTextSize(22);
            personTextView.setBackgroundResource(R.drawable.person_container);

            personTextView.setTag(person);

            personTextView.setOnClickListener(v -> {
                String personName = (String) v.getTag();
                selectNewPerson(personName);
            });

            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            p.setMargins(0,16,0,16);
            personTextView.setLayoutParams(p);
            container.addView(personTextView);
        }
    }

    private String getCurrentUserName(List<Shift> shift) {
        for(Shift s : shift) {
            for(Employee e: s.employeeList) {
                if(e.androidId.equals(androidId)){
                    return e.name;
                }
            }
        }
        return null;
    }

    private void sendChangeRequest(boolean dayOrNot) {
        if(currentUserName == null) {
            Toast.makeText(this, "Välj en dag du ska jobba", Toast.LENGTH_SHORT).show();
            return;
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Ansök om att byta pass för " + currentUserName);
        builder.setPositiveButton("OK", (dialog, which) -> {
            selectNewPerson(currentUserName);
        });
        builder.setNegativeButton("Avbryt", null);
        builder.show();
    }

    private void selectNewPerson(String personName) {
        List<String> listWithAllOtherPersons = new ArrayList<>();

        for(String employee : allEmployees) {
            if(!employee.equals(personName)) {
                listWithAllOtherPersons.add(employee);
            }
        }

        String[] personArray = listWithAllOtherPersons.toArray(new String[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Byt pass för " + personName);

        builder.setItems(personArray, (dialog, which) -> {
            String selectedPerson = personArray[which];
            swapPerson(personName, selectedPerson);
        });

        builder.setNegativeButton("Avbryt", null);
        builder.show();
    }

    //till senare
    private void swapPerson(String personName, String changeToPerson) {
        Log.d("REQUESR", "Swap request" + personName + " -> " + changeToPerson);
        Toast.makeText(this, "Förfrågan skickad", Toast.LENGTH_SHORT).show();

    }

    // Send the correlating object or data to GET/POST/PUT
    private void asyncLoadEmployees() {

        employeesRepo.getEmployees(new EmployeesRepository.GetCallback() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSuccess(List<Employee> employees) {
                populateEmployeesUI(employees);
            }

            @Override
            public void onError(String message) {
                Log.e("DRINKS", "Fel: " + message);
            }
        });
    }
    private void populateEmployeesUI(List<Employee> employees) {
        allEmployees.clear();

        for(Employee e : employees) {
            allEmployees.add(e.name);
        }

        Log.d("EMPLOYEES", "" + employees.size());
    }


    private void asyncLoadShifts() {
        shiftsRepo.getShifts(new ShiftsRepository.GetCallback() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSuccess(List<Shift> shifts) {
                populateShiftsUI(shifts);
            }

            @Override
            public void onError(String message) {
                Log.e("DRINKS", "Fel: " + message);
            }
        });
    }

    private void asyncLoadShiftsByDate(String date) {
        shiftsRepo.getShiftsByDate(date, new ShiftsRepository.GetCallback() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onSuccess(List<Shift> shifts) {
                populateShiftsUI(shifts);
            }

            @Override
            public void onError(String message) {
                Log.e("DRINKS", "Fel: " + message);
            }
        });
    }

    private void asyncPostShiftSwap(ShiftSwap shiftSwap) {
        //asynchronous post the order to the database
        shiftsRepo.postShiftSwap(shiftSwap, new ShiftsRepository.PostCallback() {
            @Override
            public void onSuccess(ShiftSwap shiftSwap) {
                Log.d("SHIFT CHANGE", "SUCCESSFULLY POSTED REQUEST");
            }
            @Override
            public void onError(String message) {
                Log.e("SHIFT CHANGE", "FAILED TO POST REQUEST: " + message);
            }
        });
    }

    public void asyncPutShiftUpdate(int shiftId, ShiftUpdate update) {
        shiftsRepo.putShiftUpdate(shiftId, update, new ShiftsRepository.PutCallback() {
            @Override
            public void onSuccess() {
                Log.d("SHIFT UPDATE", "SUCCESSFULLY UPDATED REQUEST");
            }
            @Override
            public void onError(String message) {
                Log.e("SHIFT UPDATE", "FAILED TO UPDATE REQUEST: " + message);
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void populateShiftsUI(List<Shift> shifts) {

        Log.d("SHIFTS", "" + shifts.size());

        for (Shift s : shifts) {
            LocalDate date = LocalDate.parse(s.date);
            List<String> names = new ArrayList<>();

            for (Employee e : s.employeeList) {
                names.add(e.name);
                if(e.androidId.equals(androidId)) {
                    currentUserName = e.name;
                }

            }
            if(s.shiftType.name.equalsIgnoreCase("Lunch")) {
                    dayWork.put(date,names);
            } else if (s.shiftType.name.equalsIgnoreCase("Middag")) {
                    nightWork.put(date,names);
            }

        }
        updateSchedule();
    }

    // How to create a shift swap query
    public void exampleCreateShiftSwap() {
        ShiftSwap sc = new ShiftSwap();
        String senderId = "cd20486bd301b603";
        String  recieverId = "cd20486bd301b606";
        int shiftId = 2;

        sc.senderId = senderId;
        sc.receiverId = recieverId;
        sc.shiftId = shiftId;

        asyncPostShiftSwap(sc);
    }

    // Updates a pending Shift change with approved or disapproved status
    public void exampleAcceptShiftChangeStatus() {
        ShiftUpdate update = new ShiftUpdate();
        update.status = "approved";
        int shiftId = 2;
        asyncPutShiftUpdate(shiftId, update);
    }

}