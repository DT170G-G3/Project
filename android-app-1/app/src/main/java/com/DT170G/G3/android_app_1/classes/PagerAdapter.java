package com.DT170G.G3.android_app_1.classes;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.DT170G.G3.android_app_1.DessertFragment;
import com.DT170G.G3.android_app_1.DrinkFragment;
import com.DT170G.G3.android_app_1.MainFragment;
import com.DT170G.G3.android_app_1.StarterFragment;
import com.DT170G.G3.android_app_1.TableFragment;

public class PagerAdapter extends FragmentStateAdapter {

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch(position) {
            case 0:
                return new DrinkFragment();
            case 1:
                return new StarterFragment();
            case 2:
                return new MainFragment();
            case 3:
                return new DessertFragment();
            case 4:
                return new TableFragment();
            default:
                return new DrinkFragment();
        }
    }

    @Override
    public int getItemCount(){
        return 5;
    }

}
