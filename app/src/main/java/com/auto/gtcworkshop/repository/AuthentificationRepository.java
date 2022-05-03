package com.auto.gtcworkshop.repository;

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


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
        auth = FirebaseAuth.getInstance();

        if (auth.getCurrentUser() != null) {
            firebaseUserMutableLiveData.postValue(auth.getCurrentUser());
        }
    }

    public void register(String email, String password) {

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

        });
    }

    public void login(String email, String password) {
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
        });

    }

    public void logout() {
        auth.signOut();
        userLoggedMutableLiveData.postValue(true);
    }
*/

}
