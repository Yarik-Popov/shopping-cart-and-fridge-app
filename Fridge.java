import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.awt.Font;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.Scanner;

public class Fridge extends JFrame
{
    public static final int WARNING_DATE = 2;
    public static final String SOURCE_FILE = "fridge.txt";
    public HashMap<String, LocalDate> items; 
    public HashMap<String, Integer> expirations; 
    private JScrollPane scrollFrame;
    private JPanel panel;
    private JButton back;

    public Fridge() 
    {
        super("Fridge");
        items = parseFridge();
        expirations = FileReadWrite.readFile("expiration-database.txt");
        panel = new JPanel();
        scrollFrame = new JScrollPane(panel);
        back = new JButton("Back to Starting Screen");
        showThis();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        back.addActionListener(e ->
        {
            new StartFrame();
            try {
                commitItems();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            dispose();
        });

        addWindowListener(new WindowListener() {
            public void windowClosing(WindowEvent evt) {
                try {
                    commitItems();
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                }
                System.exit(0);
            }

            @Override
            public void windowOpened(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowClosed(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowIconified(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowDeiconified(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowActivated(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void windowDeactivated(WindowEvent e) {
                // TODO Auto-generated method stub
                
            }
           });
    }

    public JScrollPane showThis()
    {
        setSize(450,900); 
        getContentPane().setBackground(new java.awt.Color(122, 143, 222)); 
        
        panel.setLayout(new BoxLayout(panel,1));
        panel.setBackground(new java.awt.Color(122, 143, 222));
        panel.add(item("Food", "Days Until Expiry", false));

        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new java.awt.Color(122, 143, 222));
        p.setSize(400, 30);
        back.setBounds(125, 0, 200, 50);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.LIGHT_GRAY);
        p.add(back);
        panel.add(p);

        items.forEach((k, v) -> {
            System.out.println(k);
            panel.add(item(k, daysLeft(k)+"", true));
        });

        add(scrollFrame); 
        setVisible(true); 
        setResizable(false);
        scrollFrame.getVerticalScrollBar().setPreferredSize(new Dimension(0,0)); // Makes vertical scrollbar's dimensions 0 to make it invisible
        return(scrollFrame);
    }

    private JPanel item(String name, String toExpired, boolean button) {
        JPanel p = new JPanel();
        p.setLayout(null);
        p.setBackground(new java.awt.Color(122, 143, 222));
        p.setSize(400, 30);
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
                    remove(name);
                    //p.repaint();
                    panel.remove(p);
                    panel.repaint();
                    panel.revalidate();
                    System.out.println(items.toString());
                }
            });
            p.add(b);
        }
        return(p);
    }

    /**
     * Read file for Fridge
     */
    public static HashMap<String, LocalDate> parseFridge()
    {
        HashMap<String, LocalDate> rows = new HashMap<String, LocalDate>();
        try {
            Scanner sc = new Scanner(new File(SOURCE_FILE));
            String[] tempRow;
            while (sc.hasNextLine()) {
                tempRow = sc.nextLine().split(":");
                rows.put(tempRow[0], LocalDate.parse(tempRow[1]));
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("File Not Found");
            e.printStackTrace();
        }
        return(rows);
    }

    public static int localTimeDifference(LocalDate date1, LocalDate date2)
    {
        int time = 0;
        while (date1.compareTo(date2) < 0)
        {
            time++;
            date2 = date2.minusDays(1);
        }
        while (date1.compareTo(date2) > 0)
        {
            time--;
            date2 = date2.minusDays(-1);
        }
        return time;
    }

    public void add(String item)
    {
        items.put(item, LocalDate.now());
    }

    public void remove(String item)
    {
        items.remove(item);
    }

    public String toString()
    {
        return items.toString();
    }

    public int daysLeft(String item)
    {
        System.out.println(item);
        int expiryDate = expirations.get(item);
        if(expiryDate == -1)
        {
            return -1;
        }    
        else
        {
            return Fridge.localTimeDifference(LocalDate.now(), items.get(item)) + expiryDate;
        }
    }

    public void commitItems() throws IOException 
    {  
        HashMap<String, LocalDate> file = parseFridge();
        BufferedWriter bw = new BufferedWriter(new FileWriter(SOURCE_FILE, true));
        for (Map.Entry<String, LocalDate> entry : items.entrySet()) {
            file.put(entry.getKey(), entry.getValue());
        }

        PrintWriter writer = new PrintWriter(SOURCE_FILE);
        writer.close();

        items.forEach((k, v) -> {
            try {
                bw.write(k+":"+v.toString());
                bw.newLine();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        });
        bw.close();
    }
}
