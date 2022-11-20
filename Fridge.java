import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class Fridge
{
    public static final int WARNING_DATE = 2;
    public static final String SOURCE_FILE = "fridge.txt";
    private HashMap<String, LocalDate> items; 
    private HashMap<String, Integer> expirations; 

    /**
     * Read file for Fridge
     */
    public static HashMap<String, LocalDate> parseFridge()
    {
        HashMap<String, LocalDate> rows = new HashMap<String, LocalDate>();
        try {
            Scanner sc = new Scanner(new File(SOURCE_FILE));
            String[] tempRow;
            while (sc.hasNextLine()) {
                tempRow = sc.nextLine().split(":");
                rows.put(tempRow[0], LocalDate.parse(tempRow[1]));
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
        return(rows);
    }

    public static int localTimeDifference(LocalDate date1, LocalDate date2)
    {
        int time = 0;
        while (date1.compareTo(date2) < 0)
        {
            time++;
            date2 = date2.minusDays(1);
        }
        while (date1.compareTo(date2) > 0)
        {
            time--;
            date2 = date2.minusDays(-1);
        }
        return time;
    }

    public Fridge() 
    {
        items = parseFridge();
        expirations = FileReadWrite.readFile("expiration-database.txt");
    }

    /**
     * Adds the item to the items list if the item exists in the database
     * @param item Item to be added
     * @return True if the item exists in the expiration database and adds the item to the items hash and false if the item doesn't exist in the expiration database
     * 
     */
    public boolean add(String item)
    {
        if (expirations.containsKey(item))
        {
            items.put(item, LocalDate.now());
            return true;
        }
        return false;        
    }

    public void remove(String item)
    {
        items.remove(item);
    }

    public String toString()
    {
        return items.toString();
    }

    public int daysLeft(String item)
    {
        int expiryDate = expirations.get(item);
        if(expiryDate == -1)
        {
            return -1;
        }    
        return Fridge.localTimeDifference(LocalDate.now(), items.get(item)) + expiryDate;
    }

    public void commitItems() throws IOException 
    {  
        HashMap<String, LocalDate> file = parseFridge();
        BufferedWriter bw = new BufferedWriter(new FileWriter(SOURCE_FILE, true));
        for (Map.Entry<String, LocalDate> entry : items.entrySet()) {
            file.put(entry.getKey(), entry.getValue());
        }

        PrintWriter writer = new PrintWriter(SOURCE_FILE);
        writer.close();

        items.forEach((k, v) -> {
            try {
                bw.write(k+":"+v.toString());
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bw.close();
    }

    public HashMap<String, LocalDate> getItems()
    {
        return items;
    }

    public HashMap<String, Integer> getExpirations()
    {
        return expirations;
    }
}
