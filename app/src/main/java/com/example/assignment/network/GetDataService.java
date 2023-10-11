package com.example.assignment.network;

import com.example.assignment.model.UpdateRequest;
import com.example.assignment.model.UpdateResponse;
import com.example.assignment.model.UserData;
import com.example.assignment.model.UserListDataResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/** Create API Service Interface:
 * Create an interface that defines the API endpoints using Retrofit annotations.
 */
public interface GetDataService {

    // Get all the users list API
    @GET("api/users")
    Call<UserListDataResponse> getAllUserList();

    //Get the User details API
    @GET("api/users/{userId}")
    Call<UserData> getUserDetail(@Path("userId") Integer userId);

    //User update the API
    @POST("api/users/{userId}")
    Call<UpdateResponse> getUserUpdate(@Path("userId") Integer userId, @Body UpdateRequest updateRequest);

    //Create Account users API
    @POST("api/users")
    Call<UpdateResponse> getCreateAccount(@Body UpdateRequest updateRequest);
}
