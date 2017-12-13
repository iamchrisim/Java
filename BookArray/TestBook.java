import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.text.*;
import java.awt.*;
import java.util.Date;
import java.util.Collections; 

//Im_Dohoo

public class TestBook {

    public static void main(String[] args) {
        Book [] books = new Book[10];
        
        books[0] = new Book(new Author("Christopher", 23), "New York Diary", 18.99,new MyDate(1,21,2003));
        books[1] = new Book(new Author("Julia", 36), "Boston Crash", 17.99,new MyDate(11,28,2004));
        books[2] = new Book(new Author("David", 60), "Life in Los Angeles", 8.99,new MyDate(12,20,1996));
        books[3] = new Book(new Author("Bob", 35), "Story of a Jupiterian", 9.99, new MyDate(11,25,1999));
        books[4] = new Book(new Author("Jacob", 32), "Living in Basement", 6.99,new MyDate(2,20,2001));
        books[5] = new Book(new Author("Juliano", 34), "Tacion Amplifier", 7.99, new MyDate(1,13,1999));
        books[6] = new Book(new Author("Abbas", 44), "From Jupiter", 9.99, new MyDate(2,2,1994));
        books[7] = new Book(new Author("Stanford", 49), "Legend of Tigar", 11.99, new MyDate(12,26,2006));
        books[8] = new Book(new Author("Suzuki", 29), "Japanese Foods", 9.99, new MyDate(8,16,2009));
        books[9] = new Book(new Author("Ronal", 1), "Tales Finale", 15.99, new MyDate(10,28,2016));
        System.out.println("------------------------------------------------------------------");
        System.out.println("Book Array Before Sort:\n");
        showBooksArray(books);
        Book newBook = max(books);
        System.out.println("Most Expensive book is: " + newBook.getTitle() + " with a price of: $" + newBook.getPrice());
        //System.out.println("\n\n\nAuthor: " +books[0].getAuthor());
        //System.out.println("\n\n\nAuthor: " +books[0].getAuthor().getName());
        //System.out.println("\n\n" + dehkhoda.getBook("Tales1"));
        //System.out.print("\n\n\n" + books[2].getDate());
        
		//sorts based on price
        System.out.println("------------------------------------------------------------------");
        System.out.println("Sorted based by price:\n");
        Arrays.sort(books);
        showBooksArray(books);

        //Sorts based on title
        System.out.println("------------------------------------------------------------------");
        System.out.println("Sorted based by title:\n");
        Arrays.sort(books, new BookTitleComparator());
        showBooksArray(books);
        
    }
    //displays books
    public static void showBooksArray(Book [] books){
        for(int i = 0; i < books.length; i++){
            System.out.println(books[i]);
            System.out.println();
        }
    }
    //returns the book with the most expensive price
    public static Book max(Book [] books){
        Book temp = books[0];
        double max = 0;
        for(int i = 0; i < books.length; i++){
            
            if(books[i].getPrice() > max){
                max = books[i].getPrice();
                temp = books[i];
            }
        }
        return temp;
    }
}