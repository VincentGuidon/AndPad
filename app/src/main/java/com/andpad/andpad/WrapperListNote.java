package com.andpad.andpad;

import android.content.Context;
import android.widget.Toast;

import com.andpad.json.PojoListNote;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Vincent on 29/03/2017.
 */
public class WrapperListNote {
    private static WrapperListNote ourInstance = new WrapperListNote();

    public static WrapperListNote getInstance() {
        return ourInstance;
    }

    public PojoListNote wrapperListNote;

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

    public void writeFile(Context context)
    {
        Gson gson = new Gson();
        String json = gson.toJson(this.wrapperListNote);

        try {
            //write converted json data to a file named "CountryGSON.json"
            FileOutputStream fileOutputStream = context.openFileOutput("Note.json", MODE_PRIVATE);
            fileOutputStream.write(json.getBytes());
            fileOutputStream.close();

        } catch (java.io.IOException e) {
            Toast.makeText(context, "An error occurred, the note has not been saved.", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
