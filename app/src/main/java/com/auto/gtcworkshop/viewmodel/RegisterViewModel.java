package com.auto.gtcworkshop.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.auto.gtcworkshop.model.User;
import com.auto.gtcworkshop.repository.AuthentificationRepository;
import com.google.firebase.auth.FirebaseUser;

public class RegisterViewModel extends ViewModel {

    private AuthentificationRepository repository;


    public RegisterViewModel()
    {this.repository = AuthentificationRepository.getInstance();}

}
