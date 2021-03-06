package com.auto.gtcworkshop.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.auto.gtcworkshop.R;
import com.auto.gtcworkshop.model.Reservation;
import com.auto.gtcworkshop.viewmodel.AddReservationViewModel;

public class AddReservationFragment extends Fragment {
    private AddReservationViewModel addReservationViewModel;
    private NavController navController;

    TextView addReservationTitle;
    TextView infoTitle;
    EditText feelTextView;
    EditText modelTextView;
    EditText problemTextView;
    EditText millageTextView;
    Button createReservationButton;
    Button cancelReservationButton;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_add_reservation, container, false);
    }

    public void initViews(View view) {
        navController = Navigation.findNavController(view);
        addReservationTitle = view.findViewById(R.id.addReservationsTitle);
        infoTitle = view.findViewById(R.id.infoTitle);
        feelTextView = view.findViewById(R.id.feelTextView);
        modelTextView = view.findViewById(R.id.modelTextView);
        problemTextView = view.findViewById(R.id.problemTextView);
        millageTextView = view.findViewById(R.id.millageTextView);
        createReservationButton = view.findViewById(R.id.createReservationButton);
        cancelReservationButton = view.findViewById(R.id.cancelReservationButton);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        addReservationViewModel = new ViewModelProvider(getActivity()).get(AddReservationViewModel.class);
        initViews(view);

        createReservationButton.setOnClickListener(v -> {
            addReservationViewModel.addReservation(new Reservation(feelTextView.getText().toString(), modelTextView.getText().toString(), problemTextView.getText().toString(), millageTextView.getText().toString()));
            navController.navigate(R.id.action_navi_add_reservations_to_navi_reservations);
            Toast.makeText(getActivity(), "Reservation added successfully!", Toast.LENGTH_SHORT).show();
        });

        cancelReservationButton.setOnClickListener(v -> {
            navController.navigate(R.id.action_navi_add_reservations_to_navi_reservations);
        });
    }
}
