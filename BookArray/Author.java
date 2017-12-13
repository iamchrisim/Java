import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.text.*;
import java.awt.*;
import java.util.ArrayList;

//Im_Dohoo

public class Author
{
	private String name;
	private int age;
	private ArrayList<Book> bookList = new ArrayList<Book>();
	
	public Author (String n, int a)
	{
		name = n;
		age = a;
	}
	public String getName()
	{
		return name;
    }
	public void setName(String n)
	{
		name = n;
    }
	public int getAge() 
	{
		return age;
	}
	public void setAge(int a)
	{
		age = a;
	}
	public Book getBook(String title) 
	{
        for(int i = 0; i < bookList.size(); i++){
            String temp = this.bookList.get(i).getTitle();
            if(temp.equals(title))
                return bookList.get(i);
        }
		return null;
    }
	public void setBook(Book b)
	{
		bookList.add(b);
    }
	public String toString()
	{
        return name + " Age:" + age;
	}
}