package com.DT170G.G3.android_app_3.classes;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;


/**
import com.DT170G.G3.android_app_3.fragments.DessertFragment;
import com.DT170G.G3.android_app_3.fragments.DrinkFragment;
import com.DT170G.G3.android_app_3.fragments.MainCourseFragment;
import com.DT170G.G3.android_app_3.fragments.StarterFragment;
import com.DT170G.G3.android_app_3.fragments.TableFragment;

public class PagerAdapter extends FragmentStateAdapter {

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch(position) {
            case 0:
                return new TableFragment();
            case 1:
                return new DrinkFragment();
            case 2:
                return new StarterFragment();
            case 3:
                return new MainCourseFragment();
            case 4:
                return new DessertFragment();
            default:
                return new TableFragment();
        }
    }

    @Override
    public int getItemCount(){
        return 5;
    }


}
*/