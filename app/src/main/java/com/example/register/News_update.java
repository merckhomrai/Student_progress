package com.example.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.register.databinding.ActivityDashboardBinding;
import com.example.register.databinding.ActivityNewsUpdateBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class News_update extends  drawerbase {
    AutoCompleteTextView mconfirm;
    FirebaseDatabase rootNode;
    DatabaseReference reference,databaseReference;
    ActivityNewsUpdateBinding activityNewsUpdateBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityNewsUpdateBinding= ActivityNewsUpdateBinding.inflate(getLayoutInflater());
        setContentView(activityNewsUpdateBinding.getRoot());
        allocateActivityTitle("Dashboard");

        mconfirm = findViewById(R.id.coursem);
        Button sub1=findViewById(R.id.sub);
        EditText teu1=findViewById(R.id.teu);

        String[] location= {"Academics","Sports","Graduation","Exams","Cats"};

        ArrayAdapter<String> ss = new ArrayAdapter<String>(this,R.layout.dropdownlocation,location);
        ss.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mconfirm.setAdapter(ss);

        mconfirm.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String location2=adapterView.getItemAtPosition(i).toString();

            }
        });
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_students);

        bottomNavigationView.setSelectedItemId(R.id.news_update);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.fee_update:
                        //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return  true;

                    case R.id.students:
                        startActivity(new Intent(getApplicationContext(),register_students.class));
                        overridePendingTransition(0,0);
                        return  true;
                }
                return false;
            }
        });

        String course=mconfirm.getText().toString().trim();
        String new1=teu1.getText().toString().trim();

        sub1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("news");
                news userhelper = new news(course,new1);
                reference.child(course).setValue(userhelper);
                Toast.makeText(News_update.this, "Student Added successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}