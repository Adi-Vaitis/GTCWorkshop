package com.auto.gtcworkshop.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import com.auto.gtcworkshop.model.User;
import com.auto.gtcworkshop.repository.AuthentificationRepository;

public class SignUpViewModel extends AndroidViewModel {

    private final AuthentificationRepository repository;

    public SignUpViewModel(Application application)
    {super(application);
    repository = AuthentificationRepository.getInstance();
    }
    public void register(String fullName, String email, String phone, String password) throws Exception {
        repository.register(new User(fullName, email,phone, password));
    }

    public void reset(){

    }


}
