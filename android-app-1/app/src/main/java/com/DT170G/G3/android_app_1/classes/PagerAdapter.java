package com.DT170G.G3.android_app_1.classes;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.DT170G.G3.android_app_1.DessertFragment;
import com.DT170G.G3.android_app_1.DrinkFragment;
import com.DT170G.G3.android_app_1.MainCourseFragment;
import com.DT170G.G3.android_app_1.R;
import com.DT170G.G3.android_app_1.StarterFragment;
import com.DT170G.G3.android_app_1.TableFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

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
