package com.auto.gtcworkshop.viewmodel;

import android.app.Application;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.auto.gtcworkshop.model.User;
import com.auto.gtcworkshop.repository.AuthentificationRepository;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class SignUpViewModel extends ViewModel {

    private final AuthentificationRepository repository;

    public SignUpViewModel() {
        repository = AuthentificationRepository.getInstance();
    }

    public void register(User user) {
        repository.register(user);
    }

    public void firebaseAuthWithGoogle(boolean isRegister, GoogleSignInAccount account, FragmentActivity activity) {
        repository.firebaseAuthWithGoogle(isRegister,account,activity);
    }

    public void reset() {

    }


}
