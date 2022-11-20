import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.awt.*;

public class Cart extends JFrame
{
    public Cart()
    {
        super("Cart");
    }

    public void display()
    {
        Border padding = BorderFactory.createEmptyBorder(10, 20, 0, 40);

        setSize(450,900); 
        getContentPane().setBackground(new java.awt.Color(122, 143, 222)); 

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(padding);
        mainPanel.setLayout(new BoxLayout(mainPanel,1));
        mainPanel.setPreferredSize(new Dimension( 450,1000));
        mainPanel.setBackground(new java.awt.Color(122, 143, 222));

        JLabel cartText = new JLabel("Cart", SwingConstants.CENTER);
        cartText.setFont(new Font("Futura", Font.PLAIN, 45));
        cartText.setBounds(0,112,450,50);

        mainPanel.add(cartText);

        for(int i=0; i<2; i++)
        {
            JPanel item = new JPanel();
        item.setMaximumSize(new Dimension(900,70));
        item.setLayout(null);

        JLabel apples = new JLabel("Apples"+i);
        apples.setBounds(0,0,100,70);

        item.add(apples);

        mainPanel.add(item);
        }
        

        JScrollPane scrollFrame = new JScrollPane(mainPanel);
        scrollFrame.setPreferredSize(new Dimension( 450,1000));
        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disables horizontal scrolling
        scrollFrame.getVerticalScrollBar().setUnitIncrement(10);
        scrollFrame.getVerticalScrollBar().setPreferredSize(new Dimension(0,0)); // Makes vertical scrollbar's dimensions 0 to make it invisible

        add(scrollFrame); 
        setVisible(true); 
        setResizable(false);
    }
}
