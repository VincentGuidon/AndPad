package com.andpad.andpad;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andpad.json.JsonHandle;
import com.andpad.json.PojoListNote;

/**
 * Created by Vincent on 26/03/2017.
 */

public class NotePadActivity extends Activity implements ColorPickerDialog.OnColorChangedListener{

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

    @Override
    public void colorChanged(int color) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.allActivityNotePad);
        ll.setBackgroundColor(color);
    }

    public void changeColor(View view) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.allActivityNotePad);
        ColorDrawable viewColor = (ColorDrawable) ll.getBackground();
        int colorId = viewColor.getColor();

        new ColorPickerDialog(NotePadActivity.this, NotePadActivity.this, colorId).show();
    }

    public void saveNote(View view) {
        JsonHandle.writeFile(getApplicationContext());
    }

    public void readNote(View view) {
        PojoListNote allNotes = JsonHandle.readFile(getApplicationContext());
        if (allNotes == null)
        {

        }
        else
        {
            ((EditText) findViewById(R.id.TextViewContent)).setText(allNotes.beau + allNotes.Name);
        }
    }
}