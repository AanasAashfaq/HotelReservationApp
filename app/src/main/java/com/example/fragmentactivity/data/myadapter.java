package com.example.fragmentactivity.data;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.fragmentactivity.loginFragment;
import com.example.fragmentactivity.signupFragment;

public class myadapter extends FragmentStateAdapter {

    public myadapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if(position == 0)
        {
            return new loginFragment();
        }
        else
        {
            return new signupFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
