package com.DT170G.G3.android_app_1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.DT170G.G3.android_app_1.classes.OrderItemRow;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MainCourseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MainCourseFragment extends Fragment {
    public MainCourseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ChefFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MainCourseFragment newInstance(String param1, String param2) {
        MainCourseFragment fragment = new MainCourseFragment();
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
        return inflater.inflate(R.layout.fragment_main_course, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] mainCourses = getResources().getStringArray(R.array.mainCourseList);

        LinearLayout mainCourseView = view.findViewById(R.id.mainCourseLayout);

        OrderItemRow orderItemRow = new OrderItemRow();

        for(String mainCourse : mainCourses){
            mainCourseView.addView(orderItemRow.createItemRow(requireContext(), mainCourse));
        }

    }

}