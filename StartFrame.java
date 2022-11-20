import java.awt.Color;
import java.awt.Font;

import javax.swing.*;

public class StartFrame extends JFrame 
{
    public JButton launchFridge;
    public JButton launchCart;
    public JPanel panel;
    public JLabel label;

    public StartFrame()
    {
        super("Fridgey");
        launchFridge = new JButton("Go to Fridge");
        launchCart = new JButton("Go to Cart");
        panel = new JPanel();
        label = new JLabel("Fridgey");
        setSize(450, 900);
        display();
    }

    public void display()
    {
        panel.setLayout(null);
        label.setBounds(170, 100, 100, 50);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Ariel", Font.PLAIN, 30));

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
            Fridge f = new Fridge();
            f.display();
            dispose();
        });

        launchCart.addActionListener(e ->
        {
            Cart c = new Cart();
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
