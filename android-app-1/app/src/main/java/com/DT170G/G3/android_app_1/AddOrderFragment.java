package com.DT170G.G3.android_app_1;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddOrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddOrderFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddOrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddOrderFragment newInstance(String param1, String param2) {
        AddOrderFragment fragment = new AddOrderFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Bordsval
        Spinner tableSpinner = view.findViewById(R.id.tableSpinner);
        //byt detta till inläsning från databas
        String[] tables = getResources().getStringArray(R.array.tableList);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.tableList,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tableSpinner.setAdapter(adapter);


        //Byt detta mot inlsäning från databas
        String[] drinks = getResources().getStringArray(R.array.drinkList);
        String[] foods = getResources().getStringArray(R.array.foodList);

        LinearLayout drinkContainer = view.findViewById(R.id.drinkContainer);
        LinearLayout foodContainer = view.findViewById(R.id.foodContainer);

        //loopa igenom listan av drinkar för att skriva ut
        for(String drink : drinks) {
            drinkContainer.addView((createDrinkAndFoodRow(drink)));
        }

        for(String food : foods) {
            foodContainer.addView(createDrinkAndFoodRow(food));
        }
    }

    private LinearLayout createDrinkAndFoodRow(String itemName) {
        LinearLayout drinkAndFoodRow = new LinearLayout(requireContext());
        drinkAndFoodRow.setOrientation(LinearLayout.HORIZONTAL);
        drinkAndFoodRow.setPadding(0,16,0,16);

        //Dryckesnamn
        TextView drinkName = new TextView(requireContext());
        drinkName.setText(itemName);
        drinkName.setTextSize(18);
        drinkName.setLayoutParams(new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT,1
        ));

        //Minusknapp
        Button minusButton = new Button(requireContext());
        minusButton.setText("-");
        minusButton.setLayoutParams(new LinearLayout.LayoutParams(120,120));

        //Antal
        TextView numberText = new TextView((requireContext()));
        numberText.setText("0");
        numberText.setTextSize(18);
        numberText.setGravity(Gravity.CENTER);
        numberText.setLayoutParams(new LinearLayout.LayoutParams(120,120));

        //Plusknapp
        Button plusButton = new Button(requireContext());
        plusButton.setText("+");
        plusButton.setLayoutParams(new LinearLayout.LayoutParams(120,120));

        //öka antal
        plusButton.setOnClickListener(numberButtonClicked -> {
            int currentNumber = Integer.parseInt(numberText.getText().toString());
            numberText.setText(String.valueOf(currentNumber +1));
        });

        minusButton.setOnClickListener(numberButtonClicked -> {
            int currentNumber = Integer.parseInt(numberText.getText().toString());
            if(currentNumber > 0 ) {
                numberText.setText(String.valueOf(currentNumber -1));
            }
        });

        drinkAndFoodRow.addView(drinkName);
        drinkAndFoodRow.addView(minusButton);
        drinkAndFoodRow.addView(numberText);
        drinkAndFoodRow.addView(plusButton);

        return drinkAndFoodRow;
    }

}