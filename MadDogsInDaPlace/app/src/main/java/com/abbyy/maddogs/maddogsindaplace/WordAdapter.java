package com.abbyy.maddogs.maddogsindaplace;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.word, parent, false);
        }

        Word currentWord = getItem(position);

//        View listItem = listItemView.findViewById(R.id.list_item);

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.default_text_view);
        defaultTextView.setText(currentWord.getWord());

        TextView russianTextView = (TextView) listItemView.findViewById(R.id.russian_text_view);
        russianTextView.setText(currentWord.getInRussian());

        return listItemView;
    }
}
