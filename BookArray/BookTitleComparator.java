import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.text.*;
import java.awt.*;
import java.util.Comparator; 

//Im_Dohoo

public class BookTitleComparator implements Comparator<Book> {

    public int compare(Book a,Book b)
	{
        return a.getTitle().compareTo(b.getTitle());
    }
}