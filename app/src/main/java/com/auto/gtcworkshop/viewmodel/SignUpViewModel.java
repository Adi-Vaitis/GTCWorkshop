package com.auto.gtcworkshop.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.auto.gtcworkshop.repository.AuthentificationRepository;

public class SignUpViewModel extends AndroidViewModel {

    private final AuthentificationRepository userRepository;

    public RegisterViewModel(Application application)
    {super(application);
    userRepository = AuthentificationRepository.getInstance(application);
    }
    public void register(String email, String password)
    {userRepository.register(email,password);}



}
