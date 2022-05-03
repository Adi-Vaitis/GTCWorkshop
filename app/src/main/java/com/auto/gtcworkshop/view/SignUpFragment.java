package com.auto.gtcworkshop.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.auto.gtcworkshop.R;
import com.google.firebase.auth.FirebaseAuth;


public class SignUpFragment extends Fragment {

   private EditText rFullName, rEmail, rPassword, rPhone;
    private TextView rtocreate;
    private Button rCreateBtn;
    private FirebaseAuth fAuth;
   private  ProgressBar progressBar;
    private FirebaseFirestore fstore;
     private String userID;
     private ProgressBar pBar;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }
}