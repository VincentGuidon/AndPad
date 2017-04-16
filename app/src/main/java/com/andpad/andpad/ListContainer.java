package com.andpad.andpad;

import android.net.Uri;
import android.support.annotation.DrawableRes;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Neilug on 25/03/2017.
 */

public class ListContainer {
    public String   Title;
    public String   Content;
    public String   Date;
    public int      Color;
    public String   Filepath;

    public ListContainer() {
        Title = "Title";
        Content = "Add your content";
        java.util.Date date = new Date();
        Date = new SimpleDateFormat("yyyy/MM/dd").format(date);
        Color = R.color.colorMainWhite;
        Filepath = null;
    }
}
