package com.auto.gtcworkshop.livedata;

import androidx.lifecycle.LiveData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class FirebaseUserLiveData extends LiveData<FirebaseUser> {

    private FirebaseAuth.AuthStateListener listener = auth -> {
        setValue(auth.getCurrentUser());
    };

    @Override
    protected void onActive() {
        super.onActive();
        FirebaseAuth.getInstance().addAuthStateListener(listener);
    }

    @Override
    protected void onInactive() {
        super.onInactive();
        FirebaseAuth.getInstance().removeAuthStateListener(listener);
    }

}


