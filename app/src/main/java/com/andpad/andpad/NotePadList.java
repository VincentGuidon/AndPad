package com.andpad.andpad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.R.attr.value;

public class NotePadList extends AppCompatActivity {

    ListNote list = ListNote.getInstance();
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);

        list.setUp(getApplicationContext());


  /*
        --debug

        ListContainer tmp = new ListContainer();
        ListContainer tmp1 = new ListContainer();
        ListContainer tmp2 = new ListContainer();
        ListContainer tmp3 = new ListContainer();
        ListContainer tmp4 = new ListContainer();
        ListContainer tmp5 = new ListContainer();
        ListContainer tmp6 = new ListContainer();
        ListContainer tmp7 = new ListContainer();


        tmp.Title = "Oui 1";
        tmp.Date = "23/07/1992";
        tmp.Content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam gravida gravida massa, non placerat risus molestie sed. Donec nec metus sapien. In pretium nulla justo, id varius dui efficitur ac. Vestibulum semper justo eget lectus mollis, quis maximus ipsum consequat. Suspendisse in justo ex. Duis porta sapien arcu. Quisque suscipit tempus arcu a posuere. Sed eget mi ut nisl tincidunt ullamcorper id sed dolor. Fusce id hendrerit justo. Aliquam convallis lectus lectus, a vehicula augue pharetra eu. Vestibulum ligula nunc, laoreet nec orci sit amet, egestas sagittis eros.\n" +
                "\n" +
                "Duis vitae erat suscipit, viverra ipsum sed, molestie felis. Nam imperdiet mi at turpis maximus aliquet. Maecenas suscipit sagittis augue, et faucibus nisi porta quis. Nunc vitae consequat ante, vel condimentum arcu. Nulla sit amet faucibus justo, id ultricies nunc. Proin pretium nibh nec massa ornare, eget tincidunt risus luctus. In congue, nunc ac euismod vulputate, libero velit pretium purus, ultrices convallis risus quam ut quam. Ut in consectetur ligula.\n" +
                "\n" +
                "Vivamus elementum tellus et lacinia interdum. Aliquam porta ligula orci, nec aliquam est porta sed. Phasellus dictum euismod tincidunt. Vestibulum nec sapien pellentesque, scelerisque turpis at, gravida tellus. Quisque aliquam leo nibh, ac vestibulum massa bibendum consectetur. Suspendisse rhoncus massa in sapien ultricies tincidunt. Praesent feugiat volutpat eros, in sodales odio efficitur vel.\n" +
                "\n" +
                "Sed rutrum nunc sed augue tincidunt, id eleifend quam elementum. Fusce aliquet feugiat mi, eu malesuada enim imperdiet eu. Quisque mollis nisl ut ipsum feugiat dapibus. Quisque tincidunt mollis sapien, eu gravida sapien tristique convallis. Suspendisse id felis congue, iaculis urna non, efficitur dolor. Donec dapibus erat et porttitor finibus. Phasellus eros augue, ornare vitae neque eu, tincidunt varius purus. Fusce euismod, justo ut lacinia dictum, sem tellus convallis odio, nec convallis metus mi vel enim. Mauris semper mi quis arcu lacinia pharetra. Morbi tincidunt auctor ipsum vel rutrum. Etiam et odio sem. Mauris nulla mi, tempus pulvinar varius nec, consequat non nunc\n";

        list.add(tmp);
        tmp1.Title = "Non 2";
        tmp1.Content = "coucou";
        tmp1.Date = "coucou";
        list.add(tmp1);
        tmp2.Title = "Putain";
        tmp2.Content = "yes";
        tmp2.Date = "24/20/2052";
        list.add(tmp2);
        tmp3.Title = "Gw2";
        tmp3.Content = "zertyuiokjhgfdsdfn,kjhgfd";
        tmp3.Date = "22/22/22";
        list.add(tmp3);
        tmp4.Title = "Music";
        tmp4.Content = "Music";
        tmp4.Date = "Music";
        list.add(tmp4);
        tmp5.Title = "Music";
        tmp5.Content = "Music";
        tmp5.Date = "Music";
        list.add(tmp5);
        tmp6.Title = "Music";
        tmp6.Content = "Music";
        tmp6.Date = "Music";
        list.add(tmp6);
        tmp7.Title = "Music";
        tmp7.Content = "Music";
        tmp7.Date = "Music";
        list.add(tmp7);

*/

        //!debug



        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.infalter_notepad, list.allNotes.noteList);
        listView = (ListView) findViewById(R.id.ListViewMain);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = new Intent(parent.getContext(), NotePadActivity.class);

                String Title = ((TextView) view.findViewById(R.id.ListTextTitle)).getText().toString();
                String Content = ((TextView) view.findViewById(R.id.ListTextContent)).getText().toString();
                String Date = ((TextView) view.findViewById(R.id.ListTextDate)).getText().toString();

             /*   intent.putExtra("Title", Title);
                intent.putExtra("Content", Content);
                intent.putExtra("Date", Date);
                intent.putExtra("Background", )*/

                ListContainer container = list.allNotes.noteList.get(position);

                intent.putExtra("Title", container.Title);
                intent.putExtra("Content", container.Content);
                intent.putExtra("Date", container.Date);
                intent.putExtra("Background", container.Color);
                intent.putExtra("Position", position);

                startActivity(intent);
            }
        });

        listView.setAdapter(adapter);
    }

}
