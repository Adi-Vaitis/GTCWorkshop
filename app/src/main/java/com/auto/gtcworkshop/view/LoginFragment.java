package com.auto.gtcworkshop.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.auto.gtcworkshop.DrawerLocker;
import com.auto.gtcworkshop.R;
import com.auto.gtcworkshop.model.User;
import com.auto.gtcworkshop.viewmodel.LogInViewModel;

public class LoginFragment extends Fragment {

    public static final int GOOGLE_SIGN_IN_CODE = 1005;
    public static final int REQUEST_CODE = 10005;
    EditText lEmail, lPassword;
    Button lLoginBtn;
    ImageView lGoogleBtn, lFbBtn;
    TextView lToCreate, forgotLink;
    ProgressBar pBar;
    private LogInViewModel viewModel;
    private NavController navController;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = new ViewModelProvider(getActivity()).get(LogInViewModel.class);
        navController = Navigation.findNavController(view);

        initializeViews(view);
        setupViews();
    }

    private void initializeViews(View view) {
        lEmail = view.findViewById(R.id.email);
        lPassword = view.findViewById(R.id.paswword);
        lLoginBtn = view.findViewById(R.id.loginbtn);
        pBar = view.findViewById(R.id.progressBar);
        lToCreate = view.findViewById(R.id.toRegister);
    }


    private void setupViews() {
        lLoginBtn.setOnClickListener(v -> {
            viewModel.attemptLogin(new User(lEmail.getText().toString(), lPassword.getText().toString()));
            Toast.makeText(getActivity(), "Logged In", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.action_loginFragment_to_navi_contactus);
        });

        lToCreate.setOnClickListener(v -> {
            navController.navigate(R.id.action_loginFragment_to_signUpFragment);
        });

    }

@Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    ((DrawerLocker) getActivity()).setDrawerEnabled(false);
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
        ((DrawerLocker) getActivity()).setDrawerEnabled(false);
    }
}












