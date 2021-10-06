package tns.fit.bstu.lab3;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class BookCollectionHelper {


    public static List<Book> itemsList = new LinkedList<Book>();
    public static final String FILE_NAME = "dataFile.json";
    public static final String ENCODING = "utf-8";


    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void createCollection(File path) throws IOException {
        File jsonCollentionFile = new File(path, FILE_NAME);
        if (jsonCollentionFile.exists()) {
            try {
                byte[] encoded = Files.readAllBytes(Paths.get(jsonCollentionFile.getPath()));
                String content = new String(encoded, ENCODING);
                itemsList = new Gson().fromJson(content, new TypeToken<LinkedList<Book>>() {
                }.getType());
            } catch (Exception e) {

            }
        } else {
            itemsList = new LinkedList<Book>();
        }
    }

    public static void saveCollection(File path) throws IOException {
        File jsonCollentionFile = new File(path, FILE_NAME);
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String jsonString = gson.toJson(itemsList);

        FileOutputStream stream = new FileOutputStream(jsonCollentionFile);
        try {
            stream.write(jsonString.getBytes());
        } finally {
            stream.close();
        }
    }


}
