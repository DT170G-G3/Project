package com.DT170G.G3.android_app_1.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.DT170G.G3.android_app_1.R;
import com.DT170G.G3.android_app_1.classes.OrderItemRow;
import com.DT170G.G3.android_app_1.dishes.Dish;
import com.DT170G.G3.android_app_1.dishes.DishesRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DessertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DessertFragment extends Fragment {
    DishesRepository dishesRepo = new DishesRepository();
    private List<TextView> allDessertCounters = new ArrayList<>();
    private Map<Integer, Integer> quantityOrderedDesserts = new HashMap<>();

    public DessertFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DessertFragment.
     */
    public static DessertFragment newInstance(String param1, String param2) {
        DessertFragment fragment = new DessertFragment();
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
        return inflater.inflate(R.layout.fragment_dessert, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        asyncLoadDesserts();
    }

    /**
     * Återställer räknarna kopplad till alla efterrätter
     */
    public void resetDessertCounter(){
        for(TextView dessertCounter : allDessertCounters){
            dessertCounter.setText("0");
        }
    }

    public void clearAllOrderedDesserts(){
        quantityOrderedDesserts.clear();
    }

    /**
     * Hämtar alla beställda efterrätter
     * @return Lista med IDn för alla beställda efterrätter
     */
    public Map<Integer, Integer> getAllOrderedDesserts(){
        return quantityOrderedDesserts;
    }

    private void asyncLoadDesserts() {
        dishesRepo.getDishes(new DishesRepository.GetCallback() {
            @Override
            public void onSuccess(List<Dish> dishes) {
                populateDessertUI(dishes);
            }
            @Override
            public void onError(String message) {
                Log.e("DESSERT", "Fel: " + message);
            }
        });
    }

    /**
     * Lägger till alla efterrätter på sidan för efterrätter
     * @param desserts
     */
    private void populateDessertUI(List<Dish> desserts) {
        LinearLayout dessertView = requireView().findViewById(R.id.dessertLayout);
        OrderItemRow orderItemRow = new OrderItemRow();

        for(Dish dessert : desserts) {
            int catId = dessert.getDishCategoryId();
            //Lägger till alla med kategori 3 som är Desserter
            if (catId != 3) {
                continue;
            }
            dessertView.addView(orderItemRow.createItemRow(requireContext(), dessert.getId(), dessert.getName(), allDessertCounters, quantityOrderedDesserts));
        }
    }
}