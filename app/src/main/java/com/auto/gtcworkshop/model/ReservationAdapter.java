package com.auto.gtcworkshop.model;

import android.app.Application;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.auto.gtcworkshop.R;
import com.auto.gtcworkshop.livedata.FirebaseUserLiveData;
import com.auto.gtcworkshop.repository.AuthentificationRepository;
import com.auto.gtcworkshop.repository.ReservationRepository;
import com.auto.gtcworkshop.viewmodel.ReservationViewModel;

import java.util.ArrayList;
import java.util.List;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    private ArrayList<Reservation> reservationList;
    private ReservationViewModel viewModel;
    Context context;

    public ReservationAdapter(ArrayList<Reservation> reservationList, ReservationViewModel viewModel, Context context) {
        this.reservationList = reservationList;
        this.viewModel = viewModel;
        this.context = context;
    }

    @NonNull
    @Override
    public ReservationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.reservation_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationAdapter.ViewHolder holder, int position) {
        Reservation reservation = reservationList.get(position);

        holder.feelItem.setText(reservation.getFeel());
        holder.modelItem.setText(reservation.getModel());
        holder.problemItem.setText(reservation.getProblem());
        holder.millageItem.setText(reservation.getMillage());
    }

    @Override
    public int getItemCount() {
        return this.reservationList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView feelItem;
        private TextView modelItem;
        private TextView problemItem;
        private TextView millageItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            feelItem = itemView.findViewById(R.id.feelItem);
            modelItem = itemView.findViewById(R.id.modelItem);
            problemItem = itemView.findViewById(R.id.problemItem);
            millageItem = itemView.findViewById(R.id.millageItem);
        }
    }

}