import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {

        // Login screen
        JFrame f = new JFrame("Fridgy");
        Fridge fridge = new Fridge();
        JButton switchTabs = new JButton("Switch Tabs");
        //f.add(switchTabs);
        JScrollPane fridgePane = fridge.show(f);
        // JScrollPane cartPane = fridge.show(f);
        // f.remove(cartPane);
        // f.repaint();
        // f.revalidate();
        
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //Initiates login window
        //Cart newWindow = new Cart();
        //newWindow.show(f);
        switchTabs.setBounds(0, 0, 450, 50);
        // boolean cart = false;
        // switchTabs.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         if (cart) {
        //             f.remove(fridgePane);
        //             f.add(cartPane);
        //         }
        //         else {
        //             f.remove(cartPane);
        //             f.add(fridgePane);
        //         }
        //         f.repaint();
        //         f.revalidate();
        //     }
        // });
    }
}

