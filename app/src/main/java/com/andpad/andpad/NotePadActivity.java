package com.andpad.andpad;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

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
    private int             backgroundColor;
    private EditText        textViewTitle;
    private EditText        textViewContent;
    private LinearLayout    ll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent intent = getIntent();

        listNote = WrapperListNote.getInstance().wrapperListNote;
        position = intent.getIntExtra("Position", 0);
        if (position < 0)
            position = 0;
        if (listNote.noteList.size() == 0)
        {
            ListContainer container = new ListContainer();
            listNote.noteList.add(container);
            position = 0;
        }
        Title = listNote.noteList.get(position).Title;
        Content = listNote.noteList.get(position).Content;
        Date = listNote.noteList.get(position).Date;
        backgroundColor = listNote.noteList.get(position).Color;

        setContentView(R.layout.notepad_activity);


        textViewTitle = (EditText) findViewById(R.id.TextViewTitle);
        textViewContent = ((EditText) findViewById(R.id.TextViewContent));
        ll = (LinearLayout) findViewById(R.id.allActivityNotePad);
        textViewTitle.setText(Title);
        textViewContent.setText(Content);
        colorChanged(backgroundColor);
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

        listNote.noteList.add(position, container);

        WrapperListNote.getInstance().writeFile(getApplicationContext());
        Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT).show();
    }

    public void deleteNote(View view)
    {
        listNote.noteList.remove(position);
        WrapperListNote.getInstance().writeFile(getApplicationContext());
        Toast.makeText(getApplicationContext(), "Note deleted", Toast.LENGTH_SHORT).show();
        returnToList();
    }

    public void returnToList()
    {
        Intent intent = new Intent(this, NotePadList.class);
        startActivity(intent);
        this.finish();
    }

    public void shareFile(View view) {
        shareIt();
    }

    private void shareIt()
    {
        Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = Content;
        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, Title);
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));

    }

/*    public void readNote(View view) {
        PojoListNote allNotes = JsonHandle.readFile(getApplicationContext());
        if (allNotes == null)
        {
            textViewContent.setText("No note saved!");
        }
        else
        {
            textViewContent.setText(allNotes.noteList.get(0).Content);
        }
    }*/
}