package com.google.lostfoundapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.lostfoundapp.adapter.ItemAdapter;
import com.google.lostfoundapp.bean.Item;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class AllActivity extends AppCompatActivity {
    private RecyclerView allRec;
    private ItemAdapter itemAdapter;
    private List<Item> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        items = LitePal.findAll(Item.class);
        allRec = findViewById(R.id.allRec);
        allRec.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        itemAdapter = new ItemAdapter(AllActivity.this, items);
        allRec.setAdapter(itemAdapter);
    }
}