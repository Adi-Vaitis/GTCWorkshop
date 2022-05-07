package com.auto.gtcworkshop.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.auto.gtcworkshop.repository.AuthentificationRepository;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class MainViewModel extends ViewModel {

        private AuthentificationRepository repository;

        public MainViewModel() {
            super();
            repository = AuthentificationRepository.getInstance();
        }

        public LiveData<FirebaseUser> getCurrentFirebaseUser() {
            return repository.getFirebaseUser();
        }

    }

