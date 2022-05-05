package com.auto.gtcworkshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.auto.gtcworkshop.repository.AuthentificationRepository;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityViewModel extends ViewModel {

    private AuthentificationRepository authentificationRepository;

    public MainActivityViewModel(){
        super();
        authentificationRepository = AuthentificationRepository.getInstance();
    }

    public LiveData<FirebaseUser> getCurrentUser(){
        return authentificationRepository.
    }
}
