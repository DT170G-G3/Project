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
import com.DT170G.G3.android_app_1.dishes.Dish;
import com.DT170G.G3.android_app_1.dishes.DishesRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StarterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StarterFragment extends Fragment {
    DishesRepository dishesRepo = new DishesRepository();
    private List<TextView> allStarterCounters = new ArrayList<>();

    public StarterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment StarterFragment.
     */
    public static StarterFragment newInstance(String param1, String param2) {
        StarterFragment fragment = new StarterFragment();
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
        return inflater.inflate(R.layout.fragment_starter, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        asyncLoadStarters();
        starterNotesSwitchListener();
    }

    private void starterNotesSwitchListener(){
        Switch starterSwitch = requireView().findViewById(R.id.starterNotesSwitch);
        EditText starterNotes = requireView().findViewById(R.id.starterNotes);

        starterSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(@NonNull CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    starterNotes.setVisibility(VISIBLE);
                } else{
                    starterNotes.setVisibility(GONE);
                }
            }
        });
    }

    public void resetStarterCounter(){
        for(TextView starterCounter : allStarterCounters){
            starterCounter.setText("0");
        }
    }
    private void asyncLoadStarters() {
        dishesRepo.getDishes(new DishesRepository.GetCallback() {
            @Override
            public void onSuccess(List<Dish> dishes) {
                //DishesCache.setCache(dishes);   //om vi ska använda cache
                populateStartersUI(dishes);
            }
            @Override
            public void onError(String message) {
                Log.e("STARTER", "Fel: " + message);
            }
        });
    }

    private void populateStartersUI(List<Dish> dishes) {
        LinearLayout starterView = requireView().findViewById(R.id.starterLayout);
        OrderItemRow orderItemRow = new OrderItemRow();

        for(Dish dish : dishes){
            int catId = dish.getDishCategoryId();
            //Lägger till alla med katergori 1 som är förrätter
             if(catId != 1) {
                 continue;
             }
            starterView.addView(orderItemRow.createItemRow(requireContext(), dish.getName(), allStarterCounters));
        }
    }

}