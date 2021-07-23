package com.example.atry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.atry.Adapter.FragmentAdapter;
import com.example.atry.databinding.ActivityMainBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth auth;
    ActivityMainBinding binding;
    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth= FirebaseAuth.getInstance();


        binding.viewpager.setAdapter(new FragmentAdapter(getSupportFragmentManager()));
        binding.tablayout.setupWithViewPager(binding.viewpager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull  MenuItem item) {

        switch (item.getItemId()){
            case R.id.setting:
                Toast.makeText(this, "setting is clicked", Toast.LENGTH_SHORT).show();
                break;

            case R.id.logout:
                auth.signOut();
                Intent intent =new Intent(MainActivity.this,SignInActivity.class);
                startActivity(intent);
                break;
        }


        return  true;
    }
}