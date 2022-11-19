import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.HashMap;

public class Fridge
{
    public static final int WARNING_DATE = 2;
    public HashMap<String, LocalDate> items; 

    public Fridge() 
    {
        items = new HashMap<>();

        // System.out.println(START_DATE);
        //File fridgeFile = new File("fridge.txt");
        //Scanner sc = new Scanner(fridgeFile);

       //sc.useDelimiter("\\Z");
 
    //System.out.println(sc.next());
    }

    public void add(String item)
    {
        items.put(item, LocalDate.now());
    }

    public String toString()
    {
        return items.toString();
    }

    public void commit() throws IOException 
    {  
    BufferedWriter bw = new BufferedWriter(new FileWriter("fridge.txt", true));
    items.forEach((k, v) -> {
        try {
            bw.write(k+":"+v.toString());
            bw.newLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    });
    bw.close();
    }
}
