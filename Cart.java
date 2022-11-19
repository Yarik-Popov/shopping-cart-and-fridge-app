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
    mainframe.setPreferredSize(new Dimension( 450,2000));
    mainframe.setBackground(new java.awt.Color(122, 143, 222));
    
    JScrollPane scrollFrame = new JScrollPane(mainframe);
    scrollFrame.setPreferredSize(new Dimension( 450,2000));
    scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disables horizontal scrolling
    scrollFrame.getVerticalScrollBar().setUnitIncrement(10);
    scrollFrame.getVerticalScrollBar().setPreferredSize(new Dimension(0,0)); // Makes vertical scrollbar's dimensions 0 to make it invisible
    
    f.add(scrollFrame); 
    f.setVisible(true); 
    f.setResizable(false);
  }
}
