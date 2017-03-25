package com.andpad.andpad;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.ListView;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NotePadActivity extends AppCompatActivity  {

    List<ListContainer> list;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_activity);

        list = new ArrayList<ListContainer>();


        ListContainer tmp = new ListContainer();

        //debug
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
        tmp.Title = "Non 2";
        list.add(tmp);
        tmp.Title = "Putain";
        list.add(tmp);
        tmp.Title = "Gw2";
        list.add(tmp);
        tmp.Title = "Music";
        list.add(tmp);
        //!debug

        ListViewAdapter adapter = new ListViewAdapter(this, R.layout.infalter_notepad, list);
        listView = (ListView) findViewById(R.id.ListViewMain);
        listView.setAdapter(adapter);
    }

}
