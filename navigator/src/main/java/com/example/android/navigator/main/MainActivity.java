package com.example.android.navigator.main;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.navigator.Database.HomeFragment;
import com.example.android.navigator.firebaseCloudMessaging.FirebaseFragment;
import com.example.android.navigator.R;
import com.example.android.navigator.serviceClass.ServiceFragment;
import com.example.android.navigator.databinding.UserBindingFragment;
import com.example.android.navigator.model.DataModel;
import com.example.android.navigator.networking.JSONFragment;
import com.example.android.navigator.networking.WeatherFragment;
import com.example.android.navigator.notification.BroadcastFragment;
import com.example.android.navigator.recycler.MoviesFragment;
import com.example.android.navigator.scheduler.SchedulerFragment;
import com.example.android.navigator.setting.SettingsFragment;
import com.example.android.navigator.web.PicturesFragment;


public class MainActivity extends AppCompatActivity implements FirebaseFragment.OnFragmentInteractionListener,ServiceFragment.OnFragmentInteractionListener,BroadcastFragment.OnFragmentInteractionListener,SchedulerFragment.OnFragmentInteractionListener,WeatherFragment.OnFragmentInteractionListener,UserBindingFragment.OnFragmentInteractionListener, HomeFragment.OnFragmentInteractionListener, PicturesFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener, JSONFragment.OnFragmentInteractionListener {

    private static MainActivity mMainContext;
    Toolbar toolbar;
    android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;
    private String[] mNavigationDrawerItemTitles;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;

    public static MainActivity getmMainContext() {
        return mMainContext;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMainContext = this;
        mDrawerTitle = getTitle();
        mTitle = mDrawerTitle;

        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_item_drawer_name);
        mDrawerLayout = findViewById(R.id.drawable);
        mDrawerList = findViewById(R.id.left_drawer);
        setupToolbar();

        DataModel[] drawerItem = new DataModel[11];
        drawerItem[0] = new DataModel(R.drawable.home, "Home");
        drawerItem[1] = new DataModel(R.drawable.movies, "Movies");
        drawerItem[2] = new DataModel(R.drawable.pictures, "Pictures");
        drawerItem[3] = new DataModel(R.drawable.set, "Settings");
        drawerItem[4] = new DataModel(R.drawable.user, "Profile");
        drawerItem[5] = new DataModel(R.drawable.json, "Json");
        drawerItem[6] = new DataModel(R.drawable.weather,"Weather");
        drawerItem[7] = new DataModel(R.drawable.scheduler,"Scheduler");
        drawerItem[8] = new DataModel(R.drawable.notification,"Broadcast");
        drawerItem[9] = new DataModel(R.drawable.services,"Service");
        drawerItem[10] = new DataModel(R.drawable.firebase,"Firebase");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        DrawItemCustomAdapter adapter = new DrawItemCustomAdapter(this, R.layout.list_item_view_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        mDrawerLayout = findViewById(R.id.drawable);
        if (savedInstanceState == null)
            selectItem(0);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        setupDrawerToggle();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.tool_menu, menu);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    private void selectItem(int position) {
        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new HomeFragment();
                break;
            case 1:
                fragment = new MoviesFragment();
                break;
            case 2:
                fragment = new PicturesFragment();
                break;
            case 3:
                fragment = new SettingsFragment();
                break;
            case 4:
                fragment = new UserBindingFragment();
                break;
            case 5:
                fragment = new JSONFragment();
                break;
            case 6:
                fragment = new WeatherFragment();
                break;
            case 7:
                fragment = new SchedulerFragment();
                break;
            case 8:
                fragment = new BroadcastFragment();
                break;
            case 9:
                fragment = new ServiceFragment();
                break;
            case 10:
                fragment= new FirebaseFragment();
                break;

            default:
                fragment = new HomeFragment();
                break;
        }

        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).addToBackStack(null).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            mDrawerLayout.closeDrawer(mDrawerList);


        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.tool_settings:
                selectItem(3);
                break;
            case R.id.tool_profile:
                selectItem(4);
                break;
            case R.id.tool_home:
                selectItem(0);
                break;
        }

        if (mDrawerToggle.onOptionsItemSelected(item)) {

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Navigator");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    void setupDrawerToggle() {
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    @Override
    public void onBackPressed() {
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if (fragment instanceof HomeFragment) {
            if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
            } else {
                mDrawerLayout.openDrawer(GravityCompat.START);
            }
        } else
            super.onBackPressed();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);

        }

    }
}
