package com.auto.gtcworkshop.view;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.auto.gtcworkshop.R;
import com.auto.gtcworkshop.viewmodel.AuthViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUpFragment extends Fragment {

   /* private EditText rFullName, rEmail, rPassword, rPhone;
    private TextView rtocreate;
    private Button rCreateBtn;
    private ProgressBar progressBar;
    private String userID;
    private ProgressBar pBar;
    private AuthViewModel viewModel;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this, (ViewModelProvider.Factory) ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);
        viewModel.getUserData().observe(this, new Observer<FirebaseUser>() {
            @Override
            public void onChanged(FirebaseUser firebaseUser) {
                if (firebaseUser != null) {
                    navController.navigate(R.id.action_loginFragment_to_accountFragment);


                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rFullName = view.findViewById(R.id.fullname);
        rEmail = view.findViewById(R.id.email);
        rPassword = view.findViewById(R.id.paswword);
        rPhone = view.findViewById(R.id.phone);
        rCreateBtn = view.findViewById(R.id.createbtn);
        pBar = view.findViewById(R.id.progressBar);
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
                viewModel.register(fullName, email, password, phone);


            }

        });


    }

    */

    private AuthViewModel authViewModel;

    EditText fullName;
    EditText email;
    EditText phoneNo;
    EditText password;
    Button registerButton;
    Button toLogInButton;
    TextView forgotPass;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        fullName = view.findViewById(R.id.fullname);
        email = view.findViewById(R.id.email);
        phoneNo = view.findViewById(R.id.phone);
        password = view.findViewById(R.id.paswword);
        registerButton = view.findViewById(R.id.createbtn);
        toLogInButton = view.findViewById(R.id.tologin);
        forgotPass = view.findViewById(R.id.forgetpass);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        authViewModel = new ViewModelProvider(this).get(AuthViewModel.class);

        if (registerButton != null) {
            registerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getView() != null) {
                        authViewModel.register((Activity) getView().getContext(), email.getText().toString(),
                                password.getText().toString());
                    }
                }
            });
        }
        if(toLogInButton != null)
        {
            toLogInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    authViewModel.setSignIn(true);
                }
            });
        }

        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authViewModel.forgotPass(view);
            }
        });
    }
}