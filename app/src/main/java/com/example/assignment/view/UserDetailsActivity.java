package com.example.assignment.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.assignment.R;
import com.example.assignment.view.fragment.UpdateUserFragment;
import com.example.assignment.view.fragment.UserDetailsFragment;

public class UserDetailsActivity extends AppCompatActivity implements UserDetailsFragment.UserEdit, UpdateUserFragment.UserUpdate {

    private int userID = 0;
    private ImageView imgBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);

        Window window = this.getWindow();

// clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.primary_app_color));

        imgBack = findViewById(R.id.img_back);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            userID = bundle.getInt("userId");
        }
        loadFragment(new UserDetailsFragment().newInstance(userID));

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void loadFragment(Fragment userDetailsFragment) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, userDetailsFragment);
        //transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void user(String firstName, String emailId, String url, String text, String avatar) {
        loadFragment(new UpdateUserFragment().newInstance(userID, firstName, emailId, url, text, avatar));
    }

    @Override
    public void updateUser(String firstName) {
        loadFragment(new UserDetailsFragment().newInstance(userID));
    }
}