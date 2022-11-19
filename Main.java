import java.io.IOException;
import java.time.LocalDate; // import the LocalDate class

public class Main {
  public static void main(String[] args) {
//    LocalDate myObj = LocalDate.now(); // Create a date object
//     LocalDate myObj1 = myObj.plusDays(15);
//     System.out.println(myObj); // Display the current date
//    System.out.println(myObj1.toString());
//    System.out.println(myObj.compareTo(myObj1));
//    System.out.println(myObj1.compareTo(myObj));
Fridge fridge = new Fridge();
fridge.add("Apple");
System.out.println(fridge);
try {
    fridge.commit();
} catch (IOException e) {
    // TODO Auto-generated catch block
    e.printStackTrace();
}
  }
}
