package us.ridiculousbakery.simpletodo;

import android.content.Context;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Model {

    static ArrayList readItems(Context ctx) {
        File filesDir = ctx.getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            return new ArrayList<String>(FileUtils.readLines(todoFile));
        } catch (IOException e) {
            return new ArrayList<String>();
        }
    }

    static void writeItems(Context ctx, ArrayList dataList) {
        File filesDir = ctx.getFilesDir();
        File todoFile = new File(filesDir, "todo.txt");
        try {
            FileUtils.writeLines(todoFile, dataList);
        } catch (IOException e) {
            e.printStackTrace();

        }

    }

}