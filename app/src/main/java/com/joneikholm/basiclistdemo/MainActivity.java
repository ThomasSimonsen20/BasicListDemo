package com.joneikholm.basiclistdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.joneikholm.basiclistdemo.adapter.MyAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements UpdateAble {
    private ListView listView;
    public static int currentIndex = -1;
    private MyAdapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Repo.init(this);
        setupList();
    }

    private void setupList() {
        listView = findViewById(R.id.myList);
        myAdapter = new MyAdapter(this, Repo.getItems());
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener((AdapterView<?> adapterView, View view, int i, long l) -> {
            currentIndex = i;
            Intent intent = new Intent(this, DetailActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        myAdapter.notifyDataSetChanged();
    }

    public void addNotePressed(View view){
        Repo.createNote("New note");
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void update(Object o) {
        myAdapter.notifyDataSetChanged();
    }
}