package com.auto.gtcworkshop.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.auto.gtcworkshop.R;
import com.auto.gtcworkshop.model.Reservation;
import com.auto.gtcworkshop.model.ReservationAdapter;
import com.auto.gtcworkshop.viewmodel.ReservationViewModel;
import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class ReservationsFragment extends Fragment {

    private FirebaseFirestore firebaseFirestore;
    private ArrayList<Reservation> reservationArrayList;
    private ReservationAdapter adapter;
    private ReservationViewModel viewModel;
    private RecyclerView recyclerView;
    Button reservationsMainButton;

    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_reservations, container, false);

    }

    private void initViews(View view){
        navController = Navigation.findNavController(view);
        recyclerView = view.findViewById(R.id.reservations_recycler);
        reservationsMainButton = view.findViewById(R.id.addReservationMainButton);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel = new ViewModelProvider(this).get(ReservationViewModel.class);
        viewModel.init();
        initViews(view);

        reservationsMainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.action_navi_reservations_to_navi_add_reservations);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        firebaseFirestore = FirebaseFirestore.getInstance();
        reservationArrayList = new ArrayList<>();

        adapter = new ReservationAdapter(this.reservationArrayList, viewModel, getContext());

        recyclerView.setAdapter(adapter);

        EventChangeListener();


        loadReservations();
    }

    private void EventChangeListener() {
        firebaseFirestore.collection("Reservations").addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error != null){
                    Log.e("Firestore error: ", error.getMessage());
                    return;
                }

                for(DocumentChange documentChange : value.getDocumentChanges()){
                    if(documentChange.getType() == DocumentChange.Type.ADDED){
                        reservationArrayList.add(documentChange.getDocument().toObject(Reservation.class));
                    }

                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    public void loadReservations(){
        viewModel.getReservations();
    }
}
