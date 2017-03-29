package com.andpad.andpad;

import android.content.Context;

import com.andpad.json.PojoListNote;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vincent on 29/03/2017.
 */
public class ListNote {
    private static ListNote ourInstance = new ListNote();

    public static ListNote getInstance() {
        return ourInstance;
    }

    PojoListNote allNotes;

    private ListNote() {
        allNotes = new PojoListNote();
    }

    public void setUp(Context context)
    {
        try {
            FileInputStream fileInputStream= context.openFileInput("Note.json");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            StringBuffer stringBuffer = new StringBuffer();
            String lines;
            while ((lines = bufferedReader.readLine())!=null) {
                stringBuffer.append(lines + "\n");
            }

            Gson gson = new Gson();
            allNotes = gson.fromJson(stringBuffer.toString(), PojoListNote.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
                allNotes = null;
        } catch (IOException e) {
            e.printStackTrace();
            allNotes = null;
        }
    }
}
