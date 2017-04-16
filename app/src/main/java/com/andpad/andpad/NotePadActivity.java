package com.andpad.andpad;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.andpad.json.PojoListNote;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vincent on 26/03/2017.
 */

public class NotePadActivity extends AppCompatActivity implements ColorPickerDialog.OnColorChangedListener{

    private static final int SELECT_PICTURE = 42;
    private PojoListNote    listNote;
    private int             position;
    private String          Title;
    private String          Content;
    private String          Date;
    private int             textColor;

    private EditText        textViewTitle;
    private EditText        textViewContent;
    private LinearLayout    ll;
    private ImageView       imageBackground;
    private String          imagePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Thread thread;

        thread = new Thread();

        thread.start();

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
        textColor = listNote.noteList.get(position).Color;
        imagePath = listNote.noteList.get(position).ImagePath;

        setContentView(R.layout.notepad_activity);

        Initiate();
        SetText();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (requestCode == SELECT_PICTURE)
        {
            if(resultCode == RESULT_OK){
                imagePath = imageReturnedIntent.getDataString();
                setFilePathAsBackground();
            }
        }
    }

    @Override
    public void colorChanged(int color) {

        textViewContent.setTextColor(color);
        textViewTitle.setTextColor(color);
    }

    public void changeColor() {
        ColorDrawable viewColor = (ColorDrawable) ll.getBackground();
        int colorId = viewColor.getColor();

        new ColorPickerDialog(NotePadActivity.this, NotePadActivity.this, colorId).show();
    }

    public void saveNote() {

        Date date = new Date();
        String dateStr = new SimpleDateFormat("yyyy/MM/dd").format(date);

        ListContainer container = new ListContainer();
        container.Title = textViewTitle.getText().toString();
        container.Date = dateStr;
        container.Content = textViewContent.getText().toString();
        container.Color = textViewContent.getCurrentTextColor();
        container.ImagePath = imagePath;

        listNote.noteList.remove(position);
        listNote.noteList.add(0, container);

        WrapperListNote.getInstance().writeFile(getApplicationContext());
        Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT).show();
    }

    private void Initiate()
    {
        textViewTitle = (EditText) findViewById(R.id.TextViewTitle);
        textViewContent = ((EditText) findViewById(R.id.TextViewContent));
        imageBackground = ((ImageView) findViewById(R.id.imageBackground));
        ll = (LinearLayout) findViewById(R.id.allActivityNotePad);

    }

    private void SetText()
    {
        textViewTitle.setText(Title);
        textViewContent.setText(Content);
        textViewTitle.setTextColor(textColor);
        textViewContent.setTextColor(textColor);
        setFilePathAsBackground();
    }

    private void setFilePathAsBackground()
    {
        if (imagePath != null) {
            imageBackground.setImageURI(Uri.parse(imagePath));
        }
    }


    public void changeBackground()
    {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED)
        {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, SELECT_PICTURE);
        }
    }

    public void deleteNote()
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_save:
                saveNote();
                break;
            case R.id.action_delete:
                deleteNote();
                break;
            case R.id.action_change_bg_color:
                changeColor();
                break;
            case R.id.action_change_bg_image:
                changeBackground();
                break;
            case R.id.action_share:
                shareIt();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}