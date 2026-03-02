package com.DT170G.G3.android_app_1.classes;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.content.ContentProvider;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.icu.text.Transliterator;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.DT170G.G3.android_app_1.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple class for creating
 * LinearLayout with order items
 *
 *
 */
public class OrderItemRow {
    public OrderItemRow() {
        // Required empty public constructor
    }

    /**
     * Use this function to create a LinearLayout with a Button and TextView
     * for displaying orderable items.
     *
     * @return A LinearLayout with a button and counter for items that can be ordered.
     */
    public LinearLayout createItemRow(Context context, int itemId, String item, List<TextView> allItemCounters, List<Integer> allOrderedItems){
        //Skapar en LinearLayout som innehåller en Button och en TextView för att kunna ta ordrar
        LinearLayout itemRow = new LinearLayout(context);
        itemRow.setOrientation(LinearLayout.HORIZONTAL);

        itemRow.setPadding(35, 10,10,5);
        LinearLayout.LayoutParams rowParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rowParams.setMargins(0,10,0,10);
        itemRow.setLayoutParams(rowParams);


        //Skapar Button
        Button itemButton = new Button(context);
        itemButton.setAllCaps(false);
        itemButton.setText(item);
        itemButton.setTextColor(Color.parseColor("#000000"));
        itemButton.setTextSize(16);

        //Ändrar färg och form på knappen
        itemButton.setBackground(ContextCompat.getDrawable(context, R.drawable.button_ripple));
        itemButton.setLayoutParams(new LinearLayout.LayoutParams(800, ViewGroup.LayoutParams.WRAP_CONTENT ));


        //Skapar TextView för counter
        TextView itemCounter = new TextView(context);
        itemCounter.setText("0");
        itemCounter.setTextSize(16);
        LinearLayout.LayoutParams counterParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        counterParams.setMargins(50,0,0,0);
        itemCounter.setLayoutParams(counterParams);

        //Lägg till räknaren i lista för enklare rensning av formuläret
        allItemCounters.add(itemCounter);


        // Lyssnare för korta klick - Ökar antalet med ett
        itemButton.setOnClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(itemCounter.getText().toString());
            //Öka räknaren
            itemCounter.setText(String.valueOf(currentNumber +1));
            //Lägg till i listan över beställda drinkar
            allOrderedItems.add(itemId);
        });

        // Lyssnare för långa klick - Minskar antalet med ett
        itemButton.setOnLongClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(itemCounter.getText().toString());
            if (currentNumber > 0) {
                //Minska räknaren
                itemCounter.setText(String.valueOf(currentNumber - 1));
                //Ta bort från listan över beställda drinkar
                allOrderedItems.remove(Integer.valueOf(itemId));
            }
            return true;
        });

        //Lägg till Button och TextViewn till LinearLayouten itemRow
        itemRow.addView(itemButton);
        itemRow.addView(itemCounter);
        return itemRow;
    }

}