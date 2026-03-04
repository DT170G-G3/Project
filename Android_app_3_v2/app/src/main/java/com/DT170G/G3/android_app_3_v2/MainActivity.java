package com.DT170G.G3.android_app_3_v2;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
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
    
    private void updateSchedule() {
        LinearLayout dayContainer = findViewById(R.id.dayScheduleContainer); 
        LinearLayout nightContainer = findViewById(R.id.nightScheduleContainer); 
        if(selectedDate == null) { 
            selectedDate = LocalDate.now(); 
        } 
        dayContainer.removeAllViews(); 
        nightContainer.removeAllViews(); 
        List<String> dayList = dayWork.get(selectedDate); 
        if(dayList == null) { 
            dayList = new ArrayList<>(); 
        } 
        List<String> nightList = nightWork.get(selectedDate); 
        if(nightList == null) { 
            nightList = new ArrayList<>(); 
        } TextView dayTitle = new TextView(this); 
        
        dayTitle.setText("Dag"); dayContainer.addView(dayTitle); 
        
        for(String person : dayList) { 
            TextView personTextView = new TextView(this); 
            personTextView.setText(person); 
            personTextView.setPadding(8,8,8,8); 
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT); 
            personTextView.setLayoutParams(p); 
            dayContainer.addView(personTextView); 
        } 
        
        TextView nightTitle = new TextView(this); 
        nightTitle.setText("Kväll"); 
        nightContainer.addView(nightTitle); 
        
        for(String person : nightList) { 
            TextView personTextView = new TextView(this);
            personTextView.setText(person); 
            personTextView.setPadding(8,8,8,8); 
            LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT); 
            personTextView.setLayoutParams(p); nightContainer.addView(personTextView); 
        } 
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
    public void changeWeek(LocalDate monday) {
        TextView monthTextView = findViewById(R.id.monthTextView); 
        TextView weekTextView = findViewById(R.id.weekTextView); 
        monthTextView.setText(monday.getMonth().getDisplayName(TextStyle.FULL, new Locale("sv", "SE"))); 
        weekTextView.setText("Vecka" + monday.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)); 
        findDateToButtons(monday); 
        clickedDay(monday); 
        
        if(selectedButton == null) {
            Button mondayButton = findViewById(R.id.mondayButton); 
            mondayButton.setBackgroundTintList(getResources().getColorStateList(R.color.markedButtonColor)); 
            selectedButton = mondayButton; 
            selectedDate = monday; 
        } 
        updateSchedule(); 
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
    
    private void styleOnClickedDay(LocalDate date, Button clickedButton) { 
        selectedDate = date; 
        if (selectedButton != null) {
            selectedButton.setBackgroundTintList(getResources().getColorStateList(R.color.dayButtonColor)); 
        } 
        clickedButton.setBackgroundTintList(getResources().getColorStateList(R.color.markedButtonColor));
        selectedButton = clickedButton; 
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
}