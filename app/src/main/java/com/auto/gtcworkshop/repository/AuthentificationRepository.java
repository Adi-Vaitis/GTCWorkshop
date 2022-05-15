package com.auto.gtcworkshop.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.auto.gtcworkshop.livedata.FirebaseUserLiveData;
import com.auto.gtcworkshop.livedata.UserLiveData;
import com.auto.gtcworkshop.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class AuthentificationRepository {

    private static AuthentificationRepository instance;

    private final FirebaseAuth fAuth;

    private final MutableLiveData<String> error = new MutableLiveData<>("");
    private MutableLiveData<String> authenticationMessage = new MutableLiveData<>("");
    private final FirebaseFirestore fStore;
    String userID;

    private final FirebaseUserLiveData currentFirebaseUser;

    private AuthentificationRepository() {

        currentFirebaseUser = new FirebaseUserLiveData();
        fStore = FirebaseFirestore.getInstance();
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
                Log.d(TAG, "createUserWithEmail:success");
            }

            Map<String, Object> user1 = new HashMap<>();
            user1.put("FullName", user.getFullName());
            user1.put("Email", user.getEmail());
            user1.put("Phone", user.getPhone());

            fStore.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getUid()).set(user1).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d(TAG, "DocumentSnapshot successfully written!");
                }
            })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });

        }).addOnFailureListener(e -> {
            Log.i("Error user", e.getMessage());
        });
    }

    public void login(User userLog) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(userLog.getEmail(), userLog.getPassword())
                .addOnCompleteListener(result -> {
                    if (result.isSuccessful()) {
                        Log.i(TAG, "Login Successfully!");
                    } else {
                        Log.i(TAG, "Error login!");
                        error.setValue("Invalid email/password combination");
                    }
                });
    }

    public void firebaseAuthWithGoogle(boolean isRegister, GoogleSignInAccount account, FragmentActivity activity) {
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);
        fAuth.signInWithCredential(credential).
                addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            authenticationMessage.postValue("Registrations successful!");
                            FirebaseUser user = fAuth.getCurrentUser();
                            if(user != null && isRegister)
                            {
                                register(new User(user.getUid(), user.getEmail()));
                            }
                        }
                        else{
                            authenticationMessage.postValue("Registration failed!");
                            Log.e(TAG,"Google: " + task.getException());
                        }
                    }
                });
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



