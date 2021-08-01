package com.example.atry.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.atry.R;
import com.example.atry.User;
import com.example.atry.chatDetailActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.viewHolder> {

    Context context;
    ArrayList<User> list;

    public UserAdapter(Context context, ArrayList<User> list) {
        this.context = context;
        this.list = list;
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        ImageView  image;
        TextView username;
        TextView lastChat;

        public viewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
             image =itemView.findViewById(R.id.profile_image);
             username =itemView.findViewById(R.id.chatuser_name_tv);
             lastChat =itemView.findViewById(R.id.lastchat_tv);
            ;
        }
    }

    @NonNull
    @NotNull
    @Override
    public  viewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType){

        View view = LayoutInflater.from(context).inflate(R.layout.sample_user_chat,parent,false);
        return new viewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull UserAdapter.viewHolder holder, int position) {

        User user = list.get(position);
        Picasso.get().load(user.getProfile()).placeholder(R.drawable.avatar).into(holder.image);
        holder.username.setText(user.getUsername());

        FirebaseDatabase.getInstance().getReference().child("chats")
                .child(FirebaseAuth.getInstance().getUid()+user.getUserId())
                .orderByChild("timestamp").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        holder.lastChat.setText(dataSnapshot.child("message").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, chatDetailActivity.class);
                intent.putExtra("username",user.getUsername());
                intent.putExtra("userId",user.getUserId());
                intent.putExtra("profilePic",user.getProfile());
                context.startActivity(intent);
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
