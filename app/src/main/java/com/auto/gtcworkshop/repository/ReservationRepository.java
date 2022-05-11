package com.auto.gtcworkshop.repository;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.auto.gtcworkshop.livedata.FirebaseUserLiveData;
import com.auto.gtcworkshop.livedata.ReservationsLiveData;
import com.auto.gtcworkshop.model.Reservation;
import com.auto.gtcworkshop.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.Source;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReservationRepository {
    private static ReservationRepository instance;
    private ReservationsLiveData reservationsLiveData;
    private ArrayList<Reservation> reservationsList = new ArrayList<>();
    private ArrayList<User> usersList = new ArrayList<>();

    private FirebaseFirestore firebaseDatabase;
    private final FirebaseUserLiveData currentFirebaseUser;

    private MutableLiveData<ArrayList<Reservation>> reservationLiveData = new MutableLiveData<>(new ArrayList<>());
    private MutableLiveData<ArrayList<User>> userLiveData = new MutableLiveData<>(new ArrayList<>());


    public static ReservationRepository getInstance(){
        if(instance == null) instance = new ReservationRepository();
        return instance;
    }

    public ReservationRepository(){
        firebaseDatabase = FirebaseFirestore.getInstance();
        currentFirebaseUser = new FirebaseUserLiveData();
    }

    public void addReservation(Reservation reservation){
        Map<String, Object> reservationMap = new HashMap<>();
          //  reservationMap.put("userId", reservation.getUserId());
            reservationMap.put("feel", reservation.getFeel());
            reservationMap.put("model", reservation.getModel());
            reservationMap.put("problem", reservation.getProblem());
            reservationMap.put("millage", reservation.getMillage());

            firebaseDatabase.collection("Reservations").document().set(reservationMap).addOnSuccessListener(new OnSuccessListener<Void>() {
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
        }

    public void getReservations(){
        DocumentReference documentReference = firebaseDatabase.collection("Reservations").document();
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    Reservation rez = task.getResult().toObject(Reservation.class);
                } else {
                    reservationLiveData.postValue(null);
                    Log.e(TAG, task.getException().getMessage());
                }
            }
        });
    }

    public void init(){
        reservationsLiveData = new ReservationsLiveData(firebaseDatabase.collection("reservations").document(FirebaseAuth.getInstance().getCurrentUser().getUid()));
    }

    public ReservationsLiveData getReservationsLiveData(){
        return reservationsLiveData;
    }

    public MutableLiveData<ArrayList<Reservation>> getReservationLiveData() {
        return reservationLiveData;
    }

    public MutableLiveData<ArrayList<User>> getUserLiveData() {
        return userLiveData;
    }
}
