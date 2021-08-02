package com.biijay.firebaselogin;

import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    EditText email,password;
    Button register;

    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Toast.makeText(MainActivity.this,"Already In", Toast.LENGTH_SHORT).show();
    }

    // Make sure to check email and password (empty and null)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();

    }

    public void onRegister(View view){

        String mEmail = email.getText().toString();
        String mPass = password.getText().toString();

        mAuth.createUserWithEmailAndPassword(mEmail, mPass)
                .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("TAG", "createUserWithEmail:success");
//                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(MainActivity.this,"Success", Toast.LENGTH_SHORT).show();

                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.i("TAG", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

    public void onLogin(View view){

        String mEmail = email.getText().toString();
        String mPass = password.getText().toString();

        mAuth.createUserWithEmailAndPassword(mEmail, mPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("Tag", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            String userID = user.getUid().toString();
                            Toast.makeText(MainActivity.this,"Auth Success", Toast.LENGTH_SHORT).show();
                            Log.i("USER", "USER: " + user.toString());
                            Log.i("USER", "USER: " + userID);


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("Tag", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this,"Auth Failed", Toast.LENGTH_SHORT).show();

                        }

                        // ...
                    }
                });

    }

    public void onLogOut(View view){
        FirebaseAuth.getInstance().signOut();
        Toast.makeText(MainActivity.this,"Sign Out", Toast.LENGTH_SHORT).show();
    }



}
