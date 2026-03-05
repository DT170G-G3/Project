package com.DT170G.G3.android_app_1.fragments;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.DT170G.G3.android_app_1.R;
import com.DT170G.G3.android_app_1.classes.OrderItemRow;
import com.DT170G.G3.android_app_1.drinks.Drink;
import com.DT170G.G3.android_app_1.drinks.DrinksRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DrinkFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DrinkFragment extends Fragment {

    DrinksRepository drinksRepo = new DrinksRepository();
    private List<TextView> allDrinkCounters = new ArrayList<>();
    private List<Integer> allOrderedDrinks = new ArrayList<>();

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
        asyncLoadDrinks();
    }


    public void resetDrinkCounter(){
        for(TextView drinkCounter : allDrinkCounters){
            drinkCounter.setText("0");
        }
    }

    public void clearAllOrderedDrinks(){
        allOrderedDrinks.clear();
    }

    public List<Integer> getAllOrderedDrinks(){
        return allOrderedDrinks;
    }

    private void asyncLoadDrinks() {
        drinksRepo.getDrinks(new DrinksRepository.GetCallback() {
            @Override
            public void onSuccess(List<Drink> drinks) {
                populateDrinksUI(drinks);
            }

            @Override
            public void onError(String message) {
                Log.e("DRINKS", "Fel: " + message);
            }
        });
    }

    private void populateDrinksUI(List<Drink> drinks) {
        LinearLayout drinkView = requireView().findViewById(R.id.drinkLayout);
        OrderItemRow orderItemRow = new OrderItemRow();

        for(Drink drink : drinks){
            drinkView.addView(orderItemRow.createItemRow(requireContext(), drink.getId() , drink.getName(), allDrinkCounters, allOrderedDrinks));
        }
    }
}