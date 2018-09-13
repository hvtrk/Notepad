package notepad;

import javax.swing.*;
import java.awt.*;


public class help 
{
    static JFrame window =new JFrame("Help");
    Notepad samp;
    JButton btn;
    public help(Notepad ref)
    {
        samp=ref;
        Container c =window.getContentPane();
        JPanel p1=new JPanel();
        p1.setLayout(new FlowLayout());
        
        String help= "<html>"+"<body>"+"Help Currently Not available<br>"+"</body>"+"/html>";
        JLabel i=new JLabel();
        i.setText(help);
        p1.add(i);
        int w = 340;
        int h = 350;
        window.setSize(w,h);
        Point center=GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        window.setLocation(center.x-w/2,center.y/2);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        window.setVisible(false);
        window.add(p1);
    }
}
