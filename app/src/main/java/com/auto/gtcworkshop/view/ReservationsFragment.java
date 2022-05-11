package com.auto.gtcworkshop.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;

import com.auto.gtcworkshop.R;

public class ReservationsFragment extends Fragment {

    Button reservationsMainButton;

    private NavController navController;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_reservations, container, false);
        reservationsMainButton = view.findViewById(R.id.addReservationMainButton);

        reservationsMainButton.setOnClickListener(view1 ->  {
                navController.navigate(R.id.action_navi_reservations_to_navi_add_reservations);
        });

        return view;
    }
}
