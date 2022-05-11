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

import java.util.ArrayList;

public class ReservationAdapter extends RecyclerView.Adapter<ReservationAdapter.ViewHolder> {

    private final ArrayList<Reservation> reservationArrayList;
    private Context context;
    private AuthentificationRepository authentificationRepository;
    private ReservationRepository reservationRepository;

    public ReservationAdapter(ArrayList<Reservation> reservationArrayList, Context context, Application app) {
        this.reservationArrayList = reservationArrayList;
        this.context = context;
        this.authentificationRepository = AuthentificationRepository.getInstance();
        this.reservationRepository = ReservationRepository.getInstance();
    }

    @NonNull
    @Override
    public ReservationAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reservation_item, parent, false);

        return new ReservationAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReservationAdapter.ViewHolder holder, int position) {
        Reservation reservation = reservationArrayList.get(position);

        holder.feelItem.setText(reservation.getFeel());
        holder.modelItem.setText(reservation.getModel());
        holder.problemItem.setText(reservation.getProblem());
        holder.millageItem.setText(reservation.getMillage());
    }

    @Override
    public int getItemCount() {
        return reservationArrayList.size();
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