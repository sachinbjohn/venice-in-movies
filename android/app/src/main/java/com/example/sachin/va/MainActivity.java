package com.example.sachin.va;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;


public class MainActivity extends ActionBarActivity  {

    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.my_pager);
        MapFragment.locID=getIntent().getStringExtra("lid");
        viewPager.setAdapter(new MyAdapter(getSupportFragmentManager()));

        slidingTabLayout = (SlidingTabLayout)findViewById(R.id.sliding_tabs);
        slidingTabLayout.setViewPager(viewPager);
    }
    public static class MyAdapter extends FragmentPagerAdapter {
        private static final int FRAGMENT_1 = 0;
        private static final int FRAGMENT_2 = 1;
        private static final int FRAGMENT_3 = 2;

        public MyAdapter (FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            switch (i){
                case FRAGMENT_1: return new MapFragment();
                case FRAGMENT_2 : return new MovieSearchFragment();
                case FRAGMENT_3 : return new LocationSearchFragment();

            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case FRAGMENT_2 : return "Movies";
                case FRAGMENT_3 : return "Locations";
                case FRAGMENT_1 : return "Map";
            }
            return super.getPageTitle(position);
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}