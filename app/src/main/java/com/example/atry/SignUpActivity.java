package com.example.atry;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.atry.databinding.ActivitySignUpBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Objects;


public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    FirebaseAuth auth;
    DatabaseReference database;
    ProgressDialog progressDialog;
    Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        //Firebase
        auth= FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance().getReference();

        //progressBar
        progressDialog = new ProgressDialog(SignUpActivity.this);
        progressDialog.setTitle("Please wait");
        progressDialog.setMessage("your account is creating");

        binding.RegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();

                registeruser(binding.usernameTextView.getText().toString(),
                        binding.emailTextView.getText().toString(),binding.passwordTextView.getText().toString());


            }
        });

        binding.olduserTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(SignUpActivity.this,SignInActivity.class);
                startActivity(intent);
            }
        });
        if(auth.getCurrentUser()!=null){
            Intent intent =new Intent(SignUpActivity.this,MainActivity.class);
            startActivity(intent);

        }


    }

    private void registeruser(String Uname, String UMail, String Upass) {
        auth.createUserWithEmailAndPassword(UMail,Upass).
                addOnSuccessListener(new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {

                User user=new User( UMail,Uname,Upass,auth.getCurrentUser().getUid());
                database.child("User").child(auth.getCurrentUser().getUid()).setValue(user)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        progressDialog.dismiss();
                        if(task.isSuccessful())
                        {
                            Toast.makeText( SignUpActivity.this, "Account Created SuccessFully",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                progressDialog.dismiss();
                Toast.makeText( SignUpActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}