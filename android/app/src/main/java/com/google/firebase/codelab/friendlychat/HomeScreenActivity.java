package com.google.firebase.codelab.friendlychat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import static android.content.ContentValues.TAG;

public class HomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        configureSignUpButton();
        configureLogInButton();
    }

    private void configureLogInButton() {
        Button LogIn = (Button) findViewById(R.id.LogInButton);
        LogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //ADD FUNCTIONALITY TO PULL USER DATA FROM FIREBASE
                startActivity(new Intent(HomeScreenActivity.this, MainActivity.class));
            }
        });
    }

    private void configureSignUpButton() {
        Button SignUp = (Button) findViewById(R.id.SignUpButton);
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addUserToSystem();
            }
        });
    }

    private void addUserToSystem() {
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        String theEmail = ((EditText) findViewById(R.id.userEmailText)).getText().toString();
        String thePassword = ((EditText) findViewById(R.id.userPasswordText)).getText().toString();
        mAuth.createUserWithEmailAndPassword(theEmail, thePassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "createUserWithEmail:success");
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.getException());
                }
            }
        });
        startActivity(new Intent(HomeScreenActivity.this, MainActivity.class));
    }
}
