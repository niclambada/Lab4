package tns.fit.bstu.lab3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EndActivity extends AppCompatActivity {




    public String name;
    public String publisher;
    public String date;
    public String spinner;
    public String count;
    public String bAge;
    public String bMaker;
    public Uri selectedImg;
    public String email;
    public String phone;
    public String inst;


    public TextView bookN ;
    public TextView pub ;
    public TextView dat ;
    public TextView spiner ;
    public TextView cout ;
    public TextView bage ;
    public TextView bmaker ;
    public EditText em ;
    public EditText ph ;
    public EditText in ;


    Book newItem;

    private List<Book> books;
    private ProgressBar pbHorizontal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end);

        Bundle arguments = getIntent().getExtras();

        name = arguments.get("bookName").toString();
        publisher = arguments.get("publisher").toString();
        date = arguments.get("date").toString();
        spinner = arguments.get("spinner").toString();
        count = arguments.get("count").toString();
        bAge = arguments.get("bAge").toString();
        bMaker = arguments.get("bMaker").toString();
        //selectedImg = Uri.parse(arguments.get("img").toString());
        selectedImg = (Uri) arguments.get("img");
        email = arguments.get("em").toString();
        phone = arguments.get("ph").toString();
        inst = arguments.get("insta").toString();


        newItem = new Book();

        Bitmap bt = null;
        try {
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImg);

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

        TextView bookN = findViewById(R.id.bName);
        TextView pub = findViewById(R.id.bookPublisher);
        TextView dat = findViewById(R.id.bookDate);
        TextView spiner = findViewById(R.id.bookShalve);
        TextView cout = findViewById(R.id.bookCountOfPages);
        TextView bage = findViewById(R.id.bookAge);
        TextView bmaker = findViewById(R.id.bookMaker);
        EditText em = findViewById(R.id.editTextTextEmailAddress);
        EditText ph = findViewById(R.id.editTextPhone);
        EditText in = findViewById(R.id.editTextTextPersonName2);




        bookN.setText(name);
        pub.setText(publisher);
        dat.setText(date);
        spiner.setText(spinner);
        cout.setText(count);
        bage.setText(bAge);
        bmaker.setText(bMaker);
        em.setText(email);
        ph.setText(phone);
        in.setText(inst);


        books = new ArrayList<Book>();
        //adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, users);

        pbHorizontal = (ProgressBar) findViewById(R.id.pb_horizontal);
        pbHorizontal.setProgress(80);
    }

    public void phoneCall(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:"+phone));
        startActivity(intent);


    }
    public void instCall(View view) {
       /* Uri appUri = Uri.parse(inst);
        Uri browserUri = Uri.parse(inst);

        try{ //first try to open in instagram app
            Intent appIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");
            if(appIntent != null){
                appIntent.setAction(Intent.ACTION_VIEW);
                appIntent.setData(appUri);
                startActivity(appIntent);
            }
        }catch(Exception e){ //or else open in browser
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, browserUri);
            startActivity(browserIntent);
        }
*/
        Intent iIntent = getPackageManager().getLaunchIntentForPackage("com.instagram.android");

        Uri uri = Uri.parse(inst);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    public void emailCall(View view){
        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        /* Fill it with Data */
        emailIntent.setType("plain/text");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{email});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Text");

        /* Send it off to the Activity-Chooser */
        this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));
    }

    public void ApplyClick(View view) throws IOException {
        pbHorizontal.setProgress(100);

        //Book book = new Book(name, publisher, date, count, spinner, bAge, bMaker, selectedImg, email, phone, inst);



        //books.add(book);

        Log.e("ASDF", name);
        //Log.e("ASDF", book.bookName + book.publisher);


        //newItem.bookName = bookName;
        newItem.bookName =  name;
        newItem.publisher = publisher;
        newItem.date = date;
        newItem.countOfPages = count;
        newItem.bookShalve = spinner;
        newItem.age = bAge;
        newItem.maker = bMaker;
        newItem.bitmap = selectedImg;
        newItem.email = email;
        newItem.phone = phone;
        newItem.inst = inst;

        BookCollectionHelper.itemsList.add(newItem);
        BookCollectionHelper.saveCollection(getExternalFilesDir(null));
        list_activity.listAdapter.notifyDataSetChanged();




       /* boolean result = JSONHelper.exportToJSON(this, books);
        if(result){
            Toast.makeText(this, "Данные сохранены", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Не удалось сохранить данные", Toast.LENGTH_LONG).show();
        }
*/
    }

    public void onClickBack(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch(id){
            case R.id.openList:
                Intent intentl = new Intent(this, list_activity.class);
               /* intentl.putExtra("NEW_ITEM", newItem);
                intentl.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);*/
                intentl.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(intentl);
                return true;
            case R.id.addBook:
                Intent intent = new Intent(this, MainActivity.class);

                startActivity(intent);
                return true;

        }
        //headerView.setText(item.getTitle());
        return super.onOptionsItemSelected(item);
    }


}