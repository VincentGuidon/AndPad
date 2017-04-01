package com.andpad.json;

import android.content.Context;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Vincent on 26/03/2017.
 */

public class JsonHandle {

    Gson gson;

    JsonHandle() {}

    public static void writeFile(Context context, PojoListNote listNote)
    {
        Gson gson = new Gson();
        String json = gson.toJson(listNote);

        try {
            //write converted json data to a file named "CountryGSON.json"
            FileOutputStream fileOutputStream = context.openFileOutput("Note.json", MODE_PRIVATE);
            fileOutputStream.write(json.getBytes());
            fileOutputStream.close();
            Toast.makeText(context, "Note save", Toast.LENGTH_SHORT).show();

        } catch (java.io.IOException e) {
            Toast.makeText(context, "An error Occurs, The note has not been save", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    public static PojoListNote readFile(Context context)
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
            PojoListNote allNotes = gson.fromJson(stringBuffer.toString(), PojoListNote.class);
            return (allNotes);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return (null);
        } catch (IOException e) {
            e.printStackTrace();
            return (null);
        }
    }

}
