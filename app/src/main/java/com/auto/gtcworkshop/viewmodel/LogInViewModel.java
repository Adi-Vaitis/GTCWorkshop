package com.auto.gtcworkshop.viewmodel;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.auto.gtcworkshop.model.User;
import com.auto.gtcworkshop.repository.AuthentificationRepository;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.auth.FirebaseUser;

public class LogInViewModel extends ViewModel {
    private AuthentificationRepository repository;

    public LogInViewModel() {
        repository = AuthentificationRepository.getInstance();
    }

    public LiveData<FirebaseUser> getCurrentFirebaseUser() {
        return repository.getCurrentFirebaseUser();
    }

    public LiveData<String> getErrorMessage() {
        return repository.getErrorMessage();
    }

    public void attemptLogin(User userLog) {
        repository.login(userLog);
    }

    public void firebaseAuthWithGoogle(boolean isRegister, GoogleSignInAccount account, FragmentActivity activity) {
        repository.firebaseAuthWithGoogle(isRegister,account,activity);
    }
}







