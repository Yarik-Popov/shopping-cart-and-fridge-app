import javax.swing.*;
import javax.swing.border.Border;
import java.awt.event.*;
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

    public void display()
    {
        Border padding = BorderFactory.createEmptyBorder(10, 20, 0, 40);
        setSize(450,900); 
        getContentPane().setBackground(new java.awt.Color(122, 143, 222)); 
        
        panel.setLayout(new BoxLayout(panel,1));
        panel.setBackground(new java.awt.Color(122, 143, 222));
        
        JLabel fridgeText = new JLabel("Fridge", SwingConstants.CENTER);
        fridgeText.setFont(new Font("Futura", Font.PLAIN, 45));
        fridgeText.setBounds(0,112,450,50);
        fridgeText.setHorizontalAlignment(SwingConstants.CENTER);
        fridgeText.setBorder(padding);

        panel.setBorder(padding);
        panel.add(fridgeText);
        setButtonEffects();
        panel.add(back);

        items.forEach((k, v) -> {
            FridgeItem f = new FridgeItem(k, daysLeft(k));
            panel.add(f);
            f.button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    removeItem(k);
                    //p.repaint();
                    panel.remove(f);
                    panel.repaint();
                    panel.revalidate();
                    System.out.println(items.toString());
                }
            });
        });

        scrollFrame.setPreferredSize(new Dimension( 450,1000));
        scrollFrame.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Disables horizontal scrolling
        scrollFrame.getVerticalScrollBar().setUnitIncrement(10);
        scrollFrame.getVerticalScrollBar().setPreferredSize(new Dimension(0,0)); // Makes vertical scrollbar's dimensions 0 to make it invisible

        add(scrollFrame); 
        setVisible(true); 
        setResizable(false);
        add(scrollFrame); 
        setVisible(true); 
        setResizable(false);
        scrollFrame.getVerticalScrollBar().setPreferredSize(new Dimension(0,0)); // Makes vertical scrollbar's dimensions 0 to make it invisible
    }

    private void setButtonEffects()
    {
        back.setBounds(125, 0, 200, 50);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.LIGHT_GRAY);
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

    public void addItem(String item)
    {
        items.put(item, LocalDate.now());
    }

    public void removeItem(String item)
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
