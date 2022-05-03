package com.auto.gtcworkshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.auto.gtcworkshop.repository.Stage;
import com.auto.gtcworkshop.view.SignUpFragment;

public class MainActivity extends AppCompatActivity {

    private Stage stage;

    private final SignUpFragment signUpFragment = new SignUpFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_login);


    }
}