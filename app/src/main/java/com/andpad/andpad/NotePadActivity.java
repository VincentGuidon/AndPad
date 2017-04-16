package com.andpad.andpad;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andpad.json.PojoListNote;

import org.w3c.dom.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vincent on 26/03/2017.
 */

public class NotePadActivity extends Activity implements ColorPickerDialog.OnColorChangedListener{

    private static final int SELECT_PICTURE = 42;
    private PojoListNote    listNote;
    private int             position;
    private String          Title;
    private String          Content;
    private String          Date;
    private int             textColor;
    private EditText        textViewTitle;
    private EditText        textViewContent;
    private TextView        textView1;
    private TextView        textView2;
    private LinearLayout    ll;
    private ImageView       iv;

    private String          filePath;
    private Uri             uri;

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
        textColor = listNote.noteList.get(position).Color;
        filePath = listNote.noteList.get(position).Filepath;

        setContentView(R.layout.notepad_activity);

        textViewTitle = (EditText) findViewById(R.id.TextViewTitle);
        textViewContent = ((EditText) findViewById(R.id.TextViewContent));
        textView1 = (TextView) findViewById(R.id.TextView1);
        textView2 = (TextView) findViewById(R.id.TextView2);
        iv = ((ImageView) findViewById(R.id.truc));
        ll = (LinearLayout) findViewById(R.id.allActivityNotePad);

        textViewTitle.setText(Title);
        textViewContent.setText(Content);
        textViewTitle.setTextColor(textColor);
        textViewContent.setTextColor(textColor);
        textView1.setTextColor(textColor);
        textView2.setTextColor(textColor);
        if (filePath != null && filePath != "")
            iv.setImageURI(Uri.fromFile(new File(filePath)));
//        setFilePathAsBackground(ll, filePath);
        Toast.makeText(getApplicationContext(), filePath, Toast.LENGTH_SHORT).show();


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        if (requestCode == SELECT_PICTURE)
        {
            if(resultCode == RESULT_OK){
                uri = imageReturnedIntent.getData();
                filePath = uri.getPath();
                setFilePathAsBackground(ll, filePath);
            }
        }
    }

    @Override
    public void colorChanged(int color) {

        textViewContent.setTextColor(color);
        textViewTitle.setTextColor(color);
        textView1.setTextColor(color);
        textView2.setTextColor(color);
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
        container.Color = textViewContent.getCurrentTextColor();
        container.Filepath = filePath;

        listNote.noteList.remove(position);
        listNote.noteList.add(0, container);

        WrapperListNote.getInstance().writeFile(getApplicationContext());
        Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT).show();
    }

    private void setFilePathAsBackground(LinearLayout linearLayout, String path)
    {
        if (path != null &&
                path != "") {
            ((ImageView) findViewById(R.id.truc)).setImageURI(uri);
        }
    }


    public void changeBackground(View view)
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
}