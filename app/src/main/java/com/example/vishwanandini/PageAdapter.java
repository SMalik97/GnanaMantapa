package com.example.vishwanandini;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class PageAdapter extends FragmentPagerAdapter {
    ArrayList<Fragment>fragments=new ArrayList<>();
    ArrayList<String>tabTitles=new ArrayList<>();

    public void addFragments(Fragment fragments,String titles)
    {
        this.fragments.add(fragments);
        this.tabTitles.add(titles);
    }
   /* private int numOfTabs;
    PageAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.numOfTabs = numOfTabs;
    }*/


    public PageAdapter(FragmentManager fm)
    {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);
    }
    @Override
    public int getCount() {

        return fragments.size();
    }
    @Override
    public CharSequence getPageTitle(int position)
    {
        return tabTitles.get(position);
    }

}
