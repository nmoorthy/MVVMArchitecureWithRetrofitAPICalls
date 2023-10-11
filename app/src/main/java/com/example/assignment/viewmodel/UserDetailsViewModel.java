package com.example.assignment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment.model.UpdateRequest;
import com.example.assignment.model.UpdateResponse;
import com.example.assignment.model.UserData;
import com.example.assignment.repository.MainRepository;
/**
 * Create a ViewModel class for your fragment. This class will hold the data and logic required for the fragment.
 */
public class UserDetailsViewModel extends ViewModel {
    MainRepository repository = new MainRepository();

    public MutableLiveData<UserData> getUserDetails(Integer userId) {
        return repository.getUserDetails(userId);
    }

    public MutableLiveData<UpdateResponse> getUserUpdate(Integer mParam1, UpdateRequest updateRequest) {
        return repository.getUserUpdate(mParam1,updateRequest);
    }

}
