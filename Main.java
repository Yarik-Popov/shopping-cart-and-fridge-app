import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;

import javax.swing.*;

public class Main extends JFrame 
{
    private JButton launchFridge;
    private JButton launchCart;
    private JPanel panel;
    private JLabel label;
    private HashMap<String, Integer> expirations; 
    public static final String EXPIRATION_SOURCE = "expiration-database.txt";
    
    public Main()
    {
        super("Fridgey");
        expirations = FileReadWrite.readFile(EXPIRATION_SOURCE);
        launchFridge = new JButton("Go to Fridge");
        launchCart = new JButton("Go to Cart");
        panel = new JPanel();
        label = new JLabel("Fridgey");
        setSize(450, 900);
        display();
        setResizable(false);
    }

    public static void main(String[] args)
    {
        new Main();
    }

    public void display()
    {
        panel.setLayout(null);
        label.setBounds(170, 100, 100, 50);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Ariel", Font.PLAIN, 25));

        buttonSettings(launchCart, 200);
        buttonSettings(launchFridge, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel.add(launchCart);
        panel.add(launchFridge);
        panel.add(label);
        add(panel);
        panel.setBackground(new Color(122, 143, 222));

        launchFridge.addActionListener(e ->
        {
            Fridge f = new Fridge(expirations);
            f.display();
            dispose();
        });

        launchCart.addActionListener(e ->
        {
            Cart c = new Cart(expirations);
            c.display();
            dispose();
        });

        setVisible(true);
    }

    public void buttonSettings(JButton button, int y)
    {
        button.setForeground(Color.WHITE);
        button.setBounds(150, y, 150, 50);
        button.setBackground(Color.LIGHT_GRAY);
    }
}
