package com.google.lostfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.lostfoundapp.bean.Item;

import org.litepal.LitePal;

public class RemoveActivity extends AppCompatActivity {
    private int itemId;
    private Item item;
    private Button remove;
    private TextView removeTv;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        itemId = (int) getIntent().getExtras().get("itemId");
        item = LitePal.where("itemId = ?", String.valueOf(itemId)).find(Item.class).get(0);

        removeTv = findViewById(R.id.removeTv);
        removeTv.setText(item.getType() + " " + item.getName() + " \n\n" + item.getDate() + " \n\nAt " + item.getLocation());

        remove = findViewById(R.id.remove);
        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.delete() > 0) {
                    Toast.makeText(RemoveActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    finish();
                }
            }
        });
    }
}