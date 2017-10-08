package com.abbyy.maddogs.maddogsindaplace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class Dictionary extends Activity {
    private GestureDetector gestureDetector;
    View.OnTouchListener gestureListener;
    WordAdapter itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);

        //DataBase.getInstance(getApplicationContext()).addWord(new Word("azaz", "лел", 14, 88));
        ArrayList<Word> words = DataBase.getInstance(getApplicationContext()).getWords();



        Button button = (Button) findViewById(R.id.addWord);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dictionary.this, MainActivity.class);
                startActivity(intent);
            }
        });
        itemsAdapter = new WordAdapter(this, new ArrayList<Word>(0));
        ListView listView = (ListView) findViewById(R.id.dictionary);
        listView.setAdapter(itemsAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(Dictionary.this, WordDescription.class);
                Word word = (Word) adapterView.getAdapter().getItem(i);
                intent.putExtra("word", word.getBundle());
                startActivity(intent);
            }
        });


        gestureDetector = new GestureDetector(new SwipeGestureDetector());
        gestureListener = new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetector.onTouchEvent(event);
            }
        };
        listView.setOnTouchListener(gestureListener);
    }

    private void onLeftSwipe() {
        Intent intent = new Intent(Dictionary.this, MainActivity.class);
        startActivity(intent);
    }

    private class SwipeGestureDetector extends GestureDetector.SimpleOnGestureListener {
        private static final int SWIPE_MIN_DISTANCE = 50;
        private static final int SWIPE_MAX_OFF_PATH = 200;
        private static final int SWIPE_THRESHOLD_VELOCITY = 200;

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
                               float velocityY) {
            try {
                float diffAbs = Math.abs(e1.getY() - e2.getY());
                float diff = e1.getX() - e2.getX();

                if (diffAbs > SWIPE_MAX_OFF_PATH)
                    return false;

                // Left swipe
                if (diff > SWIPE_MIN_DISTANCE
                        && Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
                    Dictionary.this.onLeftSwipe();
                }
            } catch (Exception e) {
                Log.e("Home", "Error on gestures");
            }
            return false;
        }
    }

    @Override
    protected void onResume() {
        ArrayList<Word> words = DataBase.getInstance(getApplicationContext()).getWords();
        itemsAdapter.clear();
        itemsAdapter.addAll(words);
        super.onResume();
    }
}
