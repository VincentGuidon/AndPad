package com.andpad.andpad;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements ColorPickerDialog.OnColorChangedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeView(View v)
    {
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_main);
        Drawable background = v.getBackground();

        ColorDrawable viewColor = (ColorDrawable) rl.getBackground();
        int colorId = viewColor.getColor();

        new ColorPickerDialog(MainActivity.this, MainActivity.this, colorId).show();
    }
  /*  public void changeView(View view) {
        Intent intent =  new Intent(this, NotePad.class);
        intent.putExtra("Title", "Inside");
        startActivity(intent);

        new ColorPickerDialog(FingerPaintActivity.this, FingerPaintActivity.this, mPaint.getColor()).show();
    }*/

    public void listView(View view) {
        Intent intent = new Intent(this, ListViewAndroidExampleActivity.class);

        startActivity(intent);
    }

    @Override
    public void colorChanged(int color) {
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_main);
        rl.setBackgroundColor(color);
    }
}
