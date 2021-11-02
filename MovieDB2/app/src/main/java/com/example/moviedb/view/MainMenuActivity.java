package com.example.moviedb.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.moviedb.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainMenuActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;
    NavHostFragment navHostFragment;
    Toolbar toolbar;
    //  NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
//
//        toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
 //       bottomNavigationView = findViewById(R.id.bottom_nav);
        bottomNavigationView = findViewById(R.id.bottom_nav);
        navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.nav_fragment_main_menu);

        //    navController = Navigation.findNavController(MainMenuActivity.this, R.id.nav_fragment_main_menu);

        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.nowPlayingFragment, R.id.upComingFragment).build();

        NavigationUI.setupActionBarWithNavController(MainMenuActivity.this, navHostFragment.getNavController(), appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment.getNavController());
  // NavigationUI.setupActionBarWithNavController(bottomNavigationView, navHostFragment.getNavController());
    }
}