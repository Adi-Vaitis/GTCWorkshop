package com.auto.gtcworkshop.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.auto.gtcworkshop.R;
import com.google.firebase.auth.FirebaseAuth;

public class LogoutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseAuth.getInstance().signOut();
        Toast.makeText(getActivity(), "Logged Out", Toast.LENGTH_SHORT).show();
        return inflater.inflate(R.layout.fragment_logout, container, false);
    }
}
