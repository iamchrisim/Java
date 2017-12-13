import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.text.*;
import java.awt.*;
import java.util.Date;

//Im_Dohoo
 
public class Book implements Comparable<Book>
{
    private MyDate date;
    private Author author;
    private String title;
    private double price;
	
    public Book(Author a, String t, double d, MyDate i)
    {
        date = i;
        author = a;
        title = t;
        price = d;
    }
    public void setTitle(String t){
        title = t;
    }
    public String getTitle(){
        return title;
    }
    public void setAuthor(Author t){
        author = t;
    }
    public Author getAuthor(){
        return author;
    }
    public void setDate(MyDate i){
        date = i;
    }
    public MyDate getDate(){
        return date;
    }
    public void setPrice(double d){
        price = d;
    }
    public double getPrice(){
        return price;
    }
    public int compareTo(Book book){
       
        if(this.price > book.getPrice()){
                   return 1;
               }
               else if(this.price == book.getPrice()){
                   return 0;
               }
               else{
                   return -1;
               }
    }
	public String toString()
	{
		return "Book{" +
                " Title:'" + title + '\'' +
                ", Author:" + author + 
                ", $" + price +  
				", Published:" + date +
                '}';
    }
}