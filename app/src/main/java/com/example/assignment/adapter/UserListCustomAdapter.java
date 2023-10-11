package com.example.assignment.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignment.R;
import com.example.assignment.model.Datum;
import com.example.assignment.view.onItemUserclick;

import java.util.List;

public class UserListCustomAdapter extends RecyclerView.Adapter<UserListCustomAdapter.CustomViewHolder> {

    private List<Datum> dataList;
    private Context context;
    private onItemUserclick onItemUserclick;

    public UserListCustomAdapter(Context context, List<Datum> dataList, onItemUserclick onItemUserClick) {
        this.context = context;
        this.dataList = dataList;
        this.onItemUserclick = onItemUserClick;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {

        public final View mView;

        TextView txtName;
        TextView txtMobileNumber;
        private ImageView userImage;

        CustomViewHolder(View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.tv_name);
            txtMobileNumber = mView.findViewById(R.id.tv_mobilenum);
            userImage = mView.findViewById(R.id.user_img);
        }
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtName.setText(dataList.get(position).getFirstName() + " " + dataList.get(position).getLastName());
        holder.txtMobileNumber.setText(dataList.get(position).getEmail());

//        Used Glide for display the users avatar
        Glide.with(context)
                .load(dataList.get(position).getAvatar())
                .circleCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.userImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemUserclick.OnItemUserClick(dataList.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
