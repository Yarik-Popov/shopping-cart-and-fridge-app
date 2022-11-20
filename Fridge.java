import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.awt.Font;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Fridge
{
    private JScrollPane scrollFrame;
    private JPanel mainframe;
    public JScrollPane show(JFrame f)
    {
        f.setSize(450,900); 
        f.getContentPane().setBackground(new java.awt.Color(122, 143, 222)); 
    
        mainframe = new JPanel();
        mainframe.setLayout(new BoxLayout(mainframe,1));
        mainframe.setBackground(new java.awt.Color(122, 143, 222));
        mainframe.add(item("Food", "Days Until Expiry", false));
        HashMap<String, Integer> fridgeItems = FileReadWrite.readFile("fridge.txt");
        for (Map.Entry<String, Integer> entry : fridgeItems.entrySet()) {
            mainframe.add(item(entry.getKey(), Integer.toString(entry.getValue()), true));
        }

        scrollFrame = new JScrollPane(mainframe);
        f.add(scrollFrame); 
        f.setVisible(true); 
        f.setResizable(false);
        scrollFrame.getVerticalScrollBar().setPreferredSize(new Dimension(0,0)); // Makes vertical scrollbar's dimensions 0 to make it invisible
        return(scrollFrame);
    }

    private JPanel item(String name, String toExpired, boolean button) {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new java.awt.Color(0, 0, 0, 0));
        p.setPreferredSize(new Dimension(450, 50));
        JLabel key = new JLabel(name);
        key.setBounds(50, 0, 400, 30);
        p.add(key);
        JLabel value = new JLabel(toExpired);
        value.setBounds(200, 0, 400, 30);
        p.add(value);
        if (button) {
            JButton b = new JButton("Delete");
            b.setBounds(300, 0, 100, 30);
            b.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    FileReadWrite.deleteItem("fridge.txt", name);
                    //p.repaint();
                    mainframe.remove(p);
                    mainframe.repaint();
                    mainframe.revalidate();
                }
            });
            p.add(b);
        }
        return(p);
    }
}

