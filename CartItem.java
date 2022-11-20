import java.awt.Dimension;
import java.io.IOException;

import javax.swing.*;

public class CartItem extends JPanel{
    public JLabel nameLabel;
    public JButton remove;
    public JButton strikeOut;

    public CartItem(String name, Cart cart)
    {
        super();
        nameLabel = new JLabel(name);
        remove = new JButton("Remove");
        strikeOut = new JButton("Strikeout");
        
        display();

        remove.addActionListener(e -> {
            cart.removeFromFrame(name, this);
        });

        strikeOut.addActionListener(e -> {
            Fridge f = new Fridge(cart.getExpirations());
            f.addItem(name);
            try {
                f.saveItems();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            cart.removeFromFrame(name, this);
        });
    }

    public void display()
    {
        setLayout(null);
        setSize(400, 30);
        setMaximumSize(new Dimension(900,70));

        nameLabel.setBounds(50,0,100,70);
        remove.setBounds(150, 0, 100, 50);
        strikeOut.setBounds(275, 0, 100, 50);

        add(nameLabel);
        add(remove);
        add(strikeOut);
    }
}
