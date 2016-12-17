package com.mwaqaspervez.burgerchamp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);

        viewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));

        tabs.setupWithViewPager(viewPager);

        tabs.setBackgroundColor(ContextCompat.getColor(this,R.color.Chocolate));
        tabs.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.Chocolate));
        tabs.setTabTextColors(ContextCompat.getColor(this, R.color.white), ContextCompat.getColor(this, R.color.GreenYellow));

        tabs.getTabAt(0).setText("Deals");
        tabs.getTabAt(1).setText("Menu");
    }


     private class PagerAdapter extends FragmentPagerAdapter {


        PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
         public Fragment getItem(int position) {
            switch (position) {

                case 0:
                    return new DealsActivity().newInstance();
                case 1:
                    return new ItemsActivity().newInstance();

            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }
}
