package com.example.metruyen.ViewPage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.metruyen.Fagment.DanhdauFragment;
import com.example.metruyen.Fagment.Lich_suFragment;
import com.example.metruyen.Fagment.tutruyen;

public class ViewPager2tutruyenAdapter extends FragmentStateAdapter {
    public ViewPager2tutruyenAdapter(@NonNull tutruyen fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new Lich_suFragment();
            case 1:
                return new DanhdauFragment();
            default:
                return null;
        }

    }

    @Override
    public int getItemCount() {
        return 2;
    }

}
