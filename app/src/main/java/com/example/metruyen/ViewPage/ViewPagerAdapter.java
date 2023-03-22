package com.example.metruyen.ViewPage;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.metruyen.Fagment.caidat;
import com.example.metruyen.Fagment.trangchu;
import com.example.metruyen.Fagment.tutruyen;

public class ViewPagerAdapter  extends FragmentStateAdapter {

   public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
      super(fragmentActivity);
   }

   @NonNull
   @Override
   public Fragment createFragment(int position) {
      switch (position) {
         case 0:
            return new tutruyen();

         case 1:
            return new trangchu();
         case 2:
            return new caidat();
         default:
            return null;
      }
   }


   @Override
   public int getItemCount() {
      return 3;
   }


}


