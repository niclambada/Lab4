package tns.fit.bstu.lab3;

import static android.app.PendingIntent.getActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    //private ProgressBar pbHorizontal;
    private DatePicker mDatePicker;
    private RadioButton mCalendarRadionButton;
    private RadioButton mSpinnerRadioButton;

    Book newItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //pbHorizontal = (ProgressBar) findViewById(R.id.progressBar);
        mDatePicker = (DatePicker) findViewById(R.id.datePicker);

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.optGroup);
        mCalendarRadionButton = (RadioButton) findViewById(R.id.optCalendar);
        mSpinnerRadioButton = (RadioButton) findViewById(R.id.optSpinners);
        mDatePicker = (DatePicker) findViewById(R.id.datePicker);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (mCalendarRadionButton.isChecked()) {
                    mDatePicker.setCalendarViewShown(true);
                    mDatePicker.setSpinnersShown(false);

                } else if (mSpinnerRadioButton.isChecked()) {
                    mDatePicker.setCalendarViewShown(false);
                    mDatePicker.setSpinnersShown(true);

                } else {
                    mDatePicker.setCalendarViewShown(true);
                    mDatePicker.setSpinnersShown(true);

                }
            }
        });

        Intent intent = getIntent();
        newItem = (Book)intent.getSerializableExtra("NEW_ITEM");
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


    public void onClick(View view) {


        EditText bookName = findViewById(R.id.bookName);
        EditText publisher = findViewById(R.id.publisher);
        DatePicker date = findViewById(R.id.datePicker);

        String bname = bookName.getText().toString();
        String publ = publisher.getText().toString();
        String datepick = date.getDayOfMonth() + "/" + (date.getMonth() + 1) + "/" + date.getYear();

        if(bname.matches("") || publ.matches("")){
            Toast.makeText(this, "???? ?????? ???????? ??????????????????!", Toast.LENGTH_SHORT).show();
        }
        else {
            Intent intent = new Intent(this, SecondActivity.class);

            intent.putExtra("bookName",bname);
            intent.putExtra("publisher",publ);
            intent.putExtra("date",datepick);

            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        }


    }
}