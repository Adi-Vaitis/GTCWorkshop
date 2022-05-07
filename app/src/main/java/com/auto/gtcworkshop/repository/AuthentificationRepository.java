package com.auto.gtcworkshop.repository;

import android.app.Application;
import android.text.TextUtils;
import android.widget.Toast;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.auto.gtcworkshop.livedata.FirebaseUserLiveData;
import com.auto.gtcworkshop.livedata.UserLiveData;
import com.auto.gtcworkshop.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.auto.gtcworkshop.livedata.FirebaseUserLiveData;
import com.auto.gtcworkshop.livedata.UserLiveData;
import com.auto.gtcworkshop.model.User;


public class AuthentificationRepository {
    private static AuthentificationRepository instance;

    private FirebaseAuth fAuth;

    private MutableLiveData<String> error;
    private UserLiveData user;

    private FirebaseUserLiveData currentFirebaseUser;

    private AuthentificationRepository() {

        currentFirebaseUser = new FirebaseUserLiveData();

    }

    public static AuthentificationRepository getInstance() {
        if (instance == null) {
            instance = new AuthentificationRepository();
        }

        return instance;
    }


    public void register(User user) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {


                } else {
                    error.setValue("Invalid email/password combination");

                }
            }

        });
    }

    public void login(String email, String password) {
        if (email == null || email.isEmpty()) {
            error.setValue("Please enter an email address");
        } else if (password == null || password.isEmpty()) {
            error.setValue("Please enter a password");
        } else {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {

                    } else {

                    }
                }
            });

        }
    }

    public void logOut() {
        FirebaseAuth.getInstance().signOut();

    }


    public FirebaseUserLiveData getCurrentFirebaseUser() {
        return currentFirebaseUser;
    }

    public LiveData<String> getErrorMessage() {
        return error;
    }
}



