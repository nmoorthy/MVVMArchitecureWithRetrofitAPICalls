package com.example.assignment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment.repository.MainRepository;
import com.example.assignment.viewmodel.UserListViewModel;

// COMPLETED (1) Make this class extend ViewModel ViewModelProvider.NewInstanceFactory
public class MainViewModelFactory implements ViewModelProvider.Factory {

    private final MainRepository mainRepository;

    public MainViewModelFactory(MainRepository moviesRepository) {
        this.mainRepository = moviesRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(UserListViewModel.class)) {
            //return (T) new UserListViewModel(moviesRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
