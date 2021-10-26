package com.joneikholm.basiclistdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class DetailActivity extends AppCompatActivity {

    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        editText = findViewById(R.id.editText);
        editText.setText(Repo.getItemOnIndex(MainActivity.currentIndex).getNote());
    }

    public void saveButtonPressed(View view){
        System.out.println("save pressed");
        String id = Repo.getItemOnIndex(MainActivity.currentIndex).getId();
        Note note = new Note(id,editText.getText().toString());
        Repo.updateNoteTxt(note);
    }

    public void deleteButtonPressed(View view) {
        String id = Repo.getItemOnIndex(MainActivity.currentIndex).getId();
        Repo.deleteNote(id);
        finish();
    }
}