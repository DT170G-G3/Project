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

import com.DT170G.G3.android_app_1.R;
import com.DT170G.G3.android_app_1.classes.OrderItemRow;
import com.DT170G.G3.android_app_1.dishes.Dish;
import com.DT170G.G3.android_app_1.dishes.DishesRepository;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DessertFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DessertFragment extends Fragment {
    DishesRepository dishesRepo = new DishesRepository();

    public DessertFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment DessertFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DessertFragment newInstance(String param1, String param2) {
        DessertFragment fragment = new DessertFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
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

        //TODO Ändra så att denna används när API är färdigt
        //asyncLoadDishes();

        String[] desserts = getResources().getStringArray(R.array.dessertList);

        LinearLayout dessertView = view.findViewById(R.id.dessertLayout);

        OrderItemRow orderItemRow = new OrderItemRow();

        for(String dessert : desserts){
            dessertView.addView(orderItemRow.createItemRow(requireContext(), dessert));
        }

    }

    private void asyncLoadDishes() {
        dishesRepo.getDishes(new DishesRepository.GetCallback() {
            @Override
            public void onSuccess(List<Dish> dishes) {
                //DishesCache.setCache(dishes);   //om vi ska använda cache
                populateDishesUI(dishes);
            }
            @Override
            public void onError(String message) {
                Log.e("DISHES", "Fel: " + message);
            }
        });
    }

    public void populateDishesUI(List<Dish> dishes) {
        LinearLayout starterView = requireView().findViewById(R.id.starterLayout);
        OrderItemRow orderItemRow = new OrderItemRow();

        for(Dish dish : dishes){
            int catId = dish.getCategoryId();
            //Log.d("DEBUG", "Dish: " + dish.getName() + " catId: " + catId);
            // TODO Lägg till igen när den hämtar från a la carte och inte lunch menyn
            /**
             if(catId != 2){
             continue;
             }
             */
            starterView.addView(orderItemRow.createItemRow(requireContext(), dish.getName()));
        }
        Log.d("DISH STARTER", "Size: " +dishes.size());
    }
}