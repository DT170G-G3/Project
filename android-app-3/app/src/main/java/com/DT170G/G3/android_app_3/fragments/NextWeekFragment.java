package com.DT170G.G3.android_app_3.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.DT170G.G3.android_app_3.R;
import com.DT170G.G3.android_app_3.classes.SchemaRow;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NextWeekFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NextWeekFragment extends Fragment {

    public NextWeekFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddFragment.
     */
    public static NextWeekFragment newInstance(String param1, String param2) {
        NextWeekFragment fragment = new NextWeekFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.next_week, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] weekdays = getResources().getStringArray(R.array.weekdays);
        String[] morningStaff = getResources().getStringArray(R.array.morningteam2);
        String[] afternoonStaff = getResources().getStringArray(R.array.afternoonteam2);

        LinearLayout nextView = view.findViewById(R.id.nextWeekLayout);

        SchemaRow row = new SchemaRow();

        for(String weekday : weekdays) {
            nextView.addView(row.createSchemaRow(requireContext(), weekday, morningStaff, afternoonStaff));
        }
    }

}