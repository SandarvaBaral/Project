package com.sandarva.medtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Reminder extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private ReminderAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        FloatingActionButton openReminderApp = findViewById(R.id.add_medicine);

        firebaseFirestore = FirebaseFirestore.getInstance();

        setUpRecyclerView();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setTitle("Reminder");

        openReminderApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Reminder.this, ReminderSetting.class ));
            }
        });
    }

    private void setUpRecyclerView(){
        Query query = firebaseFirestore.collection("reminder");

        FirestoreRecyclerOptions<ReminderData> options = new FirestoreRecyclerOptions.Builder<ReminderData>().setQuery(query, ReminderData.class).build();

        adapter = new ReminderAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.reminder_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                adapter.deleteReminder(viewHolder.getAdapterPosition());

            }
        }).attachToRecyclerView(recyclerView);
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
