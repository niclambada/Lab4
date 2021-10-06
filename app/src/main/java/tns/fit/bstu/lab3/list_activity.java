package tns.fit.bstu.lab3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class list_activity extends AppCompatActivity {

    ListView listView;
    public static ArrayAdapter<Book> listAdapter;


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {
            BookCollectionHelper.createCollection(getExternalFilesDir(null));
        } catch (IOException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.listView);
        ArrayAdapter<Book> arrayAdapter = new ArrayAdapter<Book>(
                this,
                android.R.layout.simple_list_item_1,
                BookCollectionHelper.itemsList );
        listView.setAdapter(arrayAdapter);

        listAdapter = (ArrayAdapter<Book>) listView.getAdapter();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Book selectedItem = (Book) listView.getItemAtPosition(i);

                Intent intent = new Intent(list_activity.this, LastActivity.class);
                intent.putExtra("NEW_ITEM", selectedItem);

                startActivity(intent);
            }
        });
    }


}