package com.auto.gtcworkshop.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.auto.gtcworkshop.repository.AuthentificationRepository;
import com.google.firebase.auth.FirebaseUser;

public class MainActivityViewModel extends AndroidViewModel {
    private final AuthentificationRepository authentificationRepository;

    public MainActivityViewModel(Application app)
    {
        super(app);
        authentificationRepository = AuthentificationRepository.getInstance(app);
    }

    public void init(FirebaseUser user)
    {
        String userId = user.getUid();
    }

    public LiveData<FirebaseUser> getCurrentUser()
    {
        return authentificationRepository.getCurrentUser();
    }

    public LiveData<String> getAuthenticationMessage()
    {
        return authentificationRepository.getAuthenticationMessage();
    }


    public MutableLiveData<Boolean> verifiedEmail()
    {
        return authentificationRepository.verifiedEmail();
    }

    public LiveData<Boolean> getProgressBar()
    {
        return authentificationRepository.getProgressBar();
    }

    public LiveData<Boolean> getSignInPressed()
    {
        return authentificationRepository.getSignIn();
    }

}
