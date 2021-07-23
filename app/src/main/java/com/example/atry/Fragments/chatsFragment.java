package com.example.atry.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.atry.Adapter.UserAdapter;
import com.example.atry.User;
import com.example.atry.databinding.FragmentChatsBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class chatsFragment extends Fragment {


    public chatsFragment() {
        // Required empty public constructor
    }


    FragmentChatsBinding binding;
    ArrayList<User> list=new ArrayList<>();
     FirebaseDatabase database;
    FirebaseAuth auth;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         binding = FragmentChatsBinding.inflate(inflater, container, false);
         database=FirebaseDatabase.getInstance();

        UserAdapter adapter=new UserAdapter(getContext(),list);
        binding.chatRecycleview.setAdapter(adapter);
        LinearLayoutManager linearlayout=new LinearLayoutManager(getContext());
        binding.chatRecycleview.setLayoutManager(linearlayout);

        database.getReference().child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull @NotNull DataSnapshot snapshot) {
                list.clear();
                Log.d("LOg1","line 65");
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Log.d("LOg2","line 66");
                    User user=dataSnapshot.getValue(User.class);

                    Log.d("LOg3","line 68");
                    assert user != null;
                    user.setUserId(dataSnapshot.getKey());
                   list.add(user);

                }
                adapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull @NotNull DatabaseError error) {

            }
        });



         return binding.getRoot();
    }
}