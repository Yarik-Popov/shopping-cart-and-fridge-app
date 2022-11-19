import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.awt.Font;
import java.awt.*;

public class Cart
{
  public void show(JFrame f)
  {
    f.setSize(450,900); 
    f.getContentPane().setBackground(new java.awt.Color(122, 143, 222)); 

    JPanel mainframe = new JPanel();
    mainframe.setLayout(new BoxLayout(mainframe,1));
    mainframe.add(new JLabel("Lol"));
    mainframe.add(new JLabel("Lolly"));
    mainframe.setPreferredSize(new Dimension( 450,2000));
    mainframe.setBackground(new java.awt.Color(122, 143, 222));
    JScrollPane scrollFrame = new JScrollPane(mainframe);
    scrollFrame.setPreferredSize(new Dimension( 450,2000));
    f.add(scrollFrame); 
    f.setVisible(true); 
    f.setResizable(false);
  }
}
