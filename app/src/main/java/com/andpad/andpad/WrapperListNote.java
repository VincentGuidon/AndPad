package com.andpad.andpad;

import android.content.Context;

import com.andpad.json.PojoListNote;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Vincent on 29/03/2017.
 */
public class WrapperListNote {
    private static WrapperListNote ourInstance = new WrapperListNote();

    public static WrapperListNote getInstance() {
        return ourInstance;
    }

    PojoListNote wrapperListNote;

    private WrapperListNote() {
        wrapperListNote = new PojoListNote();
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
            wrapperListNote = gson.fromJson(stringBuffer.toString(), PojoListNote.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
                wrapperListNote = null;
        } catch (IOException e) {
            e.printStackTrace();
            wrapperListNote = null;
        }
    }
}
