package com.auto.gtcworkshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.auto.gtcworkshop.viewmodel.MainViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private ImageView image;
    private TextView username;
    private TextView email;

    private NavController navController;
    private AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationDrawer;

    private MainViewModel viewModel;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        initializeLayout();
        setupNavigation();
        setupAuthentication();

    }

    private void initializeLayout() {
        navigationDrawer = findViewById(R.id.navigation_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        toolbar = findViewById(R.id.toolbar);

        View header = navigationDrawer.getHeaderView(0);

    }

    private void setupNavigation() {
        navController = Navigation.findNavController(this, R.id.main_activity_fragment);
        setSupportActionBar(toolbar);
        appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.accountFragment
                R.id.

        ).setOpenableLayout(drawerLayout).build();

        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navigationDrawer, navController);
        navigationDrawer.setNavigationItemSelectedListener(item -> {
            Bundle bundle = new Bundle();
            if (item.getItemId() == R.id.accountFragment) {
                bundle.putString("recipeType", "personal");}


            navController.navigate(item.getItemId(), bundle);
            drawerLayout.closeDrawers();
            return true;
        });
    }
    private void setupAuthentication() {
        viewModel.getCurrentFirebaseUser().observe(this, user -> {
            if (user == null) {
                navController.navigate(R.id.loginFragment);
            }
            //de schimbat din accountFragment in ce mm sa porneasca aplicatia
            navController.navigate(R.id.accountFragment);
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, appBarConfiguration) || super.onSupportNavigateUp();
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }









}