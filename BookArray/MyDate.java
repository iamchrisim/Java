import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.*;
import java.io.*;
import java.text.*;
import java.awt.*;

//Im_Dohoo

public class MyDate
{
    private int month, day, year;
    public MyDate(int m, int d, int y)
	{
        day = d;
        month =m;
        year = y;
    }
    public void setMonth(int m){
        month = m;
    }
    public int getMonth(){
        return month;
    }
    public void setDay(int d){
        day = d;
    }
    public int getDay(){
        return day;
    }
    public void setYear(int y){
        year = y;
    }
    public int getYear(){
        return year;
    }
    public String toString(){
        return month + "," + day + "," + year;
    }
}