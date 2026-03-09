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
import com.DT170G.G3.android_app_1.orders.DishEntry;
import com.DT170G.G3.android_app_1.orders.DrinkEntry;
import com.DT170G.G3.android_app_1.orders.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainCourseFragment extends Fragment {
    DishesRepository dishesRepo = new DishesRepository();
    private List<TextView> allMainCounters = new ArrayList<>();
    private Map<Integer, Integer> quantityOrderedMainCourses = new HashMap<>();

    public MainCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment MainCourseFragment.
     */
    public static MainCourseFragment newInstance(String param1, String param2) {
        MainCourseFragment fragment = new MainCourseFragment();
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
        return inflater.inflate(R.layout.fragment_main_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        asyncLoadMainDishes();
    }

    /**
     * Återställer räknarna kopplad till alla varmrätter
     */
    public void resetMainCounter() {
        for (TextView mainCounter : allMainCounters) {
            mainCounter.setText("0");
        }
    }

    public void clearAllOrderedMainCourses() {
        quantityOrderedMainCourses.clear();
    }

    /**
     * Hämtar alla beställda varmrätter
     *
     * @return Lista med IDn för alla beställda varmrätter
     */
    public Map<Integer, Integer> getAllOrderedMainCourses() {
        return quantityOrderedMainCourses;
    }

    private void asyncLoadMainDishes() {
        dishesRepo.getDishes(new DishesRepository.GetCallback() {
            @Override
            public void onSuccess(List<Dish> dishes) {
                populateMainDishesUI(dishes);
            }

            @Override
            public void onError(String message) {
                Log.e("MAINDISHES", "Fel: " + message);
            }
        });
    }

    /**
     * Lägger till alla varmrätter på sidan för varmrätter
     *
     * @param dishes
     */
    private void populateMainDishesUI(List<Dish> dishes) {
        LinearLayout mainCourseView = requireView().findViewById(R.id.mainCourseLayout);
        OrderItemRow orderItemRow = new OrderItemRow();

        for (Dish dish : dishes) {
            int catId = dish.getDishCategoryId();
            //Lägger till alla med kategori 2 som är Varmrätter
            if (catId != 2) {
                continue;
            }
            mainCourseView.addView(orderItemRow.createItemRow(requireContext(), dish.getId(), dish.getName(), allMainCounters, quantityOrderedMainCourses));
        }
    }
}