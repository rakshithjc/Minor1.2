package com.example.minor12;

import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Reg_Activity extends AppCompatActivity {
    FirebaseAuth mFirebaseAuth;
    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText first_name,last_name,date,phone;
    Button submitbut;
    FirebaseUser user;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg_);
        mFirebaseAuth = FirebaseAuth.getInstance();
        first_name=findViewById(R.id.firstname);
        last_name=findViewById(R.id.lastname);
        phone=(EditText)findViewById(R.id.editTextPhone);
        date=(EditText)findViewById(R.id.editTextDate);
        submitbut=findViewById(R.id.button);
        user=mFirebaseAuth.getCurrentUser();
        submitbut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name1=first_name.getText().toString();
                String name2=last_name.getText().toString();
                String dob=date.getText().toString();
                String ph= phone.getText().toString();
                String uid =user.getUid();
                //mail = email.substring(0, email.indexOf('@'));
                if(name1.isEmpty() || name2.isEmpty() || dob.isEmpty() || ph.isEmpty()){
                    Toast.makeText(Reg_Activity.this, "Fields are empty!", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(user!=null){
                        database = FirebaseDatabase.getInstance();
                        myRef = database.getReference(uid);
                        myRef.child("First_Name").setValue(name1);
                        myRef.child("Last_Name").setValue(name2);
                        myRef.child("Date_Of_Birth").setValue(dob);
                        myRef.child("Phone_Number").setValue(ph);
                        myRef.child("Balance").setValue("0");
                        myRef.child("Battery").setValue("0");
                        myRef.child("Last_recharge").setValue("0");
                        myRef.child("Range").setValue("0");
                        startActivity(new Intent(Reg_Activity.this, HomeActivity.class));

                    }
                }
            }
        });




    }
}