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

public class FilePacker // class should be complete to give access 
{
    // characteristics of class
    FileOutputStream outstream = null; // can use anywhere 
    String ValidExt[] = {".txt",".c",".java",".cpp"}; // valid extensions
    
    public static void main(String are[]) throws Exception
    { // created object of class which is used to access all the method from class
        FilePacker obj = new FilePacker("C:JAVA/PackerUnpacker/Demo" /*absolute path */ ,"combine.txt"); // packed output will go in combine.txt file
    }
    public FilePacker(String src, String Dest) throws Exception // constructor
    {
    System.out.println(src +" "+ Dest); // paraemters 
    File outfile =new File(Dest); // file is inbuilt class of java.io.File //  new file will create here with combine.txt
    File infile = null;   //to read file , which is set to null
    outstream = new FileOutputStream(Dest); // to write in file.. dest object is passed , reference is created on line number 19. 
    
        File folder = new File(src); //
        
        System.setProperty("user.dir",src);// user.dir// current directory
        
        listAllFiles(src); //to travel the directory(demo)
    }
    
    public void listAllFiles(String path) // directory name // demo
    {
        try(Stream<Path> paths = Files.walk(Paths.get(path))) // coded try block/ inline try block(with circular brackets) // exception prone syntax(can generate exception)
        {   // created stream(sequence of byte) of path. // path is demo directory // walk:
            paths.forEach(filePath -> 
                          {
                              if (Files.isRegularFile(filePath)) // check file is regular or not// isRegularFile give true or flase return value
                              {   // if true it will go in if condition
                                  try
                                  {
                                      String name = filePath.getFileName().toString();
									  // with getFileName will get abc.txt from the path "C:/Marvellous/PackerUnpacker/Demo/abc.txt"  
                                      String ext = name.substring(name.lastIndexOf(".")); // .txt is substring of string(after . whatever)
                                      List<String> list = Arrays.asList(ValidExt); // array ValidExt is created at line no.20
									  if(list.contains(ext)) // this will iterate for all files from path
                                      {
                                          File file=new File(filePath.getFileName().toString()); // created object of new file and open that file to travel
                                          
                                          Pack(file.getAbsolutePath()); // get absolute path of that file, it will go to the function "pack"
                                      }
                                  }
                                  catch (Exception e)
                                  {
                                              System.out.println(e);
                                  }
                              }
                          });
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    public void Pack(String filePath) // sending path of one file at a time, e.g "abc.txt"
    {
        FileInputStream instream = null; //to read the data from file , used reference instream which is declared st line no. 19
		//file handling is part of checked exception when you read or write the data from or in fileit may generate exception so we shouldd handle it with try catch 
        try
        {
            byte[] buffer = new byte[1024]; // character and byte are two d.t. character is of 2 bytes, because java supports unicode //
            int length;
            byte temp[] = new byte[100]; // header of array 
            
            File fobj = new File(filePath); // path abc.txt // size 10 byte 
            String Header = filePath+" "+fobj.length(); // file path , space , length of file
            for (int i = Header.length(); i < 100; i++) // this loop will interate 90 times if you have data of size 10 
                Header += " ";  // when we want uppack these files, we can use space " " as a delimiter 
            
            temp = Header.getBytes(); //string converted into byte 
            
            instream = new FileInputStream(filePath);
            outstream.write(temp, 0, temp.length); // here we write header which is of 100 bytes
            
            while ((length = instream.read(buffer)) > 0)
            {
                outstream.write(buffer, 0, length); // here we write data of file
            }
        
            instream.close();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
