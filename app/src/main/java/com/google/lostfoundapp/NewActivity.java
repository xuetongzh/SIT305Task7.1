package com.google.lostfoundapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.lostfoundapp.bean.Item;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class NewActivity extends AppCompatActivity {
    private List<Item> items = new ArrayList<>();
    private int itemId;

    private EditText name, phone, description, date, location;
    private RadioGroup radioGroup;
    private Button save;

    private String mName, mPhone, mDescription, mDate, mLocation, mType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        items = LitePal.findAll(Item.class);
        itemId = items.size() + 1;

        name = findViewById(R.id.name);
        phone = findViewById(R.id.phone);
        description = findViewById(R.id.description);
        date = findViewById(R.id.date);
        location = findViewById(R.id.location);
        radioGroup = findViewById(R.id.radio);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (radioGroup.getCheckedRadioButtonId()) {
                    case R.id.lost:
                        mType = "Lost";
                        break;
                    case R.id.found:
                        mType = "Found";
                        break;
                }
            }
        });

        save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mName = name.getText().toString();
                mPhone = phone.getText().toString();
                mDescription = description.getText().toString();
                mDate = date.getText().toString();
                mLocation = location.getText().toString();

                if (mName.isEmpty() || mPhone.isEmpty() || mDescription.isEmpty() || mDate.isEmpty() || mLocation.isEmpty() || mType.isEmpty()) {
                    Toast.makeText(NewActivity.this, "Please enter the complete content", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (new Item(itemId, mType, mName, mPhone, mDescription, mDate, mLocation).save()) {
                    Toast.makeText(NewActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(NewActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}