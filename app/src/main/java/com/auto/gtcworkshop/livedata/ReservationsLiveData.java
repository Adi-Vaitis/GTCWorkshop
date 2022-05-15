package com.auto.gtcworkshop.livedata;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;

import com.auto.gtcworkshop.model.Reservation;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;

import java.util.ArrayList;

public class ReservationsLiveData extends LiveData<ArrayList<Reservation>> {

    private DatabaseReference databaseReference;
    private DocumentReference dbRef;

    public ReservationsLiveData(DocumentReference dbRef) {
        this.dbRef = dbRef;
        setValue(new ArrayList<>());
    }

    private ChildEventListener listener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
            Reservation reservation = snapshot.getValue(Reservation.class);
            reservation.setId(snapshot.getKey());

            ArrayList<Reservation> current = getValue();
            current.add(reservation);
            setValue(current);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot snapshot) {
            ArrayList<Reservation> current = getValue();

            for (int i = 0; i < current.size(); i++) {
                if (current.get(i).getId().equals(snapshot.getKey())) {
                    current.remove(i);
                    break;
                }
            }

            setValue(current);
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    protected void onActive() {
        super.onActive();
        databaseReference.addChildEventListener(listener);

    }

    @Override
    protected void onInactive() {
        super.onInactive();
        databaseReference.addChildEventListener(listener);
    }
}
