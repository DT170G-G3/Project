package com.DT170G.G3.android_app_3.classes;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


import com.DT170G.G3.android_app_3.fragments.NextWeekFragment;
import com.DT170G.G3.android_app_3.fragments.ThisWeekFragment;

public class PagerAdapter extends FragmentStateAdapter {

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch(position) {
            case 0:
                return new ThisWeekFragment();
            case 1:
                return new NextWeekFragment();
            default:
                return new ThisWeekFragment();
        }
    }

    @Override
    public int getItemCount(){
        return 2;
    }


}
