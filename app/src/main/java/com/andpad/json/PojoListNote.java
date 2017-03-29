package com.andpad.json;

import com.andpad.andpad.ListContainer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Vincent on 26/03/2017.
 */

public class PojoListNote {

 /*   public String  Name;
    public int     beau;
    public List    list;*/
    public List<ListContainer> noteList;

    public PojoListNote () {
        noteList = new ArrayList<ListContainer>();
    }

}
