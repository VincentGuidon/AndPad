package com.andpad.andpad;

import android.Manifest;
import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.andpad.json.PojoListNote;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.w3c.dom.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Vincent on 26/03/2017.
 */

public class NotePadActivity extends AppCompatActivity implements ColorPickerDialog.OnColorChangedListener {

    private static final int SELECT_PICTURE = 42;
    private PojoListNote listNote;
    private int position;
    private String Title;
    private String Content;
    private String Date;
    private int textColor;
    private EditText textViewTitle;
    private EditText textViewContent;
    private LinearLayout ll;
    private ImageView iv;

    private String filePath;
    private Uri uri;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    //private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        Intent intent = getIntent();

        listNote = WrapperListNote.getInstance().wrapperListNote;
        position = intent.getIntExtra("Position", 0);
        if (position < 0)
            position = 0;
        if (listNote.noteList.size() == 0) {
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

        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        // add the custom view to the action bar
        actionBar.setCustomView(R.layout.note_title);
        textViewTitle = (EditText) actionBar.getCustomView().findViewById(R.id.NoteTitle);
        textViewTitle.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                //textViewTitle2.setText(v.getText());
                return false;
            }
        });
        actionBar.setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM
                | android.support.v7.app.ActionBar.DISPLAY_SHOW_HOME);

        textViewTitle = (EditText) findViewById(R.id.NoteTitle);
        textViewContent = ((EditText) findViewById(R.id.TextViewContent));
        iv = ((ImageView) findViewById(R.id.truc));
        ll = (LinearLayout) findViewById(R.id.allActivityNotePad);

        textViewTitle.setText(Title);
        textViewContent.setText(Content);
        textViewContent.setTextColor(textColor);
        if (filePath != null && filePath.equals(""))
            iv.setImageURI(Uri.fromFile(new File(filePath)));
//        setFilePathAsBackground(ll, filePath);
        Toast.makeText(getApplicationContext(), filePath, Toast.LENGTH_SHORT).show();
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

        if (requestCode == SELECT_PICTURE) {
            if (resultCode == RESULT_OK) {
                uri = imageReturnedIntent.getData();
                filePath = uri.getPath();
                setFilePathAsBackground(ll, filePath);
            }
        }
    }

    @Override
    public void colorChanged(int color) {

        textViewContent.setTextColor(color);
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
        container.Filepath = filePath;

        listNote.noteList.remove(position);
        listNote.noteList.add(0, container);

        WrapperListNote.getInstance().writeFile(getApplicationContext());
        Toast.makeText(getApplicationContext(), "Note saved", Toast.LENGTH_SHORT).show();
    }

    private void setFilePathAsBackground(LinearLayout linearLayout, String path) {
        if (path != null &&
                path != "") {
            ((ImageView) findViewById(R.id.truc)).setImageURI(uri);
        }
    }


    public void changeBackground() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, SELECT_PICTURE);
        }
    }

    public void deleteNote() {
        listNote.noteList.remove(position);
        WrapperListNote.getInstance().writeFile(getApplicationContext());
        Toast.makeText(getApplicationContext(), "Note deleted", Toast.LENGTH_SHORT).show();
        returnToList();
    }

    public void returnToList() {
        Intent intent = new Intent(this, NotePadList.class);
        startActivity(intent);
        this.finish();
    }

    public void shareFile(View view) {
        shareIt();
    }

    private void shareIt() {
        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        String shareBody = Content;
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, Title);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveNote();
                break;
            case R.id.action_delete:
                deleteNote();
                break;
            case R.id.action_change_font_color:
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

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("NotePad Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

}