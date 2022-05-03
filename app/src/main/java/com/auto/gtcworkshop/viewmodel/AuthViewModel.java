package com.auto.gtcworkshop.viewmodel;

import android.app.Activity;
import android.app.Application;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.auto.gtcworkshop.repository.AuthentificationRepository;
import com.google.firebase.auth.FirebaseUser;

public class AuthViewModel extends AndroidViewModel {

    private AuthentificationRepository repository;
  //  private MutableLiveData<FirebaseUser> userData;
 //   private MutableLiveData<Boolean> loggedStatus;

 //   public MutableLiveData<FirebaseUser> getUserData() {
   //     return userData;
 //   }

 //   public MutableLiveData<Boolean> getLoggedstatus() {
 //       return loggedStatus;
 //   }

    public AuthViewModel(Application application) {
        super(application);
        repository = AuthentificationRepository.getInstance(application);
      //  userData = repository.getFirebaseUserMutableLiveData();
      //  loggedStatus = repository.getUserLoggedMutableLiveData();
    }

    public void register(Activity context, String email, String password) {
        repository.registerAccount(context, email, password);
    public void register(String fullName,String email, String phone,String password) {
        repository.register(fullName, email,phone, password);
    }

    public void setSignIn(Boolean signIn){
        repository.setSignIn(signIn);
    }

    public void forgotPass(View view){
        repository.forgotPassword(view);
    }

}
