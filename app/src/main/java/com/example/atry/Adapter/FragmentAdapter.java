package com.example.atry.Adapter;

import com.example.atry.Fragments.callsFragment;
import com.example.atry.Fragments.chatsFragment;
import com.example.atry.Fragments.statusFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class FragmentAdapter extends FragmentPagerAdapter {

    public FragmentAdapter(@NonNull @org.jetbrains.annotations.NotNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Fragment getItem(int position) {

        switch(position){

            case 0:return new chatsFragment();
            case 1:return new statusFragment();
            case 2:return new callsFragment();

            default :return new chatsFragment();

        }

    }
//7275353014
    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        String title=null;

        if(position==0){
            title="CHATS";
        }

        if(position==1){
            title="STATUS";
        }

        if(position==2){
            title="CALLS";
        }

        return  title;
    }
}
