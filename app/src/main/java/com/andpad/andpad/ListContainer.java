package com.andpad.andpad;

import android.content.Intent;
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
    public Uri      imageUri;
    public Intent   imageIntent;

    public ListContainer() {
        Title = "Title";
        Content = "Add your content";
        java.util.Date date = new Date();
        Date = new SimpleDateFormat("yyyy/MM/dd").format(date);
        Color = R.color.colorMainWhite;
        imageUri = null;
        imageIntent = null;
    }
}
