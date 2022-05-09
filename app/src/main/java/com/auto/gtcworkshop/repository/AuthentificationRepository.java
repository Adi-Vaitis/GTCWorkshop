package com.auto.gtcworkshop.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.auto.gtcworkshop.livedata.FirebaseUserLiveData;
import com.auto.gtcworkshop.livedata.UserLiveData;
import com.auto.gtcworkshop.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AuthentificationRepository {
    private static AuthentificationRepository instance;

    private FirebaseAuth fAuth;

    private MutableLiveData<String> error;
    private UserLiveData user;
    private FirebaseUser userFire;
    private DatabaseReference databaseReference;
    private FirebaseDatabase database;
    private FirebaseFirestore fstore;
    String userID;


    private FirebaseUserLiveData currentFirebaseUser;

    private AuthentificationRepository() {

        currentFirebaseUser = new FirebaseUserLiveData();
        fstore = FirebaseFirestore.getInstance();
        fAuth = FirebaseAuth.getInstance();
    }

    public static AuthentificationRepository getInstance() {
        if (instance == null) {
            instance = new AuthentificationRepository();
        }

        return instance;
    }


    public void register(User user) {

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(user.getEmail(), user.getPassword()).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
               // databaseReference.child("Users").child(FirebaseAuth.getInstance().getUid()).setValue(user);
             //   UserProfileChangeRequest request = new UserProfileChangeRequest.Builder()
                //        .setDisplayName(user.getFullName())
               //         .build();

              //  FirebaseUser person = fAuth.getCurrentUser();
                Log.d(TAG, "createUserWithEmail:success");
                FirebaseUser userFire = fAuth.getCurrentUser();

            }

            userID = fAuth.getCurrentUser().getUid();
            DocumentReference documentReference = fstore.collection("Users").document(userID);

            Map<String, Object> user1 = new HashMap<>();
            user1.put("fullName", user.getFullName());
            user1.put("email_address", user.getEmail());
            user1.put("phone_number", user.getPhone());

            fstore.collection("Users").document(fAuth.getCurrentUser().getUid()).collection("Users").add(user);

        }).addOnFailureListener(e -> {
            Log.i("Error user",e.getMessage() );
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



