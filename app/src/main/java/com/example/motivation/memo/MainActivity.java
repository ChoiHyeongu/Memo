package com.example.motivation.memo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Fragment listFragment;
    Fragment memoFragment;
    Fragment importantFragment;
    Fragment trashFragment;

    MenuItem writeButton;
    MenuItem deleteButton;

    private long pressedTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("List");
        setSupportActionBar(toolbar);

        listFragment = new ListFragment();
        memoFragment = new MemoFragment();
        importantFragment = new ImportantFragment();
        trashFragment = new TrashFragment();

        getSupportFragmentManager().beginTransaction().add(R.id.maincontent_container, listFragment).commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        final NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSupportFragmentManager().beginTransaction().replace(R.id.maincontent_container, memoFragment).addToBackStack(null).commit();
                navigationView.setCheckedItem(R.id.nav_write);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        writeButton = menu.findItem(R.id.action_save);
        deleteButton = menu.findItem(R.id.action_delete);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_all) {
            getSupportFragmentManager().beginTransaction().replace(R.id.maincontent_container, listFragment).addToBackStack(null).commit();
            getSupportActionBar().setTitle(R.string.List);
            writeButton.setVisible(false);
            deleteButton.setVisible(false);
        } else if (id == R.id.nav_important) {
            getSupportFragmentManager().beginTransaction().replace(R.id.maincontent_container, importantFragment).addToBackStack(null).commit();
            getSupportActionBar().setTitle(R.string.Important);
            writeButton.setVisible(false);
            deleteButton.setVisible(true);
        } else if (id == R.id.nav_trash) {
            getSupportFragmentManager().beginTransaction().replace(R.id.maincontent_container, trashFragment).addToBackStack(null).commit();
            getSupportActionBar().setTitle(R.string.action_trash);
            writeButton.setVisible(false);
            deleteButton.setVisible(true);
        } else if (id == R.id.nav_write){
            getSupportFragmentManager().beginTransaction().replace(R.id.maincontent_container, memoFragment).addToBackStack(null).commit();
            getSupportActionBar().setTitle(R.string.Untitled);
            writeButton.setVisible(true);
            deleteButton.setVisible(false);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public interface onBackPressedListener {
        public void onBack();
    }

    private onBackPressedListener mBackListener;

    public void setOnBackPressedListener(onBackPressedListener listener) {
        mBackListener = listener;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }

        if (mBackListener != null) {
            if (mBackListener != null) {
                mBackListener.onBack();
            } else {
                if (getSupportFragmentManager().getBackStackEntryCount() == 0) {
                    Toast.makeText(this, "종료하려면 한 번 더 누르세요.", Toast.LENGTH_SHORT).show();
                } else {
                    super.onBackPressed();
                }
            }

        }
    }
}