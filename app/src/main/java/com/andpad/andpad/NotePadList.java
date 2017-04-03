    package com.andpad.andpad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class NotePadList extends AppCompatActivity {

    WrapperListNote list = WrapperListNote.getInstance();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);

        list.setUp(getApplicationContext());
    }

    @Override
    public void onResume()
    {
        super.onResume();
        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.infalter_notepad, list.wrapperListNote.noteList);
        listView = (ListView) findViewById(R.id.ListViewMain);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(parent.getContext(), NotePadActivity.class);
                intent.putExtra("Position", position);
                startActivity(intent);
            }
        });

        listView.setAdapter(adapter);

    }

    public void addNote(View view) {
        ListContainer container = new ListContainer();
  //      ((Button) findViewById(R.id.addNote)).setText(String.valueOf(position));

        list.wrapperListNote.noteList.add(container);
        int position = list.wrapperListNote.noteList.size() - 1;

        Intent intent = new Intent(view.getContext(), NotePadActivity.class);
        intent.putExtra("Position", position);
        startActivity(intent);
        this.finish();
    }
}
