package com.andpad.andpad;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Neilug on 25/03/2017.
 */

public class ListViewAdapter extends ArrayAdapter<ListContainer> {

    List<ListContainer> list;
    Context context;
    int resource;

    public ListViewAdapter(Context context, int resource, List<ListContainer> objects) {
        super(context, resource, objects);
        this.list = objects;
        this.context = context;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        View view = inflater.inflate(resource, parent, false);
        return (view);
    }
}
