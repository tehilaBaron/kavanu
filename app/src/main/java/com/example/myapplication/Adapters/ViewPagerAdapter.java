package com.example.myapplication.Adapters;

import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.example.myapplication.Fragments.ContactUsFragment;
import com.example.myapplication.Fragments.MapFragment;
import com.example.myapplication.Fragments.PriceListFragment;
import com.example.myapplication.Fragments.workHoursFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new PriceListFragment();
            case 1:
                return new workHoursFragment();
            case 2:
                return new MapFragment();
            case 3:
                return new ContactUsFragment();
            default:
                return new PriceListFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
