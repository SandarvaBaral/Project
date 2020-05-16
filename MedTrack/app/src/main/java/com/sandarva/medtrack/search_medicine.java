package com.sandarva.medtrack;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;



public class search_medicine extends AppCompatActivity {

    private FirebaseFirestore firebaseFirestore;
    private RecyclerView fireSearchList;
    private FirestoreRecyclerAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_medicine);

        firebaseFirestore = FirebaseFirestore.getInstance();
        fireSearchList =  findViewById(R.id.fireSearchList);

        setTitle("Medicine Library");

        //Query
        Query query = firebaseFirestore.collection("product");

        //RecyclerOptions
        FirestoreRecyclerOptions<ProductList> options = new FirestoreRecyclerOptions.Builder<ProductList>().setQuery(query, ProductList.class).build();

        adapter = new FirestoreRecyclerAdapter<ProductList, search_medicine.UsersViewsHolder>(options) {
            @NonNull
            @Override
            public search_medicine.UsersViewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.searchmed, parent,false);

                return new search_medicine.UsersViewsHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull search_medicine.UsersViewsHolder holder, int position, @NonNull ProductList model) {

                holder.medName.setText(model.getName());
                holder.medManufacturer.setText(model.getManufacture());
                holder.medDescrip.setText(model.getDescription());
                holder.medShape.setText(model.getShape());
                holder.medDosage.setText(model.getDosage());

            }

        };

        fireSearchList.setHasFixedSize(true);
        fireSearchList.setLayoutManager(new LinearLayoutManager(this));
        fireSearchList.setAdapter(adapter);

    }

    private class UsersViewsHolder extends RecyclerView.ViewHolder {

        private TextView medName;
        private TextView medManufacturer;
        private TextView medDescrip;
        private TextView medShape;
        private TextView medDosage;

        public UsersViewsHolder(@NonNull View itemView) {
            super(itemView);


            medName = itemView.findViewById(R.id.medName);
            medManufacturer = itemView.findViewById(R.id.medManufacturer);
            medDescrip = itemView.findViewById(R.id.medDescrip);
            medShape = itemView.findViewById(R.id.medShape);
            medDosage = itemView.findViewById(R.id.medDosage);
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

