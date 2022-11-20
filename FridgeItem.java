import java.awt.Dimension;
import javax.swing.*;

public class FridgeItem extends JPanel 
{
    public JLabel nameLabel;
    public JLabel daysLeftLabel;
    public JButton button;

    public FridgeItem(String name, int daysLeft)
    {
        super();
        nameLabel = new JLabel(name);
        daysLeftLabel = new JLabel(daysLeft+"");
        button = new JButton("Remove");
        setLayout(null);
        setSize(400, 30);
        setMaximumSize(new Dimension(900,70));
        setVisuals(nameLabel, 25);
        setVisuals(daysLeftLabel, 150);
        add(button);
        button.setBounds(250, 0, 100, 30);
    }

    private void setVisuals(JLabel label, int x)
    {
        label.setBounds(x, 0, 400, 30);
        add(label);
    }
}
