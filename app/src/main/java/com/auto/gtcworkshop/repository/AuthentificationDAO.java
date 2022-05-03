package com.auto.gtcworkshop.repository;

import static android.content.ContentValues.TAG;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Application;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class AuthentificationDAO {
    private final AuthentificationLiveData currentUser;
    private final Application application;
    private static AuthentificationDAO instance;
    private DocumentReference doc;

    private MutableLiveData<String> authenticationMessage = new MutableLiveData<>("");
    private MutableLiveData<Boolean> progressBar = new MutableLiveData<>(false);

    private MutableLiveData<Boolean> verifiedEmail = new MutableLiveData<>(false);

    private MutableLiveData<Boolean> signIn = new MutableLiveData<>(false);

    private MutableLiveData<Boolean> register = new MutableLiveData<>(false);
    private MutableLiveData<Boolean> signOut = new MutableLiveData<>(false);

    private FirebaseAuth firebaseAuth;

    private FirebaseFirestore firebaseDatabase;

    private AuthentificationDAO(Application app) {
        this.application = app;
        currentUser = new AuthentificationLiveData();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseFirestore.getInstance();
    }

    public static AuthentificationDAO getInstance(Application app) {
        if (instance == null) {
            instance = new AuthentificationDAO(app);
        }
        return instance;
    }

    public MutableLiveData<Boolean> verifiedEmail()
    {
        if(currentUser.getValue() != null)
        {
            verifiedEmail.postValue(currentUser.getValue().isEmailVerified());
        }
        return verifiedEmail;
    }

    public LiveData<Boolean> signIn() {
        return signIn;
    }

    public void setSignIn(Boolean isSignInPressed) {
        this.signIn.setValue(isSignInPressed);
    }

    public LiveData<String> getAuthenticationMessage() {
        return authenticationMessage;
    }

    public LiveData<Boolean> getProgressBar() {
        return progressBar;
    }

    public LiveData<FirebaseUser> getCurrentUser() {
        return currentUser;
    }

    public LiveData<Boolean> getSignOut() {
        return signOut;
    }

    public void signOut() {
        AuthUI.getInstance().signOut(application.getApplicationContext());
        signOut.setValue(true);
    }

    public void registerAccount(Activity activity, String email, String password) {
        progressBar.setValue(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.d(TAG, "createUserWithEmail:success");
                                    authenticationMessage.postValue("User created!");
                                } else {
                                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                    authenticationMessage.postValue("Error on creating user");
                                }
                            }
                        });
                signOut.postValue(false);
                progressBar.postValue(false);
            }
        }, 3000);
    }

    public void loginAccount(Activity activity, String email, String password) {
        progressBar.setValue(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                firebaseAuth.signInWithEmailAndPassword(email, password).
                        addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    //Sign in success
                                    Log.d(TAG, "signInUserWithEmail:success");
                                    authenticationMessage.postValue("You are signed in!");
                                } else {
                                    Log.w(TAG, "signInWithEmail:failure", task.getException());
                                    authenticationMessage.postValue("Error on signing in");
                                }
                            }
                        });
                signOut.postValue(false);
                progressBar.postValue(false);
            }
        }, 3000);
    }

    public void forgotPassword(View view) {
        EditText resetEmail = new EditText(view.getContext());
        AlertDialog.Builder passwordResetDialog = new AlertDialog.Builder(view.getContext());
        passwordResetDialog.setTitle("Reset password?");
        passwordResetDialog.setMessage("Enter your email to reset the password");
        passwordResetDialog.setView(resetEmail);

        passwordResetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                firebaseAuth.sendPasswordResetEmail(resetEmail.getText().toString()).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        authenticationMessage.postValue("Email sent!");
                        Log.e(TAG, "Email sent!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        authenticationMessage.postValue("Error! Reset link was not sent!");
                        Log.w(TAG, e.getMessage());
                    }
                });

            }
        });

        passwordResetDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                authenticationMessage.postValue("Reset password cancelled.");
            }
        });

        passwordResetDialog.create().show();
    }

    public void createUser(String email, String password) {
        Map<String, Object> user = new HashMap<>();
        if (currentUser.getValue() != null) {
            Authentification createUser = new Authentification(firebaseAuth.getCurrentUser().getUid(), email, password);
            user.put("uid", createUser.getUserId());
            user.put("username", createUser.getFullName());
            user.put("password", createUser.getPassword());
            user.put("email", createUser.getEmail());
            user.put("phoneNo", createUser.getPhoneNo());
        }
    }

    public void verifyEmail() {
        progressBar.setValue(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if(currentUser.getValue() != null)
                {
                    currentUser.getValue().sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Log.d(TAG, "Email successfully sent!");
                                authenticationMessage.postValue("Email successfully sent!");
                                verifiedEmail.postValue(true);
                            }
                            else {
                                Log.e(TAG, "Error sending the mail. Error: " + task.getException());
                                authenticationMessage.postValue("Error sending the mail.");
                            }
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, "Error sending the mail. Error: " + e.getMessage());
                            authenticationMessage.postValue("Error sending the mail.");
                        }
                    });
                }
                progressBar.postValue(false);
            }
        }, 3000);
    }
}
