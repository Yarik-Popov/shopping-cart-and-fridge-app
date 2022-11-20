import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.*;
public class Main {
    public static void main(String[] args)  
    {
        Fridge f = new Fridge(FileReadWrite.readFile(StartFrame.EXPIRATION_SOURCE));
        String[] arr = {"apple", "bananas", "chicken stock"};
        for(int i=0; i<arr.length; i++)
        {
            f.addItem(arr[i]);
        }
        try {
            f.saveItems();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        new StartFrame();
        // new Fridge();
        // try {
        //     test();
        // } catch (Exception e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    }
}