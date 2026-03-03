package com.DT170G.G3.android_app_3.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

//import com.DT170G.G3.android_app_3.R;
import com.DT170G.G3.android_app_3.classes.SchemaRow;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StarterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StarterFragment extends Fragment {
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
/**
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_starter, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] starters = getResources().getStringArray(R.array.starterList);

        LinearLayout starterView = view.findViewById(R.id.starterLayout);

        OrderItemRow orderItemRow = new OrderItemRow();

        for(String starter : starters){
            starterView.addView(orderItemRow.createItemRow(requireContext(), starter));
        }

    }
    */
}