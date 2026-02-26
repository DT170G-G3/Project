package com.DT170G.G3.android_app_1.fragments;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.DT170G.G3.android_app_1.R;
import com.DT170G.G3.android_app_1.tables.Table;
import com.DT170G.G3.android_app_1.tables.TablesRepository;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TableFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TableFragment extends Fragment {
    TablesRepository tablesRepo = new TablesRepository();

    public TableFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddFragment.
     */
    public static TableFragment newInstance(String param1, String param2) {
        TableFragment fragment = new TableFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        asyncLoadTables();
/**
         String[] tables = getResources().getStringArray(R.array.tableList);

        LinearLayout tableView = view.findViewById(R.id.tableLayout);

        for(String table : tables){
            tableView.addView(createButton(table));
        }
*/

    }

    public Button createButton(String item){
        //Skapar knappen
        Button tableButton = new Button(requireContext());
        tableButton.setAllCaps(false);
        tableButton.setText("Bord " + item);
        tableButton.setTextColor(Color.parseColor("#000000"));

        //Ändrar färg och form på knappen
        tableButton.setBackground(ContextCompat.getDrawable(requireContext(),R.drawable.button_ripple));

        //Lägger till margins till knappen
        LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        buttonParams.setMargins(35,20,35,20);
        tableButton.setLayoutParams(buttonParams);

        //Funktion för tryck på knapp, ändrar text för valt bord till valda bordet samt ändrar till dryckessidan
        tableButton.setOnClickListener(buttonClicked -> {
            //Ändra text för valt bord
            TextView tv = requireActivity().findViewById(R.id.selectedTable);
            tv.setText("Bord " + item);

            //Ändrar vy till Drinkvy
            ViewPager2 viewPager = requireActivity().findViewById(R.id.viewPager);
            viewPager.setCurrentItem(1);
        });


        return tableButton;
    }


    private void asyncLoadTables() {
        tablesRepo.getTables(new TablesRepository.GetCallback() {
            @Override
            public void onSuccess(List<Table> tables) {
                populateTablesUI(tables);
            }
            @Override
            public void onError(String message) {
                Log.e("TABLES", "Fel: " + message);
            }
        });
    }

    private void populateTablesUI(List<Table> tables) {
        LinearLayout tableView = requireView().findViewById(R.id.tableLayout);

        for(Table table : tables){
            String tableNumber = String.valueOf(table.getTableId());
                        tableView.addView(createButton(tableNumber));
        }
    }
}