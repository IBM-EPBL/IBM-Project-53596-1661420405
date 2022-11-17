package com.ibmproject.isifms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Login extends AppCompatActivity {
    Button signInBtn;
    EditText email, password;
    //    Firebase Methods Declarations
    FirebaseAuth auth;
    //    Basic Declarations
    String Email, Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signInBtn = (Button) findViewById(R.id.signInBtn);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);

        //        Firebase Initialize
        auth = FirebaseAuth.getInstance();

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Email = email.getText().toString();
                Password = password.getText().toString();
                auth.signInWithEmailAndPassword(Email, Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            if (Objects.requireNonNull(auth.getCurrentUser()).isEmailVerified()){
                                Toast.makeText(Login.this, "Login Success", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(Login.this, "Please Verify your Mail and Try Again!", Toast.LENGTH_SHORT).show();
                            }
                            FirebaseAuth.getInstance().signOut();
                        }else {
                            Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
