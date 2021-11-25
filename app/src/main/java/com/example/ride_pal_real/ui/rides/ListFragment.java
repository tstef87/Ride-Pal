package com.example.ride_pal_real.ui.rides;

import android.app.Activity;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.example.ride_pal_real.R;
import com.example.ride_pal_real.databinding.FragmentListBinding;
import com.example.ride_pal_real.ui.AccountInfoActivity;
import com.example.ride_pal_real.ui.map.MapsFragment;
import com.example.ride_pal_real.ui.rides.create.CreateRide;
import com.example.ride_pal_real.ui.rides.create.Rides;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ListFragment extends Fragment {

    ArrayAdapter<String> adapter;
    ArrayList<String> data = new ArrayList<String>();
    ListView listView;
    SearchView searchView;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        //DatabaseReference ref = database.getReference("Rides");
        //ArrayList<Rides> ridesArrayList = new ArrayList<>();
        //HashMap<String, String> hmap = new HashMap<>();


        View view = inflater.inflate(R.layout.fragment_list, container, false);


        FirebaseDatabase.getInstance().getReference().child("Rides")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String s = snapshot.child("destination").getValue().toString();
                            s += "- ";
                            s += snapshot.child("time").getValue().toString();
                            s += ": ";

                            if(snapshot.child("monday").getValue().toString().equals("true")){ s += "Monday ";}
                            if(snapshot.child("tuesday").getValue().toString().equals("true")){ s += "Tuesday ";}
                            if(snapshot.child("wednesday").getValue().toString().equals("true")){ s += "Wednesday ";}
                            if(snapshot.child("thursday").getValue().toString().equals("true")){ s += "Thursday ";}
                            if(snapshot.child("friday").getValue().toString().equals("true")){ s += "Friday ";}

                            data.add(s);
                        }

                        listView = (ListView) view.findViewById(R.id.listview);
                        adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, data);
                        listView.setAdapter(adapter);




                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        return view;

    }

    @Override
    public void onStart(){
        super.onStart();



    }


    private void moveToNewActivity (){

        Intent i = new Intent(getActivity(), CreateRide.class);
        startActivity(i);
        ((Activity)getActivity()).overridePendingTransition(0,0);
    }

}