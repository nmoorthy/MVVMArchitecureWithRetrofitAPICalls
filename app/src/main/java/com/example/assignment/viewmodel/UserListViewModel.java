package com.example.assignment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment.model.UserListDataResponse;
import com.example.assignment.repository.MainRepository;

/**
 * Create a ViewModel class for your fragment. This class will hold the data and logic required for the activity.
 */
public class UserListViewModel extends ViewModel {
    private MainRepository repository;

    public UserListViewModel(){
        repository = new MainRepository();
    }

    public MutableLiveData<UserListDataResponse> getUserList() {
        return repository.getUserList();
    }

}
