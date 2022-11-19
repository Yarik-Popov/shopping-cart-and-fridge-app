import java.io.IOException;
import java.time.LocalDate; // import the LocalDate class

public class Main {
    public static void main(String[] args) 
    {
    //    LocalDate myObj = LocalDate.now(); // Create a date object
    //     LocalDate myObj1 = myObj.plusDays(14);
    //     System.out.println(myObj); // Display the current date
    //    System.out.println(myObj1.toString());
    //    System.out.println(myObj.compareTo(myObj1));
    //    System.out.println(myObj1.compareTo(myObj));
    //    int h = myObj.hashCode();
    //    int h1 = myObj1.hashCode();
    //    int difference = h1 - h;
    //    System.out.println(difference);
    //    System.out.println(Fridge.localTimeDifference(myObj1, myObj));
    Fridge fridge = new Fridge();
    fridge.add("apple");
    fridge.remove("banana");
    System.out.println(fridge);
    System.out.println(fridge.daysLeft("apple"));
    try 
        {
            fridge.commitItems();
        } 
    catch (IOException e) 
        {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
    }
}
