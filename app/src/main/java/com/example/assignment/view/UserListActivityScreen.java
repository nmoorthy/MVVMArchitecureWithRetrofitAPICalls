package com.example.assignment.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment.R;
import com.example.assignment.adapter.UserListCustomAdapter;
import com.example.assignment.databinding.UserListActivityBinding;
import com.example.assignment.model.Datum;
import com.example.assignment.viewmodel.UserListViewModel;

import java.util.List;

public class UserListActivityScreen extends AppCompatActivity implements onItemUserclick {

    private UserListActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.user_list_activity);

        UserListViewModel viewModel = new UserListViewModel();
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.primary_app_color));

        setupViewModel();

        binding.imgCreateUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), CreateAccountActivityScreen.class);
                startActivity(intent);
            }
        });

        binding.imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setupViewModel() {
        /* Set up viewmodel in fragment:,  obtain an instance of the ViewModel using */
//        UserListViewModel viewModel = ViewModelProviders.of(this).get(UserListViewModel.class); // depricated
        UserListViewModel viewModel = new ViewModelProvider(this).get(UserListViewModel.class);

        /*and observe the data changes using the LiveData returned by the ViewModel.*/
        /* Update your UI elements when the data changes.*/

        // MainViewModelFactory factory = new MainViewModelFactory(this, recyclerView);
        viewModel.getUserList().observe(this, userListDataResponses -> {
            /* Update UI with the data received from the ViewModel */
            Log.d("User list>>>>", "===" + userListDataResponses);
            if (userListDataResponses != null) {
                generateDataList(userListDataResponses.getData());
            } else {
                Toast.makeText(UserListActivityScreen.this, "Please check the internet connection", Toast.LENGTH_SHORT).show();

            }

        });


    }


    private void generateDataList(List<Datum> userListDataResponses) {

        UserListCustomAdapter adapter;
        //RecyclerView recyclerView;
        adapter = new UserListCustomAdapter(this, userListDataResponses, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        binding.customRecyclerView.setLayoutManager(layoutManager);
        binding.customRecyclerView.setAdapter(adapter);
    }

    @Override
    public void OnItemUserClick(Datum datum) {

        Intent intent = new Intent(this, UserDetailsActivity.class);
        intent.putExtra("userId", datum.getId());
        startActivity(intent);

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }


}
