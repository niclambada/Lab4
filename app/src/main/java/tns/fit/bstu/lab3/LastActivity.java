package tns.fit.bstu.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

public class LastActivity extends AppCompatActivity {

    Book newItem;


    TextView bookN;
    TextView pub;
    TextView dat;
    TextView spiner;
    TextView cout;
    TextView bage;
    TextView bmaker;
    EditText em;
    EditText ph;
    EditText in;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last2);


        bookN = findViewById(R.id.bName);
        pub = findViewById(R.id.bookPublisher);
        dat = findViewById(R.id.bookDate);
        spiner = findViewById(R.id.bookShalve);
        cout = findViewById(R.id.bookCountOfPages);
        bage = findViewById(R.id.bookAge);
        bmaker = findViewById(R.id.bookMaker);
        em = findViewById(R.id.editTextTextEmailAddress);
        ph = findViewById(R.id.editTextPhone);
        in = findViewById(R.id.editTextTextPersonName2);

        Intent intent = getIntent();
        newItem = (Book) intent.getSerializableExtra("NEW_ITEM");

        bookN.setText(newItem.bookName);
        pub.setText(newItem.publisher);
        dat.setText(newItem.date);
        spiner.setText(newItem.bookShalve);
        cout.setText(newItem.countOfPages);
        bage.setText(newItem.age);
        bmaker.setText(newItem.maker);
        em.setText(newItem.email);
        ph.setText(newItem.phone);
        in.setText(newItem.inst);

        Bitmap bt = null;
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), newItem.bitmap);

            float w = bitmap.getWidth();//get width
            float h = bitmap.getHeight();//get height
            int W = 1024;
            int H = (int) ( (h*W)/w);
            bt = Bitmap.createScaledBitmap(bitmap, W, H, false);//scale the bitmap

            ImageView img = findViewById(R.id.bookImage);
            img.setImageBitmap(bt);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}