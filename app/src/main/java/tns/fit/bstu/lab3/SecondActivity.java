package tns.fit.bstu.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.URLUtil;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.regex.Pattern;

public class SecondActivity extends AppCompatActivity {
    static final int GALLERY_REQUEST = 1;
    private static final int MAX_BITMAP_SIZE = 100 * 1024 * 1024;
    private ProgressBar pbHorizontal;
    String[] bookshalve = { "Твёрдый", "Мягкий"};
    Uri selectedImage1;
    String name;
    String publ;
    String date;

    ImageView imageView ;
    private static final String TAG = SecondActivity.class.getSimpleName();
    final static String nameVariableKey = "NAME_VARIABLE";
    final static String textViewTexKey = "TEXTVIEW_TEXT";
    String name1 ="undefined";
    Uri selectedImg2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);



        pbHorizontal = (ProgressBar) findViewById(R.id.pb_horizontal);
        pbHorizontal.setProgress(40);

        Bundle arguments = getIntent().getExtras();

        name = arguments.get("bookName").toString();
        publ = arguments.get("publisher").toString();
        date = arguments.get("date").toString();

       // EditText ageText = findViewById(R.id.editTextTextPersonName);
        //ageText.setText(name +"|" + publ +"|" + date);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, bookshalve);
        // Определяем разметку для использования при выборе элемента
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Применяем адаптер к элементу spinner
        spinner.setAdapter(adapter);
        imageView = (ImageView) findViewById(R.id.imageView);



        Button button = findViewById(R.id.addImg);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });


        ImageView img = findViewById(R.id.imageView);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
                photoPickerIntent.setType("image/*");
                startActivityForResult(photoPickerIntent, GALLERY_REQUEST);
            }
        });



        if (savedInstanceState != null){
            selectedImage1 = savedInstanceState.getParcelable("uri");
            if(selectedImage1 != null){
                Bitmap bitmap = null;
                Bitmap bt = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage1);
                        /*int bitmapSize = bitmap.getByteCount();
                        if (bitmapSize > MAX_BITMAP_SIZE) {
                            Toast.makeText(this, "TooLargeSizeOfImage", Toast.LENGTH_SHORT).show();
                            throw new RuntimeException(
                                    "Canvas: trying to draw too large(" + bitmapSize + "bytes) bitmap.");
                        }*/


                    float w = bitmap.getWidth();//get width
                    float h = bitmap.getHeight();//get height
                    int W = 1024;
                    int H = (int) ( (h*W)/w);
                    bt = Bitmap.createScaledBitmap(bitmap, W, H, false);//scale the bitmap


                } catch (IOException e) {
                    e.printStackTrace();
                }
                imageView.setImageBitmap(bt);
            }

        }


    }




    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (selectedImage1 != null) {
            outState.putParcelable("uri", selectedImage1);
        }
    }






    boolean check = false;
    public void onCheckboxClicked(View view) {
        // Получаем флажок
        CheckBox checkBox = (CheckBox) view;
        TextView selection = (TextView) findViewById(R.id.checkBox);
        Button button = findViewById(R.id.addImg);
        // Получаем, отмечен ли данный флажок
        if(checkBox.isChecked()) {
            button.setEnabled(true);
            check = true;
        }
        else {
            button.setEnabled(false);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);

        Bitmap bitmap = null;
        Bitmap bt = null;
       // ImageView imageView = (ImageView) findViewById(R.id.imageView);

        switch(requestCode) {
            case GALLERY_REQUEST:
                if(resultCode == RESULT_OK){
                    Uri selectedImage = imageReturnedIntent.getData();
                    selectedImage1 = selectedImage;

                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                        /*int bitmapSize = bitmap.getByteCount();
                        if (bitmapSize > MAX_BITMAP_SIZE) {
                            Toast.makeText(this, "TooLargeSizeOfImage", Toast.LENGTH_SHORT).show();
                            throw new RuntimeException(
                                    "Canvas: trying to draw too large(" + bitmapSize + "bytes) bitmap.");
                        }*/


                        float w = bitmap.getWidth();//get width
                        float h = bitmap.getHeight();//get height
                        int W = 1024;
                        int H = (int) ( (h*W)/w);
                        bt = Bitmap.createScaledBitmap(bitmap, W, H, false);//scale the bitmap


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageView.setImageBitmap(bt);
                }
        }
    }


    private boolean isValidMobile(String phone) {

        String MOBILE_STRING = "^(\\+375|80)(29|25|44|33)(\\d{3})(\\d{2})(\\d{2})$";

        return Pattern.compile(MOBILE_STRING).matcher(phone).matches();
    }


    private boolean isValidEmail(String email) {

        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        return Pattern.compile(EMAIL_STRING).matcher(email).matches();

    }

    public void onClick(View view) {


        EditText countOfPages = findViewById(R.id.editTextNumber);
        Spinner bookshalv = findViewById(R.id.spinner);
        EditText age = findViewById(R.id.editTextNumber2);
        EditText maker = findViewById(R.id.editTextTextPersonName);
        //ImageView img = findViewById(R.id.imageView);
        EditText email = findViewById(R.id.editTextTextEmailAddress);
        EditText phone = findViewById(R.id.editTextPhone);
        EditText inst = findViewById(R.id.editTextTextPersonName2);




        String count = countOfPages.getText().toString();
        String spinner = bookshalv.getSelectedItem().toString();
        String bAge = age.getText().toString();
        String bMaker = maker.getText().toString();
        String em = email.getText().toString();
        String ph = phone.getText().toString();
        String insta = inst.getText().toString();




        if(count.matches("") || spinner.matches("") || bAge.matches("") ||
                bMaker.matches("") || selectedImage1 == null || !isValidEmail(em)
                || !isValidMobile(ph)  ||  !URLUtil.isValidUrl(insta)){
            Toast.makeText(this, "Не все поля заполнены или введены кореректно!", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent = new Intent(this, EndActivity.class);
            intent.putExtra("bookName",name);
            intent.putExtra("publisher",publ);
            intent.putExtra("date",date);
            intent.putExtra("spinner",spinner);
            intent.putExtra("count",count);
            intent.putExtra("bAge",bAge);
            intent.putExtra("bMaker",bMaker);
            intent.putExtra("img",selectedImage1);
            intent.putExtra("em",em);
            intent.putExtra("ph",ph);
            intent.putExtra("insta",insta);



            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }



    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

}