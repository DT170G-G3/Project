package com.DT170G.G3.android_app_3;

import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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

    private LocalDate monday;
    private Button selectedButton;
    private LocalDate selectedDate;
    //TODO: bara för test, byt mot databas sen private
    private Map<LocalDate, List<String>> dayWork = new HashMap<>();
    private Map<LocalDate, List<String>> nightWork = new HashMap<>();

    private List<String> allPersons = List.of("Sigrid", "Frank", "Melker", "Andreas", "Jacob", "Christine", "Doris");

    private String correctTestID = "cd20486bd301b60d";
    private String wrongTestID = "cd20486bd301b602";

    private String name;

    private String androidId;

    private Map<String, String> idToName = Map.of(correctTestID, "Sigrid");


    private @NonNull Insets systemBars;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
        Log.d("ANDROID_ID", androidId);


        if(androidId.equals(correctTestID)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Välkommen!");
            builder.setMessage("Ange ditt namn: ");

            EditText input = new EditText(this);
            input.setHint("skriv ditt namn här");

            builder.setView(input);

            builder.setPositiveButton("ok", (dialog, which) -> {
                name  = input.getText().toString();
            });
            builder.show();
        }


        LocalDate today = LocalDate.now();
        monday = today.with(DayOfWeek.MONDAY);

        printSchedule(monday);
        selectedDate = monday;
        changeWeek(monday);


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
        asyncLoadShiftSwaps();
        //asyncLoadShifts();
        String testDate = "2026-02-28";
        //asyncLoadShiftsByDate(testDate);

        //--------POST---------------------
        //exampleCreateShiftSwap();
        //--------PUT-(EDIT)---------------
        //exampleRejectShiftChangeStatus();
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
        TextView monthTextView = findViewById(R.id.monthTextView);
        TextView weekTextView = findViewById(R.id.weekTextView);
        monthTextView.setText(monday.getMonth().getDisplayName(TextStyle.FULL, new Locale("sv", "SE")));
        weekTextView.setText("Vecka" + monday.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
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

        addHeader(nightContainer, "kväll kl. 16-23", false);
        addPersonToSchedule(nightContainer, nightList);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void printSchedule(LocalDate today) {
        dayWork.clear();
        nightWork.clear();
        dayWork.put(today, new ArrayList<>(List.of("Sigrid", "Frank", "Susanna")));
        nightWork.put(today, new ArrayList<>(List.of("Andreas", "Jacob", "Christine")));
        dayWork.put(today.plusDays(1), new ArrayList<>(List.of("Andreas", "Jacob", "Christine")));
        nightWork.put(today.plusDays(1), new ArrayList<>(List.of("Molly", "Frank", "Susanna")));
        dayWork.put(today.plusDays(2), new ArrayList<>(List.of("Molly", "Frank", "Susanna")));
        nightWork.put(today.plusDays(2), new ArrayList<>(List.of("Andreas", "Jacob", "Christine")));


    }

    private void addPersonToSchedule(LinearLayout container, List<String> personList) {
        for(String person : personList) {
            TextView personTextView = new TextView(this);
            personTextView.setText(person);
            personTextView.setPadding(32, 32, 32, 32);
            personTextView.setTextSize(25);
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

    private void sendChangeRequest(boolean dayOrNot) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Redigera schema");
        builder.setMessage("Välj den person som inte ska jobba");
        builder.setPositiveButton("OK", null);
        builder.show();
    }

    private void selectNewPerson(String personName) {

        String[] personArray = allPersons.toArray(new String[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Byt pass för " + personName);

        builder.setItems(personArray, (dialog, which) -> {
            String selectedPerson = personArray[which];
            swapPerson(personName, selectedPerson);

            Toast.makeText(this, "förfrågan skickad", Toast.LENGTH_SHORT).show();
        });

        builder.setNegativeButton("Avbryt", null);
        builder.show();
    }

    //till senare
    private void swapPerson(String personName, String changeToPerson) {
        Log.d("REQUESR", "Swap request" + personName + " -> " + changeToPerson);
        Toast.makeText(this, "Förfrågan skcikad", Toast.LENGTH_SHORT).show();

    }

    private void checkId() {}


    // Send the correlating object or data to GET/POST/PUT
    private void asyncLoadEmployees() {

        employeesRepo.getEmployees(new EmployeesRepository.GetCallback() {
            @Override
            public void onSuccess(List<Employee> employees) {
                populateEmployeesUI(employees);
            }

            @Override
            public void onError(String message) {
                Log.e("EMPLOYEES", "Fel: " + message);
            }
        });
    }
    private void asyncLoadShiftSwaps() {
        shiftsRepo.getShiftSwaps(new ShiftsRepository.GetShiftSwapCallback() {
            @Override
            public void onSuccess(List<ShiftSwap> shiftSwaps) {
                populateShiftSwapsUI(shiftSwaps);
            }

            @Override
            public void onError(String message) {
                Log.e("SHIFTSWAPS", "Fel: " + message);
            }
        });
    }
    private void asyncLoadShifts() {
        shiftsRepo.getShifts(new ShiftsRepository.GetCallback() {
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

    private void populateShiftSwapsUI(List<ShiftSwap> shiftSwaps) {
        Log.d("SHIFTSWAPS", "" + shiftSwaps.size());
        ShiftSwap swap = shiftSwaps.get(0);
        int swapId = swap.id;
        exampleAcceptShiftChangeStatus(swapId);
    }
    private void populateEmployeesUI(List<Employee> employees) {
        Log.d("EMPLOYEES", "" + employees.size());
    }
    private void populateShiftsUI(List<Shift> shifts) {
        Log.d("SHIFTS", "" + shifts.size());
        for (Shift s : shifts) {
            Log.d("Date: ", "" + s.date);
            Log.d("ShiftType: ", "" + s.shiftType.name);
            for (Employee e : s.employeeList) {
                Log.d("Employee", "android id: " + e.androidId);
                Log.d("Employee", "name: " + e.name);
            }
        }
    }


    // Denna fungerar. Man kan byta bort sin egna plats på ett pass,
    // men inte byta till sig ett pass (?)
    public void exampleCreateShiftSwap() {
        ShiftSwap sc = new ShiftSwap();
        String senderId = "cd20486bd301b60f";       // Mike
        String  recieverId = "cd20486bd301b606";    // Konan Barbaren
        int shiftId = 1;

        sc.senderId = senderId;
        sc.receiverId = recieverId;
        sc.shiftId = shiftId;

        asyncPostShiftSwap(sc);
    }



    // Updates a pending Shift change with approved or disapproved status
    public void exampleRejectShiftChangeStatus() {
        ShiftUpdate update = new ShiftUpdate();
        update.status = "rejected";
        int shiftSwapId = 1; // NOT shiftId of the shiftSwap. Use shift.id of the Shift class
        asyncPutShiftUpdate(shiftSwapId, update);
    }
    public void exampleAcceptShiftChangeStatus(int id) {
        ShiftUpdate update = new ShiftUpdate();
        update.status = "approved";
        asyncPutShiftUpdate(id, update);
    }

}