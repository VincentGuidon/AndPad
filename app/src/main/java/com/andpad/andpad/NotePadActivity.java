package com.andpad.andpad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Vincent on 26/03/2017.
 */

public class NotePadActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent intent = getIntent();

        String Title = intent.getStringExtra("Title");
        String Content= intent.getStringExtra("Content");
        String Date= intent.getStringExtra("Date");

        setContentView(R.layout.notepad_activity);

        ((EditText) findViewById(R.id.TexxtViewTitle)).setText(Title);
        ((EditText) findViewById(R.id.TextViewContent)).setText(Content);
    }
}