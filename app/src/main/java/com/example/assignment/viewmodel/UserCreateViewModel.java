package com.example.assignment.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.assignment.model.UpdateRequest;
import com.example.assignment.model.UpdateResponse;
import com.example.assignment.repository.MainRepository;

public class UserCreateViewModel extends ViewModel {
    MainRepository repository = new MainRepository();

    public MutableLiveData<UpdateResponse> getCreateAccount(UpdateRequest updateRequest) {
        return repository.getCreateAccount(updateRequest);
    }

}
