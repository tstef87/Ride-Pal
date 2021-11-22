package com.example.ride_pal_real.ui.rides;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ride_pal_real.R;
import com.example.ride_pal_real.ui.AccountInfoActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;


public class CreateRide extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private CheckBox mday, tuday, wday, thday, fday;
    private TextView dis, time;
    private Button createRide, back;
    private Spinner spinner;
    private String spinnerResult;
    private FirebaseAuth mAuth;





    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_ride);

        mAuth = FirebaseAuth.getInstance();
        mday = findViewById(R.id.monday_cb);
        tuday = findViewById(R.id.tuesday_cb);
        wday = findViewById(R.id.wednesday_cb);
        thday = findViewById(R.id.thursday_cb);
        fday = findViewById(R.id.firday_cb);
        time = findViewById(R.id.editTextTime);
        dis = findViewById(R.id.discription_et);

        createRide = findViewById(R.id.create_ride_button);
        back = findViewById(R.id.back_button_create_ride);

        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.parking_lots, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        Button create = (Button) findViewById(R.id.create_ride_button);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        Button back = (Button) findViewById(R.id.back_button_create_ride);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CreateRide.this, AccountInfoActivity.class));
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
        spinnerResult = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(),text, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }

    private boolean tof(CheckBox x){

        return x.isChecked();
    }

    private void register(){

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference ref = database.getReference("Rides");

        String t = time.getText().toString().trim();
        String d = dis.getText().toString().trim();
        String dest = spinnerResult;
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        boolean tue = tof(tuday);
        boolean mond = tof(mday);
        boolean wed = tof(wday);
        boolean thu = tof(thday);
        boolean fri = tof(fday);


        if (t.isEmpty()){
            time.setError("time can not be empty");
            time.requestFocus();
            return;
        }
        if (d.isEmpty()){
            dis.setError("discription can not be empty");
            dis.requestFocus();
            return;
        }

        FirebaseDatabase.getInstance().getReference("Rides")
                .child(d)
                .setValue(new Rides(user.getUid(), t, dest, mond, tue, wed, thu, fri));

    }


}