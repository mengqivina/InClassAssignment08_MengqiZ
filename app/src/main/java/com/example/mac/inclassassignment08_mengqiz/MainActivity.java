package com.example.mac.inclassassignment08_mengqiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference myRef;
    EditText keyField;
    EditText valueField;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        keyField = (EditText) findViewById(R.id.key_edit_text_field);
        valueField = (EditText) findViewById(R.id.value_edit_text_field);
        database = FirebaseDatabase.getInstance();
    }

    public void writeToCloud(View view){
        myRef = database.getReference(keyField.getText().toString());
        myRef.setValue(valueField.getText().toString());
    }

    public void readFromCloud(View view){
        myRef = database.getReference(keyField.getText().toString());
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                String loadData = dataSnapshot.getValue(String.class);
                if(dataSnapshot.exists() == true) {
                    valueField.setText(loadData);
                } else{
                    Toast.makeText(MainActivity.this, "Error finding key", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError error){
                Toast.makeText(MainActivity.this, "Error loading Firebase", Toast.LENGTH_SHORT).show();
            }
        });
    }
}


