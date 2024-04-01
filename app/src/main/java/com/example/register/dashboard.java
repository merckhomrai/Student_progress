package com.example.register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.register.databinding.ActivityDashboardBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class dashboard extends drawerbase {
    FirebaseUser user;
    DatabaseReference reference, reference2;
    ActivityDashboardBinding activityDashboardBinding;
    FirebaseDatabase rootNode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDashboardBinding = ActivityDashboardBinding.inflate(getLayoutInflater());
        setContentView(activityDashboardBinding.getRoot());
        allocateActivityTitle("Dashboard");

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        String userid = user.getUid();

        final TextView fullTextview = findViewById(R.id.REG);
        final TextView feeTextview = findViewById(R.id.fee);
        final TextView catTextview = findViewById(R.id.cat);
        final TextView examTextview = findViewById(R.id.exam);
        final TextView unitTextview = findViewById(R.id.unit);

         TextView cat=findViewById(R.id.catR);
         TextView exam=findViewById(R.id.examR);
         TextView unit=findViewById(R.id.unitR);
         TextView fee=findViewById(R.id.feeR);
         EditText enq=findViewById(R.id.enquire);
          Button sub=findViewById(R.id.submit1);



        reference.child(userid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                userhelper userprofile=snapshot.getValue(userhelper.class);
                if(userprofile!=null){

                    String name=userprofile.fullname;
                    String fee =userprofile.fee;
                    String  cat= userprofile.cat;
                    String exam=userprofile.exam;
                    String unit=userprofile.unit;
                       /*try {
                            Picasso.get()
                                    .load(link)
                                    .into(imageView);
                        }
                        catch (Exception e){

                        }*/
                    fullTextview.setText(name);
                    feeTextview.setText(fee);
                    examTextview.setText(exam);
                    catTextview.setText(cat);
                    unitTextview.setText(unit);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
               Toast.makeText(dashboard.this, "something wrong happened", Toast.LENGTH_SHORT).show();
            }
        });

        String eq=enq.getText().toString().trim();

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("news");
                Enquiries enqure = new Enquiries(eq);
                Toast.makeText(dashboard.this, "Student Added successfully", Toast.LENGTH_SHORT).show();
            }
        });

    }
}