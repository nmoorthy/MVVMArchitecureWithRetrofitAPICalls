package com.example.assignment.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.assignment.R;
import com.example.assignment.model.UpdateRequest;
import com.example.assignment.model.UpdateResponse;
import com.example.assignment.viewmodel.UserDetailsViewModel;
import com.example.assignment.viewmodel.UserListViewModel;

import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateUserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_USERID = "param1";
    private static final String ARG_USER = "param2";
    private static final String ARG_EMAIL = "param3";
    private static final String ARG_URL = "param4";
    private static final String ARG_TEXT = "param5";
    private static final String ARG_AVATAR = "param6";

    // TODO: Rename and change types of parameters
    private Integer userId;
    private String userFirstName, emailId, str_url, str_text;

    private String avatar_url;

    EditText userName, email, url, text;
    ImageView img_user;
    Button btn_edit;
    UserDetailsViewModel viewModel;

    UserUpdate upateListner;

    public interface UserUpdate {
        public void updateUser(String firstName);
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param2    Parameter 2.
     * @param param1    Parameter 1.
     * @param firstName
     * @param emailId
     * @param url
     * @param text
     * @param avatar
     * @return A new instance of fragment UserDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateUserFragment newInstance(Integer param1, String firstName, String emailId, String url, String text, String avatar) {
        UpdateUserFragment fragment = new UpdateUserFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_USERID, param1);
        args.putString(ARG_USER, firstName);
        args.putString(ARG_EMAIL, emailId);
        args.putString(ARG_URL, url);
        args.putString(ARG_TEXT, text);
        args.putString(ARG_AVATAR, avatar);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt(ARG_USERID);
            userFirstName = getArguments().getString(ARG_USER);
            emailId = getArguments().getString(ARG_EMAIL);
            str_url = getArguments().getString(ARG_URL);
            str_text = getArguments().getString(ARG_TEXT);
            avatar_url = getArguments().getString(ARG_AVATAR);
            Log.e("-----", "-mParam1--" + userId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_update, container, false);
        userName = view.findViewById(R.id.ed_first_name);
        img_user = view.findViewById(R.id.user_img);
        email = view.findViewById(R.id.ed_email);
        url = view.findViewById(R.id.ed_url);
        text = view.findViewById(R.id.ed_text);
        btn_edit = view.findViewById(R.id.btn_edit);
        return view;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /* Set up viewmodel in fragment:,  obtain an instance of the ViewModel using */
//        viewModel = ViewModelProviders.of(this).get(UserDetailsViewModel.class); //depricated
        viewModel = new ViewModelProvider(this).get(UserDetailsViewModel.class);

        /*and observe the data changes using the LiveData returned by the ViewModel.*/
        /* Update your UI elements when the data changes.*/
        userName.setText("" + userFirstName);

        email.setText("" + emailId);
        url.setText("" + str_url);
        text.setText("" + str_text);

        Glide.with(requireContext())
                .load(avatar_url)
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(img_user);

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userUpdate();
            }
        });
    }

    private void userUpdate() {
        UpdateRequest updateRequest = new UpdateRequest();
        updateRequest.setName(userName.getText().toString());
        updateRequest.setJob("test");

        /*Handle lifecycle
         * Ensure that you handle the fragment's lifecycle properly to prevent memory leaks.
         * Use getViewLifecycleOwner() when observing LiveData to tie the observer's lifecycle to the fragment's view.
         */
        viewModel.getUserUpdate(userId, updateRequest).observe(getViewLifecycleOwner(), datum -> {

            /* Update UI with the data received from the ViewModel */
            Log.d("User list>>>>", "===" + datum.getName());

            if (datum != null) {
                upateListner.updateUser(datum.getName());
                Toast.makeText(getActivity(), "User updated successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getActivity(), "Please check the internet connection", Toast.LENGTH_SHORT).show();

            }

        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            upateListner = (UserUpdate) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement LogoutUser");
        }
    }

}