package com.auto.gtcworkshop.repository;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import android.view.View;
import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
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
    private static AuthentificationRepository instance;
    private static AuthentificationDAO authentificationDAO;
   // private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
   // private MutableLiveData<Boolean> userLoggedMutableLiveData;
  //  private FirebaseAuth auth;

    private AuthentificationRepository(Application app){
        authentificationDAO = AuthentificationDAO.getInstance(app);
    }

    public LiveData<Boolean> getSignIn()
    {
        return authentificationDAO.signIn();
    }

    public void setSignIn(Boolean signIn) {
        authentificationDAO.setSignIn(signIn);
    }

    public LiveData<String> getAuthenticationMessage()
    {
        return authentificationDAO.getAuthenticationMessage();
    }

    public LiveData<Boolean> getProgressBar()
    {
        return authentificationDAO.getProgressBar();
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return authentificationDAO.getCurrentUser();
    }

    public LiveData<Boolean> getSignOut() {
        return authentificationDAO.getSignOut();
    }

    public void signOut() {
        authentificationDAO.signOut();
    }

    public void registerAccount(Activity activity, String email, String password) {
        authentificationDAO.registerAccount(activity,email,password);
    }

    public void loginAccount(Activity activity, String email, String password)
    {
        authentificationDAO.loginAccount(activity,email,password);
    }

    public void forgotPassword(View view)
    {
        authentificationDAO.forgotPassword(view);
    }

    public MutableLiveData<Boolean> verifiedEmail()
    {
        return authentificationDAO.verifiedEmail();
    }

    public void verifyEmail(){
        authentificationDAO.verifyEmail();
    }

    public static AuthentificationRepository getInstance(Application app){
        if(instance == null){
            instance = new AuthentificationRepository(app);
        }
        return instance;
    }
    private MutableLiveData<FirebaseUser> firebaseUserMutableLiveData;
    private MutableLiveData<Boolean> userLoggedMutableLiveData;
    private FirebaseAuth fAauth;
    FirebaseFirestore fStore;
    String userID;

  /*  public MutableLiveData<FirebaseUser> getFirebaseUserMutableLiveData() {
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
*/

}
