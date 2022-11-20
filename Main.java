import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;
public class Main {
    public static void main(String[] args)  
    {
        new StartFrame();
        // new Fridge();
        // try {
        //     test();
        // } catch (Exception e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    }

    public static void test() throws Exception
    {
        // Login screen
        JFrame f=new JFrame("Fridgy");
        // f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        //Initiates login window
        Fridge fridge = new Fridge();
        fridge.add("apple");
        fridge.add("bananas");
        fridge.commitItems();
        System.out.println(fridge);
        // System.out.println(fridge.daysLeft("banana"));
        // fridge.showThis(f);

        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.addWindowListener(new WindowListener() {
            public void windowClosing(WindowEvent evt) {
                try {
                    fridge.commitItems();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                System.exit(0);
            }

            @Override
            public void windowOpened(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowClosed(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowIconified(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowActivated(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }
           });
    }
}