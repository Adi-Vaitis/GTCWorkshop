package com.auto.gtcworkshop.repository;

import android.app.Application;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.auto.gtcworkshop.livedata.FirebaseUserLiveData;
import com.auto.gtcworkshop.livedata.UserLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class AuthentificationRepository {
    private Application application;
    private static AuthentificationRepository instance;
    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
    private MutableLiveData<Boolean> userLoggedMutableLiveData;
    private FirebaseAuth auth;
    private FirebaseUserLiveData currentFirebaseUser;
    private UserLiveData user;
    private MutableLiveData<String> error;

private AuthentificationRepository()
{
    currentFirebaseUser = new FirebaseUserLiveData();
    error = new MutableLiveData<>();
}


    public static AuthentificationRepository getInstance()
    {if(instance == null)
    {instance = new AuthentificationRepository();}
        return instance;}

   /* public void initCurrentUser() {
        user = new UserLiveData(dbRef.child("users").child(FirebaseAuth.getInstance().getUid()));
    } de vazut ce draq e de facut cu dvRef  */

    public AuthentificationRepository(Application application) {


        this.application = application;
        firebaseUserMutableLiveData = new MutableLiveData<>();
        userLoggedMutableLiveData = new MutableLiveData<>();
        auth = FirebaseAuth.getInstance();
        error = new MutableLiveData<>();

        if (auth.getCurrentUser() != null) {
            firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
        }
    }



    public void register(String email, String password) {

        if(email == null || email.isEmpty()){   error.setValue("Please enter an email address");
        }else if(password == null || password.isEmpty()) {
            error.setValue("Please enter a password");
        }{


        // de comparat metodele de a crea contul in firebase
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                    Toast.makeText(application, "Account Created", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                }
            }

        });}
    }

    public void login(String email, String password) {

        if(email == null || email.isEmpty()){   error.setValue("Please enter an email address");
        }else if(password == null || password.isEmpty()) {
            error.setValue("Please enter a password");
        }else{
            // de comparat metodele de a crea contul in firebase
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
                    Toast.makeText(application, "Logged In", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(application, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });}

    }
    public FirebaseUserLiveData getCurrentFirebaseUser() {
        return currentFirebaseUser;
    }

    public void logout() {
        auth.signOut();
        FirebaseAuth.getInstance().signOut();
        userLoggedMutableLiveData.postValue(true);
    }





}
