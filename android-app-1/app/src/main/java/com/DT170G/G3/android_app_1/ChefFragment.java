package com.DT170G.G3.android_app_1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.DT170G.G3.android_app_1.classes.Order;
import com.DT170G.G3.android_app_1.classes.FoodAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChefFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChefFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChefFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChefFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChefFragment newInstance(String param1, String param2) {
        ChefFragment fragment = new ChefFragment();
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
        return inflater.inflate(R.layout.fragment_chef, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.chefRecyclerView);

        String tablename1 = "bord 2";
        String tablename2 = "bord 5";

        List<String> order1 = new ArrayList<>();
        order1.add("kött");
        order1.add("fisk");

        List<String> order2 = new ArrayList<>();
        order2.add("soppa");
        order2.add("veg");

        Order chefOrder1 = new Order(tablename1, order1);
        Order chefOrder2 = new Order(tablename2, order2);

        List<Order> allOrders = new ArrayList<>();
        allOrders.add(chefOrder1);
        allOrders.add(chefOrder2);

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        FoodAdapter orderAdapter = new FoodAdapter(allOrders);
        recyclerView.setAdapter(orderAdapter);
    }
}