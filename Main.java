import javax.swing.*;
public class Main {
    public static void main(String[] args) throws Exception {

        // Login screen
        // JFrame f=new JFrame("Fridgy");
        // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        //Initiates login window
        Fridge newWindow = new Fridge();
        newWindow.add("apple");
        newWindow.add("banana");
        newWindow.add("pear");
        newWindow.commitItems();
        // newWindow.show(f);
    }
}