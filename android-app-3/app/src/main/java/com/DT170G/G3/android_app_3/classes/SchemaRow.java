package com.DT170G.G3.android_app_3.classes;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.DT170G.G3.android_app_3.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple class for creating
 * LinearLayout with order items
 *
 *
 */
public class SchemaRow {

    public SchemaRow() {
        // Required empty public constructor
    }

    /**
     * Use this function to create a LinearLayout with a Button and TextView
     * for displaying orderable items.
     *
     * @return A LinearLayout with a button and counter for items that can be ordered.
     */
    public LinearLayout createSchemaRow(Context context,String weekdayName, String[] morningStaff, String[] afternoonStaff) {
        List<TextView> workingMorningStaff = new ArrayList<>();
        List<TextView> workingAfternoonStaff = new ArrayList<>();

        //Skapar en LinearLayout som håller i två Linjära LinearLayouts med morgon och eftermiddagspass
        LinearLayout schemaRow = new LinearLayout(context);
        schemaRow.setOrientation(LinearLayout.HORIZONTAL);
        schemaRow.setPadding(20, 20, 20, 0);
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rowParams.setMargins(0, 10, 0, 10);
        schemaRow.setLayoutParams(rowParams);


        //MORNING SHIFT COLUMN
        LinearLayout morningColumn = new LinearLayout(context);
        morningColumn.setOrientation(LinearLayout.VERTICAL);
        morningColumn.setPadding(20, 20, 20, 20);
        morningColumn.setBackground(ContextCompat.getDrawable(context, R.drawable.button_ripple));
        LinearLayout.LayoutParams columnParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        columnParams.setMargins(30, 10, 20, 0);
        morningColumn.setLayoutParams(columnParams);

        //AFTERNOON SHIFT COLUMN
        LinearLayout afternoonColumn = new LinearLayout(context);
        afternoonColumn.setOrientation(LinearLayout.VERTICAL);
        afternoonColumn.setPadding(20, 20, 20, 20);
        afternoonColumn.setBackground(ContextCompat.getDrawable(context, R.drawable.button_ripple));
        afternoonColumn.setLayoutParams(columnParams);


        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParams.setMargins(10, 30, 0, 0);

        TextView weekday = new TextView(context);
        weekday.setText(weekdayName);
        weekday.setLayoutParams(textParams);


        LinearLayout.LayoutParams textParamsStaff = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        textParamsStaff.setMargins(10, 5, 0, 5);

        // Textviews för förmiddagspersonalen
        TextView morningText = new TextView(context);
        morningText.setText("Förmiddag");
        morningText.setLayoutParams(textParamsStaff);
        morningText.setTypeface(null, Typeface.BOLD);
        morningColumn.addView(morningText);

        for (String person : morningStaff) {
            TextView morning = new TextView(context);
            morning.setText(person);
            morning.setLayoutParams(textParamsStaff);
            morningColumn.addView(morning);
            workingMorningStaff.add(morning);
        }

        //Skapar TextView för eftermiddagspersonalen
        TextView afternoonText = new TextView(context);
        afternoonText.setText("Förmiddag");
        afternoonText.setLayoutParams(textParamsStaff);
        afternoonText.setTypeface(null, Typeface.BOLD);
        afternoonColumn.addView(afternoonText);

        for (String person : afternoonStaff) {
            TextView afternoon = new TextView(context);
            afternoon.setText(person);
            afternoon.setLayoutParams(textParamsStaff);
            afternoonColumn.addView(afternoon);
            workingAfternoonStaff.add(afternoon);

        }

        morningColumn.setOnClickListener(buttonClicked -> {
            changeWorkingStaff(context, workingMorningStaff);
        });


        afternoonColumn.setOnClickListener(buttonClicked -> {
            changeWorkingStaff(context, workingAfternoonStaff);
        });



        //Lägg till förmiddag och eftermiddags kolumnerna i LinearLayouten schemaRow
        schemaRow.addView(weekday);
        schemaRow.addView(morningColumn);
        schemaRow.addView(afternoonColumn);
        return schemaRow;
    }

    private void changeWorkingStaff(Context context, List<TextView> currentWorkingStaff){

        String[] staffOptions = {"Viktor", "Björn", "Elina", "Albin", "Klara"};
        String[] currentStaff = getCurrentNames(currentWorkingStaff);

        new AlertDialog.Builder(context)
                .setTitle("Välj den som ska ersättas")
                        .setItems(currentStaff, (dialog, which) -> {
                                TextView tv = currentWorkingStaff.get(which);

                                new AlertDialog.Builder(context)
                                        .setTitle("Välj ersättare")
                                        .setItems(staffOptions, (dialog2, whichReplacement) -> {
                                                tv.setText(staffOptions[whichReplacement]);
                                        }).setNegativeButton("Avbryt", null)
                                        .show();
                })
                .setNegativeButton("Avbryt", null)
                .show();
    }

    private String[] getCurrentNames(List<TextView> currentWorkingStaff){
        String[] names = new String[currentWorkingStaff.size()];
        for (int i = 0; i < currentWorkingStaff.size(); i++){
            names[i] = currentWorkingStaff.get(i).getText().toString();
        }
        return names;

    }
}