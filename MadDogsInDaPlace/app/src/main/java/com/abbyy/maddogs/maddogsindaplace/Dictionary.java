package com.abbyy.maddogs.maddogsindaplace;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class Dictionary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        ArrayList<Word> words = new ArrayList<Word>() {{
            add(new Word("Hi", "Привет"));
            add(new Word("Buy", "Пока"));
        }};

        WordAdapter itemsAdapter = new WordAdapter(this, words);
        ListView listView = (ListView) findViewById(R.id.dictionary);
        listView.setAdapter(itemsAdapter);
    }
}
