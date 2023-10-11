package com.example.assignment.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.assignment.model.UpdateRequest;
import com.example.assignment.model.UpdateResponse;
import com.example.assignment.model.UserData;
import com.example.assignment.model.UserListDataResponse;
import com.example.assignment.network.GetDataService;
import com.example.assignment.network.RetrofitClientInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Create a repository class to abstract the data source, which in this case is the Retrofit API call.
 */

/** Make the API Call:
 * The API call is made inside the MyRepository class, which is accessed through the ViewModel.
    *The ViewModel exposes a LiveData object that the fragment observes. When the API call completes, it updates the LiveData,
    *and the fragment receives the updated data automatically.
 * */
public class MainRepository {

    private GetDataService getDataService;
    public MainRepository(){
        getDataService = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
    }
    public MutableLiveData<UserListDataResponse> getUserList() {

        Call<UserListDataResponse> call = getDataService.getAllUserList();

        final MutableLiveData<UserListDataResponse> userData = new MutableLiveData<>();
        call.enqueue(new Callback<UserListDataResponse>() {
            @Override
            public void onResponse(Call<UserListDataResponse> call, Response<UserListDataResponse> response) {
                userData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserListDataResponse> call, Throwable t) {
                System.out.println("onFailure");
                userData.setValue(null);
            }
        });
        return userData;
    }


    public MutableLiveData<UserData> getUserDetails(Integer userId) {

        Call<UserData> call = getDataService.getUserDetail(userId);

        final MutableLiveData<UserData> userData = new MutableLiveData<>();
        call.enqueue(new Callback<UserData>() {
            @Override
            public void onResponse(Call<UserData> call, Response<UserData> response) {
                userData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UserData> call, Throwable t) {
                System.out.println("onFailure");
                userData.setValue(null);
            }
        });
        return userData;
    }


    public MutableLiveData<UpdateResponse> getUserUpdate(Integer mParam1, UpdateRequest updateRequest) {
        Call<UpdateResponse> call = getDataService.getUserUpdate(mParam1, updateRequest);

        final MutableLiveData<UpdateResponse> userData = new MutableLiveData<>();
        call.enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                userData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                System.out.println("onFailure");
                userData.setValue(null);
            }
        });
        return userData;
    }

    public MutableLiveData<UpdateResponse> getCreateAccount(UpdateRequest updateRequest) {
        Call<UpdateResponse> call = getDataService.getCreateAccount(updateRequest);

        final MutableLiveData<UpdateResponse> userData = new MutableLiveData<>();
        call.enqueue(new Callback<UpdateResponse>() {
            @Override
            public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                userData.setValue(response.body());
                Log.d(">>Create account repositoy", "-----" + response);
            }

            @Override
            public void onFailure(Call<UpdateResponse> call, Throwable t) {
                System.out.println("onFailure");
                userData.setValue(null);
            }
        });
        return userData;
    }
}
