package com.example.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.register.databinding.ActivityDashboardBinding;
import com.example.register.databinding.ActivityRegisterStudentsBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class register_students extends drawerbase {
    ActivityRegisterStudentsBinding activityRegisterStudentsBinding;
    Button button;
    EditText mFullName, mEmail, mPassword, mPhone,mconfirm2,mid;
    AutoCompleteTextView mconfirm;
    FirebaseDatabase rootNode;
    DatabaseReference reference,databaseReference;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRegisterStudentsBinding = ActivityRegisterStudentsBinding.inflate(getLayoutInflater());
        setContentView(activityRegisterStudentsBinding.getRoot());
        allocateActivityTitle("Register");

        mFullName = findViewById(R.id.mFullName);
        mEmail = findViewById(R.id.mEmail);
        mPassword = findViewById(R.id.password);
        mconfirm = findViewById(R.id.course);
        mid = findViewById(R.id.Reg_no);
        mPhone = findViewById(R.id.mPhone);
        button = findViewById(R.id.buttonl1);
        fAuth = FirebaseAuth.getInstance();

        String[] location= {"BCS","BMT","BCSF","BIT","BBIT","BED"};

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

        bottomNavigationView.setSelectedItemId(R.id.students);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.fee_update:
                        //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return  true;

                    case R.id.news_update: startActivity(new Intent(getApplicationContext(),News_update.class));
                        overridePendingTransition(0,0);
                        return  true;
                }
                return false;
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {




                String fullname=mFullName.getText().toString().trim();
                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();
                String phone=mPhone.getText().toString().trim();
                String course=mconfirm.getText().toString().trim();
                String id=mid.getText().toString().trim();



                if (TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    mPassword.setError("password is required");
                    return;
                }
                if (mPassword.length() < 6) {
                    mPassword.setError(("password must be>=6 characters"));
                    return;
                }
                if(TextUtils.isEmpty(id)) {
                    mid.setError("please enter id number");

                    return;
                }
                if(mid.length()!=8){
                    mid.setError("please enter valid id number");
                    return;
                }
                /*if(mid.length()==8 && mPhone.length()==10){
                    databaseReference.orderByChild("id").equalTo(id).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.exists()){
                                Toast.makeText(register.this, "id number already exist", Toast.LENGTH_SHORT).show();
                                mid.setError("id number already exist");

                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                    return;
                }*/
                if(TextUtils.isEmpty(phone)) {
                    mPhone.setError("please enter phone number");
                    return;
                }
                if(mPhone.length()!=10){
                    mPhone.setError("please enter valid phone number");
                    return;
                }




                if(TextUtils.isEmpty(fullname)) {
                    mFullName.setError("please enter your full names");
                }

                else {

                   // progressBar.setVisibility(View.VISIBLE);
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {

                                                rootNode = FirebaseDatabase.getInstance();
                                                reference = rootNode.getReference("users");
                                                String Uid = task.getResult().getUser().getUid();
                                                userhelper userhelper = new userhelper(Uid, fullname, email, password, phone, course, id, 0, "","0ksh","0%","0%","0 units");
                                                reference.child(Uid).setValue(userhelper);
                                                Toast.makeText(register_students.this, "Student Added successfully", Toast.LENGTH_SHORT).show();
                                            }

                            else {
                                Toast.makeText(register_students.this, "Error" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);

                            }

                        }
                    });
                }
            }
        });
    }
}