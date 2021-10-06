package tns.fit.bstu.lab3;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Book implements Serializable {
    public static List<Book> itemsList;

    public String bookName;
    public String publisher;
    public String date;
    public String countOfPages;
    public String bookShalve;
    public String age;
    public String maker;
    public Uri bitmap = null;
    public String email;
    public String phone;
    public String inst;


    public  Book() {}

    public Book(String bookName, String publisher, String date, String countOfPages,
                String bookShalve, String age, String maker, Uri bitmap, String email, String phone, String inst) {

        this.bookName = bookName;
        this.publisher = publisher;
        this.date = date;
        this.countOfPages = countOfPages;
        this.bookShalve = bookShalve;
        this.age = age;
        this.maker = maker;
        this.bitmap = bitmap;
        this.email = email;
        this.phone = phone;
        this.inst = inst;
    }

    @Override
    public String toString() {
        return bookName + " " + publisher;
    }
}


