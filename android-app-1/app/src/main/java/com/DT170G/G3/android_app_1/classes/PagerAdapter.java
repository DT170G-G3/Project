package com.DT170G.G3.android_app_1.classes;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.DT170G.G3.android_app_1.fragments.DessertFragment;
import com.DT170G.G3.android_app_1.fragments.DrinkFragment;
import com.DT170G.G3.android_app_1.fragments.MainCourseFragment;
import com.DT170G.G3.android_app_1.fragments.StarterFragment;
import com.DT170G.G3.android_app_1.fragments.TableFragment;

public class PagerAdapter extends FragmentStateAdapter {
    private DrinkFragment drinkFragment = new DrinkFragment();
    private TableFragment tableFragment = new TableFragment();
    private StarterFragment starterFragment = new StarterFragment();
    private MainCourseFragment mainCourseFragment = new MainCourseFragment();
    private DessertFragment dessertFragment = new DessertFragment();

    public PagerAdapter(@NonNull FragmentActivity fragmentActivity){
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position){
        switch(position) {
            case 0:
                return tableFragment;
            case 1:
                return drinkFragment;
            case 2:
                return starterFragment;
            case 3:
                return mainCourseFragment;
            case 4:
                return dessertFragment;
            default:
                return tableFragment;
        }
    }

    @Override
    public int getItemCount(){
        return 5;
    }

    public TableFragment getTableFragment() {
        return tableFragment;
    }

    public DrinkFragment getDrinkFragment() {
        return drinkFragment;
    }

    public StarterFragment getStarterFragment() {
        return starterFragment;
    }

    public MainCourseFragment getMainCourseFragment() {
        return mainCourseFragment;
    }

    public DessertFragment getDessertFragment() {
        return dessertFragment;
    }

}
