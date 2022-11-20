import javax.lang.model.util.ElementScanner14;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.awt.*;

public class Cart extends JFrame
{
    public static final String SOURCE_FILE = "cart.txt";

    private ArrayList<String> items;
    private JButton backButton;
    private JPanel panel;
    private HashMap<String, Integer> expirations; 
    private JTextField textField;
    private JButton addButton; 

    public Cart(HashMap<String, Integer> expirations)
    {
        super("Cart");
        items = parseCart();
        backButton = new JButton("Back to Starting Screen");
        panel = new JPanel();
        textField = new JTextField();
        addButton = new JButton("Add Item");
        this.expirations = expirations;
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        backButton.addActionListener(e ->
        {
            try {
                saveItems();
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            new Main();
            dispose();
        });

        addWindowListener(new WindowListener() {
            public void windowClosing(WindowEvent evt) {
                try {
                    saveItems();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                System.exit(0);
            }

            @Override
            public void windowOpened(WindowEvent e) {}

            @Override
            public void windowClosed(WindowEvent e) {}

            @Override
            public void windowIconified(WindowEvent e) {}

            @Override
            public void windowDeiconified(WindowEvent e) {}

            @Override
            public void windowActivated(WindowEvent e) {}

            @Override
            public void windowDeactivated(WindowEvent e) {}
           });
    }

    public HashMap<String, Integer> getExpirations()
    {
        return expirations;
    }

    public void display()
    {
        Border padding = BorderFactory.createEmptyBorder(10, 20, 0, 40);

        setSize(450,900); 
        getContentPane().setBackground(new java.awt.Color(122, 143, 222)); 

        panel.setBorder(padding);
        panel.setLayout(new BoxLayout(panel,1));
        panel.setPreferredSize(new Dimension( 450,1000));
        panel.setBackground(new java.awt.Color(122, 143, 222));
        
        JLabel cartText = new JLabel("Cart", SwingConstants.CENTER);
        cartText.setFont(new Font("Futura", Font.PLAIN, 45));
        cartText.setBounds(0,112,450,50);

        panel.add(cartText);
        setButtonEffects();

        textField.setBounds(0, 175, 450, 50);
        textField.setMaximumSize(new Dimension(450, 50));
        panel.add(new JPanel().add(textField));

        addButton.setBounds(300, 175, 100, 50);
        addButton.setForeground(Color.WHITE);
        addButton.setBackground(Color.LIGHT_GRAY);
        panel.add(addButton);

        addButton.addActionListener(e -> {
            String entry = textField.getText().toLowerCase();
            if (entry.equals(""))
            {
                JOptionPane.showMessageDialog(null, "Input something into the text field");
            }
            else if (!expirations.containsKey(entry))
            {
                JOptionPane.showMessageDialog(null, "There is no food with that name in the expiration database");
            }
            else if (items.contains(entry))
            {
                JOptionPane.showMessageDialog(null, "You already have that food in the Cart");
            }
            else
            {
                addItem(entry);
                panel.add(new CartItem(entry, this));
                panel.repaint();
                panel.revalidate();
            }      
        });

        items.forEach(i -> {
            panel.add(new CartItem(i, this));           
        });

        JScrollPane scrollFrame = new JScrollPane(panel);
        scrollFrame.setPreferredSize(new Dimension( 450,1000));
        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disables horizontal scrolling
        scrollFrame.getVerticalScrollBar().setUnitIncrement(10);
        scrollFrame.getVerticalScrollBar().setPreferredSize(new Dimension(0,0)); // Makes vertical scrollbar's dimensions 0 to make it invisible

        add(scrollFrame); 
        setVisible(true); 
        setResizable(false);
    }

    public void removeFromFrame(String name, CartItem cartItem)
    {
        removeItem(name);
        panel.remove(cartItem);
        panel.repaint();
        panel.revalidate();
    }

    private void setButtonEffects()
    {
        backButton.setBounds(125, 0, 200, 50);
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(Color.LIGHT_GRAY);
        panel.add(backButton);
    }

    public ArrayList<String> parseCart() 
    {
        ArrayList<String> list = new ArrayList<String>();
        try {
            Scanner s = new Scanner(new File(SOURCE_FILE));
            
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return removeDuplicates(list);
    }

    public static <T> ArrayList<T> removeDuplicates(ArrayList<T> list)
    {
  
        // Create a new ArrayList
        ArrayList<T> newList = new ArrayList<T>();
  
        // Traverse through the first list
        for (T element : list) {
  
            // If this element is not present in newList
            // then add it
            if (!newList.contains(element)) {
  
                newList.add(element);
            }
        }
  
        // return the new list
        return newList;
    }

    public void saveItems() throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(SOURCE_FILE, true));
        PrintWriter writer = new PrintWriter(SOURCE_FILE);
        writer.close();

        items.forEach(i -> {
            try {
                bw.write(i);
                bw.newLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        bw.close();
    }

    public void addItem(String item)
    {
        items.add(item);
    }

    public void removeItem(String item)
    {
        items.remove(item);
    }
}
