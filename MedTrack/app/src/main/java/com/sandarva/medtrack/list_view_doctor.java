package com.sandarva.medtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class list_view_doctor extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView doctorList;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_doctor);

        firebaseFirestore = FirebaseFirestore.getInstance();
        doctorList =  findViewById(R.id.doctorList);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Doctor List");

        //Query
        Query query = firebaseFirestore.collection("doctors");

        //RecyclerOptions
        FirestoreRecyclerOptions<UsersDoctor> options = new FirestoreRecyclerOptions.Builder<UsersDoctor>().setQuery(query, UsersDoctor.class).build();

        adapter = new FirestoreRecyclerAdapter<UsersDoctor, list_view_doctor.UsersViewsHolder>(options) {
            @NonNull
            @Override
            public list_view_doctor.UsersViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_doctor, parent,false);

                return new list_view_doctor.UsersViewsHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull list_view_doctor.UsersViewsHolder holder, int position, @NonNull UsersDoctor model) {

                holder.listdoctorName.setText(model.getFirstName());
                holder.docPhoneNumber.setText(model.getPhNumber());

            }
        };

        doctorList.setHasFixedSize(true);
        doctorList.setLayoutManager(new LinearLayoutManager(this));
        doctorList.setAdapter(adapter);

    }

    private class UsersViewsHolder extends RecyclerView.ViewHolder {

        private TextView listdoctorName;
        private TextView docPhoneNumber;


        public UsersViewsHolder(@NonNull View itemView) {
            super(itemView);

            listdoctorName = itemView.findViewById(R.id.listdoctorName);
            docPhoneNumber = itemView.findViewById(R.id.docPhoneNumber);
        }
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }
}
