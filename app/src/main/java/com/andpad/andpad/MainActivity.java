package com.andpad.andpad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_activity);

        startActivity(new Intent(this, NotePadList.class));
       this.finish();
    }



    /*
    implements ColorPickerDialog.OnColorChangedListener

    public void changeView(View v)
    {
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_main);
        Drawable background = v.getBackground();

        ColorDrawable viewColor = (ColorDrawable) rl.getBackground();
        int colorId = viewColor.getColor();

        new ColorPickerDialog(MainActivity.this, MainActivity.this, colorId).show();
    }*/
  /*  public void changeView(View view) {
        Intent intent =  new Intent(this, NotePad.class);
        intent.putExtra("Title", "Inside");
        startActivity(intent);

        new ColorPickerDialog(FingerPaintActivity.this, FingerPaintActivity.this, mPaint.getColor()).show();
    }*/
/*
    @Override
    public void colorChanged(int color) {
        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_main);
        rl.setBackgroundColor(color);
    }*/
}
