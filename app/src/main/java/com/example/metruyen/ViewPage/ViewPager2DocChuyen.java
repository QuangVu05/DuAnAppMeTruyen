package com.example.metruyen.ViewPage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.metruyen.Fagment.ChuongFragment;
import com.example.metruyen.Fagment.GioiThieuFragment;

public class ViewPager2DocChuyen extends FragmentStateAdapter {

    public ViewPager2DocChuyen(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new GioiThieuFragment();
            case 1:
                return new ChuongFragment();
            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
