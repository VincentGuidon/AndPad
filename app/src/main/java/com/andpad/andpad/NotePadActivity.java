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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Vincent on 26/03/2017.
 */

public class NotePadActivity extends Activity implements ColorPickerDialog.OnColorChangedListener{

    private PojoListNote    listNote;
    private int             position;
    private String          Title;
    private String          Content;
    private String          Date;
    private int             backGroundColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent intent = getIntent();

        listNote = new PojoListNote();
        Title = intent.getStringExtra("Title");
        Content = intent.getStringExtra("Content");
        Date = intent.getStringExtra("Date");
        backGroundColor = intent.getIntExtra("BackGround", 0);
        position = intent.getIntExtra("Position", -1);

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

        Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyy/MM/dd").format(date);

        ListContainer container = new ListContainer();
        container.Title = "Vicnent";
        container.Date = "20011/1995";
        container.Content = "azeuikjhbfdsertyuk";
        container.Color = 222;

        listNote.noteList.add(container);

        JsonHandle.writeFile(getApplicationContext(), listNote);
    }

    public void readNote(View view) {
        PojoListNote allNotes = JsonHandle.readFile(getApplicationContext());
        if (allNotes == null)
        {
            ((EditText) findViewById(R.id.TextViewContent)).setText("No note save!");
        }
        else
        {
            ((EditText) findViewById(R.id.TextViewContent)).setText(allNotes.noteList.get(0).Content);
        }
    }
}