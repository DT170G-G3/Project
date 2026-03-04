package com.DT170G.G3.android_app_3_v2;

import android.os.Build;
import android.os.Bundle;
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
    private LocalDate monday;
    private Button selectedButton;
    private LocalDate selectedDate;
    //bara för test, byt mot databas sen private
    Map<LocalDate, List<String>> dayWork = new HashMap<>();
    private Map<LocalDate, List<String>> nightWork = new HashMap<>();

    //för test
    private List<String> allPersons = List.of("Molly", "Frank", "Melker", "Andreas", "Jacob", "Christine", "Doris");
    private @NonNull Insets systemBars;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


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
        dayWork.put(today, new ArrayList<>(List.of("Molly", "Frank", "Susanna")));
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
}