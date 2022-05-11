package com.auto.gtcworkshop.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.auto.gtcworkshop.R;
import com.auto.gtcworkshop.model.Reservation;
import com.auto.gtcworkshop.model.User;
import com.auto.gtcworkshop.viewmodel.ReservationViewModel;

public class AddReservationFragment extends Fragment {
    private ReservationViewModel reservationViewModel;
    private NavController navController;

    TextView addReservationTitle;
    TextView infoTitle;
    EditText feelTextView;
    EditText modelTextView;
    EditText problemTextView;
    EditText millageTextView;
    Button createReservationButton;
    Button cancelReservationButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_reservation, container, false);

        addReservationTitle = view.findViewById(R.id.addReservationsTitle);
        infoTitle = view.findViewById(R.id.infoTitle);
        feelTextView = view.findViewById(R.id.feelTextView);
        modelTextView = view.findViewById(R.id.modelTextView);
        problemTextView = view.findViewById(R.id.problemTextView);
        millageTextView = view.findViewById(R.id.millageTextView);
        createReservationButton = view.findViewById(R.id.createReservationButton);
        cancelReservationButton = view.findViewById(R.id.cancelReservationButton);

        cancelReservationButton.setOnClickListener(view1 -> {
                navController.navigate(R.id.action_navi_add_reservations_to_navi_reservations);
        });

        createReservationButton.setOnClickListener(view2 -> {
                reservationViewModel.addReservation(new Reservation(feelTextView.toString(), modelTextView.toString(), problemTextView.toString(), millageTextView.toString()), new User());
                navController.navigate(R.id.action_navi_add_reservations_to_navi_reservations);
        });

        return view;
    }
}
