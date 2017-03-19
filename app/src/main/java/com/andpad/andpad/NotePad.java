package com.andpad.andpad;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class NotePad extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_pad);

        TextView tv = (TextView) findViewById(R.id.Title);
        String str = getIntent().getExtras().getString("Title");


        RelativeLayout rl = (RelativeLayout) findViewById(R.id.activity_note_pad);
        rl.setBackgroundColor(Color.parseColor("#ffff66"));

        tv.setText(str);
    }
}
