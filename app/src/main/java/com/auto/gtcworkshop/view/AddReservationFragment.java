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

import com.auto.gtcworkshop.R;
import com.auto.gtcworkshop.viewmodel.ReservationViewModel;

public class AddReservationFragment extends Fragment {
    private ReservationViewModel reservationViewModel;

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

        return view;
    }
}
