package com.abbyy.maddogs.maddogsindaplace;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by EvgenyShlykov on 07.10.2017, 007.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    public WordAdapter(Activity context, ArrayList<Word> words) {
        super(context, 0, words);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.word, parent, false);
        }
        final Word currentWord = getItem(position);

        listItemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick( View view) {
                Intent intent = new Intent(getContext(), WordDescription.class);
                Log.d("Diction", "intenting");
                intent.putExtra("word", currentWord.getBundle());
                getContext().startActivity(intent);
            }
        });

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getSrcWord());

        TextView russianTextView = (TextView) listItemView.findViewById(R.id.russian_text_view);
        russianTextView.setText(currentWord.getDstWord());

        Button deleteButton = (Button) listItemView.findViewById(R.id.delete_word);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataBase.getInstance(getContext()).delete(currentWord);
                WordAdapter.this.remove(currentWord);
            }
        });
        return listItemView;
    }


}
