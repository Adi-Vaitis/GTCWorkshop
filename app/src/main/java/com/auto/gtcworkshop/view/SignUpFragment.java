package com.auto.gtcworkshop.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.auto.gtcworkshop.R;
import com.auto.gtcworkshop.model.User;
import com.auto.gtcworkshop.viewmodel.SignUpViewModel;


public class SignUpFragment extends Fragment {


    private EditText rFullName, rEmail, rPassword, rPhone;
    private TextView rToCreate;
    private Button rCreateBtn;
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
        rToCreate = view.findViewById(R.id.tologin);
        navController = Navigation.findNavController(view);
    }

    private void setUpViews() {
        rCreateBtn.setOnClickListener(view -> {
            viewModel.register(new User(rEmail.getText().toString(), rPassword.getText().toString(), rFullName.getText().toString(), rPhone.getText().toString()));
            navController.navigate(R.id.action_signUpFragment_to_accountFragment);
            Toast.makeText(getActivity(), "Account registered successfully!", Toast.LENGTH_SHORT).show();
            rToCreate.setOnClickListener(v -> {
                navController.navigate(R.id.action_signUpFragment_to_loginFragment);
            });

        });
    }

    @Override
    public void onStop() {
        super.onStop();
        viewModel.reset();
    }
}


