package com.auto.gtcworkshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.auto.gtcworkshop.repository.AuthentificationRepository;
import com.google.firebase.auth.FirebaseUser;

public class LogInViewModel extends ViewModel {
    private AuthentificationRepository repository;
public LogInViewModel(){
    repository = AuthentificationRepository.getInstance();}



    public LiveData<FirebaseUser> getCurrentFirebaseUser() {
        return repository.getCurrentFirebaseUser();
    }



    public LiveData<String> getErrorMessage() {
        return repository.getErrorMessage();
    }

    public void attemptLogin(String email, String password) {
        repository.login(email, password);
    }
}







