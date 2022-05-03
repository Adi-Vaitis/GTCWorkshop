package com.auto.gtcworkshop.repository;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AuthentificationRepository {
    private Application application;
    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
    private MutableLiveData<Boolean> userLoggedMutableLiveData;
    private FirebaseAuth fAauth;
    FirebaseFirestore fStore;
    String userID;

    public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
        return firebaseUserMutableLiveData;
    }

    public MutableLiveData<Boolean> getUserLoggedMutableLiveData() {
        return userLoggedMutableLiveData;
    }


    public AuthentificationRepository(Application application) {

        this.application = application;
        firebaseUserMutableLiveData = new MutableLiveData<>();
        userLoggedMutableLiveData = new MutableLiveData<>();
        fAauth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        if (fAauth.getCurrentUser() != null) {
            firebaseUserMutableLiveData.postValue(fAauth.getCurrentUser());
        }
    }

    public void register(String fullName,String email, String phone,String password) {

        fAauth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseUserMutableLiveData.postValue(fAauth.getCurrentUser());
                    Toast.makeText(application, "Account Created", Toast.LENGTH_SHORT).show();

                    userID = fAauth.getCurrentUser().getUid();
                    DocumentReference documentReference = fStore.collection("users").document();
                    FirebaseUser person = fAauth.getCurrentUser();

                    Map<String, Object> user = new HashMap<>();
                    user.put("fullName", fullName);
                    user.put("email_address", email);
                    user.put("phone_number", phone);
                    documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.d(TAG, "onSuccess: User profile is created in Firestore for" + userID);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.d(TAG, "onFailure: "+ e.toString());
                        }

                });} else {
                    Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

        });
    }

    public void login(String email, String password) {
        fAauth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseUserMutableLiveData.postValue(fAauth.getCurrentUser());
                    Toast.makeText(application, "Logged In", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void logout() {
        fAauth.signOut();
        userLoggedMutableLiveData.postValue(true);
    }

}
