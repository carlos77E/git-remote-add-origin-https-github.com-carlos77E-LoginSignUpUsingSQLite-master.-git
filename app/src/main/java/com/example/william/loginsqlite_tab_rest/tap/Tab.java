package com.example.william.loginsqlite_tab_rest.tap;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.william.loginsqlite_tab_rest.R;
import com.example.william.loginsqlite_tab_rest.activities.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class Tab extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;


    public static int simple=android.R.layout.simple_list_item_1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);



        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.salir:
                Intent i = new Intent(this,LoginActivity.class);
                startActivity(i);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }
//TARE TAPS DINAMICOS con escrolll y  coneccion a base de datos
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).

            switch (position){
                case 0:
                    Tab01User tab1 = new Tab01User();
                    return tab1;
                case 1:
                    Tab02Favotites tab2 = new Tab02Favotites();
                    return tab2;
                case 2:
                    Tab03Contacts tab3 = new Tab03Contacts();
                    return tab3;

                default:
                    return null;
            }
            //return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
        List<String>list  =new ArrayList<>();


        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "USUARIO";
                case 1:
                    return "FAVORITES";
                case 2:
                    return "CONTACTS";

            }
            return null;
        }
    }
}
