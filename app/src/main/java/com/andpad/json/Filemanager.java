package com.andpad.json; /**
 * Created by Neilug on 21/03/2017.
 */

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.Object;
import java.util.Date;

public class Filemanager {

    static void Save(String filename, Date date, String content) {

        //code tellement moche...

        try {
            //pour ecrire le fichier
            FileWriter fstream = new FileWriter(filename);
            BufferedWriter out = new BufferedWriter(fstream);

            //créer le json
            JSONObject saver = new JSONObject();

            try {
                saver.put("filename", filename);
                saver.put("date", date);
                saver.put("content", content);

                //stocker le json dans le fichier filename
                out.write(saver.toString());
                out.close();

            }
            catch (org.json.JSONException e)
            {
                System.out.print(e.toString());
            }

        }
        catch (java.io.IOException e) {
            System.out.print(e.toString());
        }

    }

    static JSONObject Load(String filename)
    {

        //code tellement moche aussi...

        try {
            //pour lire le fichier
            FileReader fstream = new FileReader(filename);
            BufferedReader in = new BufferedReader(fstream);


            String tmp = in.readLine(); //peut etre ça marchera pas

            try {
                //créer le json
                JSONObject saver = new JSONObject(tmp);
                return (saver);
            }
            catch (JSONException e)
            {
                System.out.print(e.toString());
            }


        }
        catch (java.io.IOException e) {
            System.out.print(e.toString());
        }
        //problèmes
        return (new JSONObject());
    }

}
