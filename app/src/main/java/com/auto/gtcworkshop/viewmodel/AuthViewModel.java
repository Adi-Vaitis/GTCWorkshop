package com.auto.gtcworkshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.auto.gtcworkshop.repository.AuthentificationRepository;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends AndroidViewModel {

    private AuthentificationRepository repository;
    private MutableLiveData<FirebaseUser> userData;
    private MutableLiveData<Boolean> loggedstatus;

    public MutableLiveData<FirebaseUser> getUserData() {
        return userData;
    }

    public MutableLiveData<Boolean> getLoggedstatus() {
        return loggedstatus;
    }

    public AuthViewModel(@NonNull Application application) {
        super(application);
        repository = new AuthentificationRepository(application);
        userData = repository.getFirebaseUserMutableLiveData();
        loggedstatus = repository.getUserLoggedMutableLiveData();
    }

    public void register(String email, String password) {
        repository.register(email, password);
    }

    public void login(String email, String password) {
        repository.login(email, password);
    }

    public void logout() {
        repository.logout();
    }


}