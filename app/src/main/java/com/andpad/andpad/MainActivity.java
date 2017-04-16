package com.andpad.andpad;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.logo_activity);

        ImageView wtf = (ImageView)findViewById(R.id.wtf);

        wtf.setOnClickListener(clickEvent());
    }

    public View.OnClickListener clickEvent()
    {
        startActivity(new Intent(this, NotePadList.class));
        this.finish();
        
        return null;
    }
}
