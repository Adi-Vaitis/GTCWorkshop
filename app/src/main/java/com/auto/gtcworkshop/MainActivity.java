package com.auto.gtcworkshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import android.os.Bundle;
import android.view.View;
import android.widget.Toolbar;

import com.auto.gtcworkshop.viewmodel.MainViewModel;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private NavigationView navigationDrawer;
    private DrawerLayout drawerLayout;
    private MainViewModel viewModel;
    private Toolbar toolbar;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navdrawer);

        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

        initializeLayout();
        setupNavigation();
        setupAuthentication();
        }

    private void initializeLayout()
    {
        navigationDrawer = findViewById(R.id.nav);
        drawerLayout = findViewById(R.id.navd);
        toolbar = findViewById(R.id.toolbar);

        View header = navigationDrawer.getHeaderView(0);



    }

    }

   /*private void replaceFragment (Fragment fragment)
   {
       FragmentManager fragmentManager= getSupportFragmentManager();
       FragmentTransaction fragmentTransaction = fragmentManager.beginTrasnsaction();
       fragmentTransaction.replace(R.id.frameLayout, fragment);
       fragmentTransaction.replace(R.id.drawer_layout, fragment);
       fragmentTransaction.commit();}*/




   }



