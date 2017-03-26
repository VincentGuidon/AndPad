package com.andpad.andpad;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Property;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neilug on 25/03/2017.
 */

public class ListViewAdapter extends ArrayAdapter<ListContainer> {

    List<ListContainer> list;
    Context context;
    int resource;

    public ListViewAdapter(Context context, int resource, List<ListContainer> objects)
    {
        super(context, resource, objects);
        this.list = objects;
        this.context = context;
        this.resource = resource;
     }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
      //  LayoutInflater inflater = ((Activity) context).getLayoutInflater();
       // View view = inflater.inflate(resource, parent, false);

        ListContainer container = list.get(position);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.infalter_notepad, null);

        TextView Title = (TextView) view.findViewById(R.id.ListTextTitle);
        TextView Content = (TextView) view.findViewById(R.id.ListTextContent);
        TextView Date = (TextView) view.findViewById(R.id.ListTextDate);

        Title.setText(String.valueOf(container.Title));
        Content.setText(String.valueOf(container.Content));
        Date.setText(String.valueOf(container.Date));


        return (view);
    }

}
