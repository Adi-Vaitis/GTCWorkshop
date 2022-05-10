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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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



