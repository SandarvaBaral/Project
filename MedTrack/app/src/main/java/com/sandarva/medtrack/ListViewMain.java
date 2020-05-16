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

public class ListViewMain extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView patientsList;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_main);

        firebaseFirestore = FirebaseFirestore.getInstance();
        patientsList =  findViewById(R.id.patientsList);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Patients");

        //Query
        Query query = firebaseFirestore.collection("patients");

        //RecyclerOptions
        FirestoreRecyclerOptions<Users>options = new FirestoreRecyclerOptions.Builder<Users>().setQuery(query, Users.class).build();

         adapter = new FirestoreRecyclerAdapter<Users, UsersViewsHolder>(options) {
            @NonNull
            @Override
            public UsersViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent,false);

                return new UsersViewsHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull UsersViewsHolder holder, int position, @NonNull Users model) {

                holder.listName.setText(model.getfName());

            }
        };

         patientsList.setHasFixedSize(true);
         patientsList.setLayoutManager(new LinearLayoutManager(this));
         patientsList.setAdapter(adapter);

    }

    private class UsersViewsHolder extends RecyclerView.ViewHolder {

        private TextView listName;


        public UsersViewsHolder(@NonNull View itemView) {
            super(itemView);

            listName = itemView.findViewById(R.id.listName);
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
