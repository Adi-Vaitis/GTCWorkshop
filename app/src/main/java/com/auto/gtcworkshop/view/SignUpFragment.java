package com.auto.gtcworkshop.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.auto.gtcworkshop.model.User;
import com.google.firebase.firestore.FirebaseFirestore;

import com.auto.gtcworkshop.R;
import com.auto.gtcworkshop.viewmodel.SignUpViewModel;


public class SignUpFragment extends Fragment {


    private EditText rFullName, rEmail, rPassword, rPhone;
    private TextView rtocreate;
    private Button rCreateBtn;
    private ProgressBar progressBar;
    private FirebaseFirestore fstore;
    private String userID;
    private SignUpViewModel viewModel;
    private NavController navController;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(getActivity()).get(SignUpViewModel.class);
        initializeViews(view);
        setUpViews();
    }

    private void initializeViews(View view) {
        rFullName = view.findViewById(R.id.fullname);
        rEmail = view.findViewById(R.id.email);
        rPassword = view.findViewById(R.id.paswword);
        rPhone = view.findViewById(R.id.phone);
        rCreateBtn = view.findViewById(R.id.createbtn);
        rtocreate = view.findViewById(R.id.tologin);
        navController = Navigation.findNavController(view);
    }

    private void setUpViews() {
        rCreateBtn.setOnClickListener(view -> {
            try {
                viewModel.register(new User(rEmail.getText().toString() ,rPassword.getText().toString(), rFullName.getText().toString(), rPhone.getText().toString() ));
            } catch (Exception e) {
                e.printStackTrace();
            }
            navController.navigate(R.id.action_signUpFragment_to_accountFragment);

        });
        rtocreate.setOnClickListener(v -> {
            navController.navigate(R.id.action_signUpFragment_to_loginFragment);
        });

    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.reset();
    }
}

/*
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rFullName = view.findViewById(R.id.fullname);
        rEmail = view.findViewById(R.id.email);
        rPassword = view.findViewById(R.id.paswword);
        rPhone = view.findViewById(R.id.phone);
        rCreateBtn = view.findViewById(R.id.createbtn);
        rtocreate = view.findViewById(R.id.tologin);

        navController = Navigation.findNavController(view);

        rtocreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_signUpFragment_to_loginFragment);
            }
        });

        rCreateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = rEmail.getText().toString().trim();
                String password = rPassword.getText().toString().trim();
                String fullName = rFullName.getText().toString();
                String phone = rPhone.getText().toString();
// pot sa fac o metoda sa fac checkurile astea in repository ?!?!?!sau trebuiesc astea sa fie in repository ?
                if (TextUtils.isEmpty(email)) {
                    rEmail.setError("Email address is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    rPassword.setError("Password is required");
                    return;
                }
                if (password.length() < 6) {
                    rPassword.setError("The password must be longer than 6 characters");
                    return;

                }
                if (rPhone.length() < 8) {
                    rPhone.setError("The phone number must be at least 8 characters");
                    return;
                }


                viewModel.register(rFullName, rEmail,rPhone,rPassword);
//metoda de facut pentru a introduce full name si phone number in firestore

            }

        });


    }
*/

