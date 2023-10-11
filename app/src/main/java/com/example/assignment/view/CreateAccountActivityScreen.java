package com.example.assignment.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.assignment.R;
import com.example.assignment.databinding.CreateAccountActivityBinding;
import com.example.assignment.model.UpdateRequest;
import com.example.assignment.viewmodel.UserCreateViewModel;

public class CreateAccountActivityScreen extends AppCompatActivity {

    private UserCreateViewModel viewModel;
    private CreateAccountActivityBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.create_account_activity);

        viewModel = new UserCreateViewModel();
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.primary_app_color));

        setupViewModel();

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userCreateApi();
            }
        });

    }

    private void setupViewModel() {
        // MainViewModelFactory factory = new MainViewModelFactory(this, recyclerView);
//        viewModel = ViewModelProviders.of(this).get(UserCreateViewModel.class);
        viewModel = new ViewModelProvider(this).get(UserCreateViewModel.class);

    }

    private void userCreateApi() {

        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setName(binding.edFirstName.getText().toString());
        updateRequest.setName(binding.edEmailId.getText().toString());
        updateRequest.setName(binding.edPassword.getText().toString());
        updateRequest.setJob("test");

        viewModel.getCreateAccount(updateRequest).observe(CreateAccountActivityScreen.this, datum -> {

            /* Update UI with the data received from the ViewModel */
            Log.d(">>Create account>>", "-----" + datum.getName().toString());

            if (datum != null) {
                finish();
                Toast.makeText(CreateAccountActivityScreen.this, "User account created successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(CreateAccountActivityScreen.this, "Please check the internet connection", Toast.LENGTH_SHORT).show();

            }

        });

    }

}
