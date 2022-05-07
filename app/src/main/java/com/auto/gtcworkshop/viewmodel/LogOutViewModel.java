package com.auto.gtcworkshop.viewmodel;

import androidx.lifecycle.ViewModel;

import com.auto.gtcworkshop.repository.AuthentificationRepository;

public class LogOutViewModel extends ViewModel {

    private AuthentificationRepository repository;

    public LogOutViewModel() {
        repository = AuthentificationRepository.getInstance();
    }

    public void logout(){
        repository.logOut();
    }


}
