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

public class appointment_view_list extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView appointment_patient_list;
    private FirestoreRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_view_list);

        firebaseFirestore = FirebaseFirestore.getInstance();
        appointment_patient_list =  findViewById(R.id.appointment_patient_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Appointment List");

        //Query
        Query query = firebaseFirestore.collection("appointment");

        //RecyclerOptions
        FirestoreRecyclerOptions<Appointment_List> options = new FirestoreRecyclerOptions.Builder<Appointment_List>().setQuery(query, Appointment_List.class).build();

        adapter = new FirestoreRecyclerAdapter<Appointment_List, appointment_view_list.UsersViewsHolder>(options) {
            @NonNull
            @Override
            public appointment_view_list.UsersViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.appointmet_patients_list, parent,false);

                return new appointment_view_list.UsersViewsHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull appointment_view_list.UsersViewsHolder holder, int position, @NonNull Appointment_List model) {

                holder.user_name.setText(model.getFirstName());
                holder.user_last_name.setText(model.getSecondName());
                holder.user_phone_number.setText(model.getPhoneNumber());
                holder.user_email.setText(model.getEmailAddress());
                holder.user_date.setText(model.getAppointmentDate());
                holder.user_time.setText(model.getTime());

            }
        };

        appointment_patient_list.setHasFixedSize(true);
        appointment_patient_list.setLayoutManager(new LinearLayoutManager(this));
        appointment_patient_list.setAdapter(adapter);
    }

    private class UsersViewsHolder extends RecyclerView.ViewHolder {

        private TextView user_name;
        private TextView user_last_name;
        private TextView user_phone_number;
        private TextView user_email;
        private TextView user_date;
        private TextView user_time;


        public UsersViewsHolder(@NonNull View itemView) {
            super(itemView);

            user_name = itemView.findViewById(R.id.user_name);
            user_last_name = itemView.findViewById(R.id.user_last_name);
            user_phone_number = itemView.findViewById(R.id.user_phone_number);
            user_email = itemView.findViewById(R.id.user_email);
            user_date = itemView.findViewById(R.id.user_date);
            user_time = itemView.findViewById(R.id.user_time);
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

