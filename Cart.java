import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.awt.Font;
import java.awt.*;

public class Cart extends JFrame
{
    public Cart()
    {
        super("Cart");
        showThis();
    }

    public void showThis()
    {
        Border padding = BorderFactory.createEmptyBorder(10, 20, 0, 25);

        setSize(450,900); 
        getContentPane().setBackground(new java.awt.Color(122, 143, 222)); 

        JPanel mainframe = new JPanel();
        mainframe.setBorder(padding);
        mainframe.setLayout(new BoxLayout(mainframe,1));
        mainframe.setPreferredSize(new Dimension( 450,1000));
        mainframe.setBackground(new java.awt.Color(122, 143, 222));

        JLabel cart_text = new JLabel("Cart", SwingConstants.CENTER);
        cart_text.setFont(new Font("Futura", Font.PLAIN, 45));
        cart_text.setBounds(0,112,450,50);

        mainframe.add(cart_text);

        JPanel item = new JPanel();
        item.setMaximumSize(new Dimension(900,70));
        item.setLayout(null);

        JLabel apples = new JLabel("Apples");
        apples.setBounds(0,0,100,70);

        item.add(apples);

        mainframe.add(item);

        JScrollPane scrollFrame = new JScrollPane(mainframe);
        scrollFrame.setPreferredSize(new Dimension( 450,1000));
        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disables horizontal scrolling
        scrollFrame.getVerticalScrollBar().setUnitIncrement(10);
        scrollFrame.getVerticalScrollBar().setPreferredSize(new Dimension(0,0)); // Makes vertical scrollbar's dimensions 0 to make it invisible

        add(scrollFrame); 
        setVisible(true); 
        setResizable(false);
    }
}
