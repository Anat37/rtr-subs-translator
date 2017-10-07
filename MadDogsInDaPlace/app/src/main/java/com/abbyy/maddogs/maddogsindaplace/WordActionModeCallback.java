package com.abbyy.maddogs.maddogsindaplace;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.graphics.Typeface;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by kozlo on 07.10.2017.
 */

public class WordActionModeCallback implements ActionMode.Callback {

    EditText bodyView;
    MainActivity context;

    WordActionModeCallback(EditText parentView, MainActivity context) {
        this.context = context;
        bodyView = parentView;
    }

    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
        Log.d("ActionMode", "create");
        MenuInflater inflater = mode.getMenuInflater();
        inflater.inflate(R.menu.action_mode_layout, menu);
        return true;
    }

    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
        Log.d("ActionMode", "prepare");
        mode.setTitle("CheckBox is Checked");
        return true;
    }



    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
        Log.d("ActionMode", "click");
        CharacterStyle cs;
        int start = bodyView.getSelectionStart();
        int end = bodyView.getSelectionEnd();

        switch(item.getItemId()) {

            case R.id.cab_add:
                Toast.makeText(context,bodyView.getText(), Toast.LENGTH_LONG);
                return true;
        }
        return false;
    }

    public void onDestroyActionMode(ActionMode mode) {
        context.mActionMode = null;
    }
}
