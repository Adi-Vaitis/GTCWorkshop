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
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
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

    private Source source = Source.CACHE;


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
            reservationMap.put("userId", FirebaseAuth.getInstance().getCurrentUser().getUid());
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
        firebaseDatabase.collection("Reservations")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }

    public void init(){
        reservationsLiveData = new ReservationsLiveData(firebaseDatabase.collection("Reservations").document(FirebaseAuth.getInstance().getCurrentUser().getUid()));
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
