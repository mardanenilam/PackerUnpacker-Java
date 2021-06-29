import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FileUnacker
{
    FileOutputStream outstream = null; //
    //If we want toexcute the code independetly, there should be main method
    public static void main(String arr[]) throws Exception
    {
        FileUnacker obj = new FileUnacker("combine.txt"); // it will go to the constructor 
    }
    
    public FileUnacker(String src) throws Exception //constructor ,here scr is combine.txt
    {
        unpack(src); //calling unpack function
    }
    public void unpack(String filePath) //filepath is scr
    {
        try
        {
            FileInputStream instream = new FileInputStream(filePath); // to read file
            byte header[]= new byte[100];  // array of header with 100 bytes
            int length = 0; 
            
            while((length = instream.read(header,0,100)) > 0) // will interate till read complete 100 byte
            {
                String str = new String(header);  //converted 100 bytes into string, if we pass the array of bytes to the object string, it will convert automatically
                
                String ext = str.substring(str.lastIndexOf("/")); // need abc.txt which is after last "/" 
                ext = ext.substring(1);  
                
                String[] words=ext.split("\\s"); // it will seperate by using space // abc.txt and size
                String filename = words[0]; // abc.txt  // array of string 
                int size = Integer.parseInt(words[1]); // size of file// string need to convert into int, we are using wrapper class Integer
                
                byte arr[] = new byte[size]; // array created to read data 
                instream.read(arr,0,size); // header 
                System.out.println(filename); //
                FileOutputStream fout=new FileOutputStream(filename); //abc.txt file get created
                fout.write(arr,0,size); // data, size written
            }
        }
        
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
