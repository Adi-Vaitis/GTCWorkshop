package com.auto.gtcworkshop.view;

import android.content.Intent;
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

import com.auto.gtcworkshop.R;
import com.auto.gtcworkshop.model.User;
import com.auto.gtcworkshop.viewmodel.LogInViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class LoginFragment extends Fragment {

    public static final int GOOGLE_SIGN_IN_CODE = 1005;
    public static final int REQUEST_CODE = 10005;
    private GoogleSignInClient googleSignInClient;
    EditText lEmail, lPassword;
    Button lLoginBtn;
    ImageView lGoogleBtn, lFbBtn;
    TextView lToCreate, forgotLink;
    ProgressBar pBar;
    private LogInViewModel viewModel;
    private NavController navController;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        createRequestGoogle();
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
        forgotLink = view.findViewById(R.id.forgetpass);
        lGoogleBtn = view.findViewById(R.id.lgooglebtn);
        lFbBtn = view.findViewById(R.id.lfbbtn);
    }


    private void setupViews() {
        lLoginBtn.setOnClickListener(v -> {
            viewModel.attemptLogin(new User(lEmail.getText().toString(), lPassword.getText().toString()));
            Toast.makeText(getActivity(), "Logged In", Toast.LENGTH_SHORT).show();
            navController.navigate(R.id.action_loginFragment_to_navi_home);
        });

        lToCreate.setOnClickListener(v -> {
            navController.navigate(R.id.action_loginFragment_to_signUpFragment);
        });

        forgotLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Will be implemented in the future", Toast.LENGTH_SHORT).show();
            }
        });

        lGoogleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
                navController.navigate(R.id.action_loginFragment_to_navi_home);
            }
        });
        lFbBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Will be implemented in the future", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == GOOGLE_SIGN_IN_CODE)
        {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            }
            catch (ApiException e)
            {
                Toast.makeText(getContext(),e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account) {
        viewModel.firebaseAuthWithGoogle(false,account,getActivity());
    }

    public void createRequestGoogle()
    {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                requestIdToken(getString(com.firebase.ui.auth.R.string.default_web_client_id)).
                requestEmail().
                build();

        googleSignInClient = GoogleSignIn.getClient(getContext(),gso);
    }

    private void logIn()
    {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, GOOGLE_SIGN_IN_CODE);
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}












