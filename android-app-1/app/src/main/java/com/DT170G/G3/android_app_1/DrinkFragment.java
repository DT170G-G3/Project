package com.DT170G.G3.android_app_1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrinkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrinkFragment extends Fragment {

    public DrinkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     *
     * @return A new instance of fragment DrinkFragment.
     */
    public static DrinkFragment newInstance() {
        DrinkFragment fragment = new DrinkFragment();
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
        return inflater.inflate(R.layout.fragment_drink, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button d1Button = view.findViewById(R.id.drinkButton1);
        TextView counter1Text = view.findViewById(R.id.drinkCounter1);


        // Lyssnare för korta klick - Ökar antalet med ett
        d1Button.setOnClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(counter1Text.getText().toString());
            counter1Text.setText(String.valueOf(currentNumber +1));
        });

        // Lyssnare för långa klick - Minskar antalet med ett
        d1Button.setOnLongClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(counter1Text.getText().toString());
            if (currentNumber > 0) {
                counter1Text.setText(String.valueOf(currentNumber - 1));
            }
            return true;
        });


        // KNAPP 2
        Button d2Button = view.findViewById(R.id.drinkButton2);
        TextView counter2Text = view.findViewById(R.id.drinkCounter2);


        // Lyssnare för korta klick - Ökar antalet med ett
        d2Button.setOnClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(counter2Text.getText().toString());
            counter2Text.setText(String.valueOf(currentNumber +1));
        });

        // Lyssnare för långa klick - Minskar antalet med ett
        d2Button.setOnLongClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(counter2Text.getText().toString());
            if (currentNumber > 0) {
                counter2Text.setText(String.valueOf(currentNumber - 1));
            }
            return true;
        });





        // KNAPP 3
        Button d3Button = view.findViewById(R.id.drinkButton3);
        TextView counter3Text = view.findViewById(R.id.drinkCounter3);


        // Lyssnare för korta klick - Ökar antalet med ett
        d3Button.setOnClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(counter3Text.getText().toString());
            counter3Text.setText(String.valueOf(currentNumber +1));
        });

        // Lyssnare för långa klick - Minskar antalet med ett
        d3Button.setOnLongClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(counter3Text.getText().toString());
            if (currentNumber > 0) {
                counter3Text.setText(String.valueOf(currentNumber - 1));
            }
            return true;
        });



        // KNAPP 4
        Button d4Button = view.findViewById(R.id.drinkButton4);
        TextView counter4Text = view.findViewById(R.id.drinkCounter4);


        // Lyssnare för korta klick - Ökar antalet med ett
        d4Button.setOnClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(counter4Text.getText().toString());
            counter4Text.setText(String.valueOf(currentNumber +1));
        });

        // Lyssnare för långa klick - Minskar antalet med ett
        d4Button.setOnLongClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(counter4Text.getText().toString());
            if (currentNumber > 0) {
                counter4Text.setText(String.valueOf(currentNumber - 1));
            }
            return true;
        });




        // KNAPP 5
        Button d5Button = view.findViewById(R.id.drinkButton5);
        TextView counter5Text = view.findViewById(R.id.drinkCounter5);


        // Lyssnare för korta klick - Ökar antalet med ett
        d5Button.setOnClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(counter5Text.getText().toString());
            counter5Text.setText(String.valueOf(currentNumber +1));
        });

        // Lyssnare för långa klick - Minskar antalet med ett
        d5Button.setOnLongClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(counter5Text.getText().toString());
            if (currentNumber > 0) {
                counter5Text.setText(String.valueOf(currentNumber - 1));
            }
            return true;
        });



        // KNAPP 6
        Button d6Button = view.findViewById(R.id.drinkButton6);
        TextView counter6Text = view.findViewById(R.id.drinkCounter6);


        // Lyssnare för korta klick - Ökar antalet med ett
        d6Button.setOnClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(counter6Text.getText().toString());
            counter6Text.setText(String.valueOf(currentNumber +1));
        });

        // Lyssnare för långa klick - Minskar antalet med ett
        d6Button.setOnLongClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(counter6Text.getText().toString());
            if (currentNumber > 0) {
                counter6Text.setText(String.valueOf(currentNumber - 1));
            }
            return true;
        });


        // KNAPP 7
        Button d7Button = view.findViewById(R.id.drinkButton7);
        TextView counter7Text = view.findViewById(R.id.drinkCounter7);


        // Lyssnare för korta klick - Ökar antalet med ett
        d7Button.setOnClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(counter7Text.getText().toString());
            counter7Text.setText(String.valueOf(currentNumber +1));
        });

        // Lyssnare för långa klick - Minskar antalet med ett
        d7Button.setOnLongClickListener(buttonClicked -> {
            int currentNumber = Integer.parseInt(counter7Text.getText().toString());
            if (currentNumber > 0) {
                counter7Text.setText(String.valueOf(currentNumber - 1));
            }
            return true;
        });


    }


}