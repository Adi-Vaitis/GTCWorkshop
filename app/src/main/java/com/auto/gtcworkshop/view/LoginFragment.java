package com.auto.gtcworkshop.view;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.auto.gtcworkshop.R;
import com.auto.gtcworkshop.viewmodel.AuthViewModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginFragment extends Fragment {

        public static final int GOOGLE_SIGN_IN_CODE = 1005;
        public static final int REQUEST_CODE = 10005;
        EditText lEmail, lPassword;
        Button lLoginBtn;
        ImageView lgoogleBtn, lfbBtn;
        TextView ltocreate, forgotLink;
        ProgressBar pBar;
        private AuthViewModel viewModel;
        private NavController navController;

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            viewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(AuthViewModel.class);
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
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            lEmail = view.findViewById(R.id.email);
            lPassword = view.findViewById(R.id.paswword);

            lLoginBtn = view.findViewById(R.id.loginbtn);
            pBar = view.findViewById(R.id.progressBar);
            ltocreate = view.findViewById(R.id.toRegister);



            navController = Navigation.findNavController(view);

            ltocreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    navController.navigate(R.id.action_loginFragment_to_signUpFragment);
                }
            });

lLoginBtn.setOnClickListener(new View.OnClickListener()
       {
            @Override
            public void onClick(View v) {
                String email = lEmail.getText().toString().trim();
                String password = lPassword.getText().toString().trim();

// pot sa fac o metoda sa fac checkurile astea in repository ?!?!?!sau trebuiesc astea sa fie in repository ?
                if (TextUtils.isEmpty(email)) {
                    lEmail.setError("Email address is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    lEmail.setError("Password is required");
                    return;
                }
                if (password.length() < 6) {
                    lPassword.setError("The password must be longer than 6 characters");
                    return;
                }

                viewModel.register(email, password);


            }

        });

        }
            @Override


            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }
}