package com.auto.gtcworkshop.view;

import android.os.Bundle;
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
import com.auto.gtcworkshop.viewmodel.ReservationViewModel;

public class ReservationsFragment extends Fragment {

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
        viewModel = new ViewModelProvider(getActivity()).get(ReservationViewModel.class);
        viewModel.init();
        initViews(view);

        reservationsMainButton.setOnClickListener(v -> {
            navController.navigate(R.id.action_navi_reservations_to_navi_add_reservations);
        });

        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

}
