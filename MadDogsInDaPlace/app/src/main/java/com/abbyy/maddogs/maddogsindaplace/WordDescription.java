package com.abbyy.maddogs.maddogsindaplace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WordDescription extends AppCompatActivity {
    TextView tvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_description);

        Intent intent = getIntent();
        Word word = new Word(intent.getBundleExtra("word"));

        tvView = (TextView) findViewById(R.id.srcWord);
        tvView.setText(word.getSrcWord());
        tvView = (TextView) findViewById(R.id.languages);
        //tvView.setText(word.getSrcLang().toString() + " - " + word.getDstLang().toString());
        tvView.setText("ENG - RUS");
        tvView = (TextView) findViewById(R.id.translation);
        tvView.setText(word.getDstWord());

        Button button = (Button) findViewById(R.id.backArrow);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WordDescription.this.finish();
            }
        });
    }

}
