package com.auto.gtcworkshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.auto.gtcworkshop.model.User;
import com.auto.gtcworkshop.repository.AuthentificationRepository;

public class AccountViewModel extends ViewModel {

    private AuthentificationRepository userRepository;
    private MutableLiveData<String> errorMessage;



    public AccountViewModel()
    {
        userRepository= AuthentificationRepository.getInstance();

        errorMessage = new MutableLiveData<>();
    }

    public void init()
    {
    }

    public LiveData<User> getUser()
    {return userRepository.getCurrentUser();}

    public boolean isValid(String email, String password)
    {
        if(email == null || email.isEmpty()) {
            return false;
        }
        if(password == null || password.isEmpty()) {
            return false;
        }
        return true;
    }


}
