package com.sandarva.medtrack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class ReminderAdapter extends FirestoreRecyclerAdapter<ReminderData, ReminderAdapter.MedicineHolder>{

    public ReminderAdapter(@NonNull FirestoreRecyclerOptions<ReminderData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull MedicineHolder holder, int position, @NonNull ReminderData model) {
        holder.textViewName.setText(model.getName());
        holder.textViewTime.setText(model.getTime());
        holder.textViewShape.setText(model.getShape());
        holder.textViewDescription.setText(model.getDescription());
    }

    @NonNull
    @Override
    public MedicineHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_list, parent, false);
        return new MedicineHolder(view);
    }

    public void deleteReminder(int position) {
        getSnapshots().getSnapshot(position).getReference().delete();
    }

    class MedicineHolder extends RecyclerView.ViewHolder{

        TextView textViewName;
        TextView textViewTime;
        TextView textViewShape;
        TextView textViewDescription;

        public MedicineHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_medName);
            textViewTime = itemView.findViewById(R.id.text_view_medTime);
            textViewShape = itemView.findViewById(R.id.text_view_shape);
            textViewDescription = itemView.findViewById(R.id.text_view_description);

        }
    }

}
