package com.auto.gtcworkshop.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.auto.gtcworkshop.livedata.FirebaseUserLiveData;
import com.auto.gtcworkshop.livedata.UserLiveData;
import com.auto.gtcworkshop.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class AuthentificationRepository {
    private static AuthentificationRepository instance;

    private FirebaseAuth fAuth;

    private MutableLiveData<String> error;
    private UserLiveData user;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;


    private FirebaseUserLiveData currentFirebaseUser;

    private AuthentificationRepository() {

        currentFirebaseUser = new FirebaseUserLiveData();
//        databaseReference = database.getReference();
        currentFirebaseUser = new FirebaseUserLiveData();
    }

    public static AuthentificationRepository getInstance() {
        if (instance == null) {
            instance = new AuthentificationRepository();
        }

        return instance;
    }


    public void register(User user) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(task ->  {
                if (task.isSuccessful()) {
                    databaseReference.child("users").child(FirebaseAuth.getInstance().getUid()).setValue(user);
                    UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                            .setDisplayName(user.getFullName())
                            .build();

                    currentFirebaseUser.getValue().updateProfile(request);
                }

        });
    }

    public void login(String email, String password) {
        if (email == null || email.isEmpty()) {
            error.setValue("Please enter an email address");
        } else if (password == null || password.isEmpty()) {
            error.setValue("Please enter a password");
        } else {

            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(result -> {
                        if (!result.isSuccessful()) {
                            error.setValue("Invalid email/password combination");
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



