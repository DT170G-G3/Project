package com.DT170G.G3.android_app_3.classes;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

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
    public LinearLayout createSchemaRow(Context context, String[] staff, String weekdayName) {
        //Skapar en LinearLayout som innehåller en Button och en TextView för att kunna ta ordrar
        LinearLayout schemaRow = new LinearLayout(context);
        schemaRow.setOrientation(LinearLayout.HORIZONTAL);
        schemaRow.setPadding(20, 20, 20, 0);
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rowParams.setMargins(0, 10, 0, 10);
        schemaRow.setLayoutParams(rowParams);

        LinearLayout morningColumn = new LinearLayout(context);
        morningColumn.setOrientation(LinearLayout.VERTICAL);
        morningColumn.setPadding(0, 0, 0, 0);
        LinearLayout.LayoutParams columnParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1f);
        columnParams.setMargins(0, 10, 0, 0);
        morningColumn.setLayoutParams(columnParams);


        LinearLayout afternoonColumn = new LinearLayout(context);
        afternoonColumn.setOrientation(LinearLayout.VERTICAL);
        afternoonColumn.setPadding(0, 0, 0, 0);
        afternoonColumn.setLayoutParams(columnParams);

        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        TextView weekday = new TextView(context);
        weekday.setText(weekdayName);
        textParams.setMargins(0, 0, 0, 0);
        weekday.setLayoutParams(textParams);

        //Skapar TextView för counter
        TextView morning1 = new TextView(context);
        morning1.setText("Anna");
        textParams.setMargins(0, 0, 0, 0);
        morning1.setLayoutParams(textParams);

        //Skapar TextView för counter
        TextView morning2 = new TextView(context);
        morning2.setText("Erik");
        textParams.setMargins(0, 0, 0, 0);
        morning2.setLayoutParams(textParams);

        //Skapar TextView för counter
        TextView morning3 = new TextView(context);
        morning3.setText("Magnus");
        textParams.setMargins(0, 0, 0, 0);
        morning3.setLayoutParams(textParams);


/**

 //Skapar Button
 Button itemButton = new Button(context);
 itemButton.setAllCaps(false);
 itemButton.setText(item);
 itemButton.setTextColor(Color.parseColor("#FFFFFF"));

 //Ändrar färg och form på knappen
 GradientDrawable gd = new GradientDrawable();
 gd.setColor(Color.parseColor("#737373"));
 gd.setCornerRadius(75);
 itemButton.setBackground(gd);

 itemButton.setLayoutParams(new LinearLayout.LayoutParams(400, ViewGroup.LayoutParams.WRAP_CONTENT ));

 */
        //Skapar TextView för eftermiddagspersonalen
        TextView afternoon1 = new TextView(context);
        afternoon1.setText("Malin");
        textParams.setMargins(0, 0, 0, 0);
        afternoon1.setLayoutParams(textParams);

        TextView afternoon2 = new TextView(context);
        afternoon2.setText("Sofie");
        textParams.setMargins(0, 0, 0, 0);
        afternoon2.setLayoutParams(textParams);

        TextView afternoon3 = new TextView(context);
        afternoon3.setText("Adam");
        textParams.setMargins(0, 0, 0, 0);
        afternoon3.setLayoutParams(textParams);

        morningColumn.addView(morning1);
        morningColumn.addView(morning2);
        morningColumn.addView(morning3);
        afternoonColumn.addView(afternoon1);
        afternoonColumn.addView(afternoon2);
        afternoonColumn.addView(afternoon3);


        //Lägg till förmiddag och eftermiddags kolumnerna i LinearLayouten schemaRow
        schemaRow.addView(weekday);
        schemaRow.addView(morningColumn);
        schemaRow.addView(afternoonColumn);
        return schemaRow;
    }
}