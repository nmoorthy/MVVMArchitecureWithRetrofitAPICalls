package com.example.assignment;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import androidx.lifecycle.LiveData;

import com.example.assignment.model.UserListDataResponse;
import com.example.assignment.repository.MainRepository;
import com.example.assignment.viewmodel.UserListViewModel;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class UserListViewModelTest {

    private UserListViewModel userListViewModel;
    @Mock
    private MainRepository mainRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
//        userListViewModel = new UserListViewModel(mainRepository);
    }

    @Test
    public void testGetData() {
        // Mock the data you expect from the repository
//        UserListDataResponse mockData = new UserListDataResponse("Moorthy"); new UserListDataResponse("test@gmail.com");
//        when(mainRepository.getUserList()).thenReturn(mockData);
        when(mainRepository.getUserList()).getMock();

        // Call the method you want to test
        LiveData<UserListDataResponse> data = userListViewModel.getUserList();

        // Assert that the LiveData contains the expected data
//        assertEquals(mockData, data.getValue());
    }

}
