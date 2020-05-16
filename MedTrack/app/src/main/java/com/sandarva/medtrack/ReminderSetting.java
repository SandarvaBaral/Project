package com.sandarva.medtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ReminderSetting extends AppCompatActivity {

    private EditText mEditTextName, mEditTextTime, mEditTextShape, mEditTextDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder_setting);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        mEditTextName = findViewById(R.id.edit_medicine_name);
        mEditTextTime = findViewById(R.id.edit_medicine_time);
        mEditTextShape = findViewById(R.id.edit_medicine_shape);
        mEditTextDescription = findViewById(R.id.edit_medicine_description);
        setTitle("Add Reminder");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.medtrack_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //Checks if the save icon has been clicked
        switch (item.getItemId()){
            case R.id.save_medicine:
                //Takes whats in the edit text and upload it in the google FireBase
                saveMedicine();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveMedicine() {

        //Get the Text from the edit text
        String name = mEditTextName.getText().toString();
        String time = mEditTextTime.getText().toString();
        String shape = mEditTextShape.getText().toString();
        String description = mEditTextDescription.getText().toString();

        //Removes empty spaces from start to end | prevents users leaving empty spaces
        if (name.trim().isEmpty() || time.trim().isEmpty() || shape.trim().isEmpty() || description.trim().isEmpty() ){
            Toast.makeText(this, "Please Check If Details Are Filled In", Toast.LENGTH_SHORT).show();
            //Continues if user wants to leave the field empty
            return;
        }

        CollectionReference reminderReference = FirebaseFirestore.getInstance().collection("reminder");
        reminderReference.add(new ReminderData(name, time, shape, description));
        Toast.makeText(this, "Reminder Added", Toast.LENGTH_SHORT).show();
        finish();

    }
}
