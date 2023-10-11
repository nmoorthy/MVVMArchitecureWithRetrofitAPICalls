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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.example.assignment.R;
import com.example.assignment.model.UserData;
import com.example.assignment.viewmodel.UserDetailsViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private Integer userId;
    private String mParam2;

    EditText userName, email, url, text;
    ImageView img_user;
    Button btn_edit;

    UserEdit userEdit, userEmail;
    UserData userData;

    // Container Activity must implement this interface
    public interface UserEdit {
        public void user(String firstName, String emailId, String url, String text, String avatar);
    }

    public UserDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserDetailsFragment newInstance(Integer param1) {
        UserDetailsFragment fragment = new UserDetailsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userId = getArguments().getInt(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            Log.e("-----", "-mParam1--" + userId);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_details, container, false);
        userName = view.findViewById(R.id.ed_first_name);
        img_user = view.findViewById(R.id.user_img);
        email = view.findViewById(R.id.ed_email);
        url = view.findViewById(R.id.ed_url);
        text = view.findViewById(R.id.ed_text);
        btn_edit = view.findViewById(R.id.btn_edit);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        /* Set up viewmodel in fragment,  obtain an instance of the ViewModel using */
//        UserDetailsViewModel viewModel = ViewModelProviders.of(this).get(UserDetailsViewModel.class); //depricated
        UserDetailsViewModel viewModel = new ViewModelProvider(this).get(UserDetailsViewModel.class);
        /*and observe the data changes using the LiveData returned by the ViewModel.*/
        /* Update your UI elements when the data changes.*/

        /*Handle lifecycle
         * Ensure that you handle the fragment's lifecycle properly to prevent memory leaks.
         * Use getViewLifecycleOwner() when observing LiveData to tie the observer's lifecycle to the fragment's view.
         */
        viewModel.getUserDetails(userId).observe(getViewLifecycleOwner(), datum -> {
            /* Update UI with the data received from the ViewModel */
            Log.d("User list>>>>", "===" + datum.getData().getFirstName());
            userData = datum;
            userName.setText("" + datum.getData().getFirstName() + " " + datum.getData().getLastName());
            email.setText("" + datum.getData().getEmail());
            url.setText("" + datum.getSupport().getUrl());
            text.setText("" + datum.getSupport().getText());

            //Used Glide For user profile Display
            Glide.with(requireContext())
                    .load(datum.getData().getAvatar())
                    .circleCrop()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
                    .into(img_user);


        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userEdit.user(userData.getData().getFirstName() + " " + userData.getData().getLastName(), userData.getData().getEmail(),
                        userData.getSupport().getUrl(), userData.getSupport().getText(), userData.getData().getAvatar());

            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            userEdit = (UserEdit) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement LogoutUser");
        }
    }

}