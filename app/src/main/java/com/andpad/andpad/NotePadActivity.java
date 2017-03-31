package com.andpad.andpad;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.andpad.json.JsonHandle;
import com.andpad.json.PojoListNote;

import java.text.SimpleDateFormat;
import java.util.Date;

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
    private EditText        textViewTitle;
    private EditText        textViewContent;
    private LinearLayout    ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent intent = getIntent();

        listNote = WrapperListNote.getInstance().wrapperListNote;
        position = intent.getIntExtra("Position", -1);
        Title = listNote.noteList.get(position).Title;
        Content = listNote.noteList.get(position).Content;
        Date = listNote.noteList.get(position).Date;
        backGroundColor = listNote.noteList.get(position).Color;

        setContentView(R.layout.notepad_activity);


        textViewTitle = (EditText) findViewById(R.id.TextViewTitle);
        textViewContent = ((EditText) findViewById(R.id.TextViewContent));
        ll = (LinearLayout) findViewById(R.id.allActivityNotePad);
        textViewTitle.setText(Title);
        textViewContent.setText(Content);
        colorChanged(backGroundColor);
    }

    @Override
    public void colorChanged(int color) {
        LinearLayout ll = (LinearLayout) findViewById(R.id.allActivityNotePad);
        ll.setBackgroundColor(color);
    }

    public void changeColor(View view) {
        ColorDrawable viewColor = (ColorDrawable) ll.getBackground();
        int colorId = viewColor.getColor();

        new ColorPickerDialog(NotePadActivity.this, NotePadActivity.this, colorId).show();
    }

    public void saveNote(View view) {

        Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyy/MM/dd").format(date);

        ListContainer container = new ListContainer();
        container.Title = textViewTitle.getText().toString();
        container.Date = dateStr;
        container.Content = textViewContent.getText().toString();
        ColorDrawable viewColor = (ColorDrawable) ll.getBackground();
        container.Color = viewColor.getColor();

        listNote.noteList.add(container);
        listNote.noteList.add(position, container);

        JsonHandle.writeFile(getApplicationContext(), listNote);
    }

    public void readNote(View view) {
        PojoListNote allNotes = JsonHandle.readFile(getApplicationContext());
        if (allNotes == null)
        {
            textViewContent.setText("No note save!");
        }
        else
        {
            textViewContent.setText(allNotes.noteList.get(0).Content);
        }
    }
}