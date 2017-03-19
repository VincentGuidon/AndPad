package com.andpad.andpad;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void changeView(View view) {
        Intent intent =  new Intent(this, NotePad.class);
        intent.putExtra("Title", "Inside");
        startActivity(intent);
    }

    public void listView(View view) {
        Intent intent = new Intent(this, ListViewAndroidExampleActivity.class);

        startActivity(intent);
    }
}
