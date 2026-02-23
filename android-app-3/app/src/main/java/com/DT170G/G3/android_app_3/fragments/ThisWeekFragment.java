package com.DT170G.G3.android_app_3.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.DT170G.G3.android_app_3.R;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThisWeekFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThisWeekFragment extends Fragment {

    public ThisWeekFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AddFragment.
     */
    public static ThisWeekFragment newInstance(String param1, String param2) {
        ThisWeekFragment fragment = new ThisWeekFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}