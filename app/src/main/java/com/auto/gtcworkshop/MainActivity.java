package com.auto.gtcworkshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.auto.gtcworkshop.repository.Stage;
import com.auto.gtcworkshop.view.SignUpFragment;
import com.auto.gtcworkshop.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private Stage stage;

    private final SignUpFragment signUpFragment = new SignUpFragment();

    ProgressBar progressBar;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        setContentView(R.layout.activity_main);

        stage = new Stage();
        progressBar = findViewById(R.id.progressBar);


       /* viewModel.getProgressBar().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean isLoading) {
                int visibility = isLoading ? View.VISIBLE : View.INVISIBLE;
                progressBar.setVisibility(visibility);
            }
        });

        */

        viewModel.getCurrentUser().observe(this, user -> {
            Fragment fragment = null;
            if (user != null) {
                viewModel.init(user);
                stage.setStage("profile");
                getSupportFragmentManager().beginTransaction().replace(R.id.navigationFragment, signUpFragment).commit();
            } else {
                viewModel.getSignInPressed().observe(this, new Observer<Boolean>() {
                    @Override
                    public void onChanged(Boolean isSignInPressed) {
                        if (isSignInPressed) {
                            stage.setStage("login");
                            getSupportFragmentManager().beginTransaction().replace(R.id.navigationFragment, signUpFragment).commit();
                        } else {
                            stage.setStage("register");
                            getSupportFragmentManager().beginTransaction().replace(R.id.navigationFragment, signUpFragment).commit();
                        }
                    }
                });
            }
        });

        viewModel.getAuthenticationMessage().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String message) {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }


    }
