package notepad;

import javax.swing.*;
import java.awt.*;


public class about 
{
    static JFrame window =new JFrame("About Notepad");
    Notepad samp;
    JButton btn;
    public about(Notepad ref)
    {
        samp=ref;
        Container c =window.getContentPane();
        JPanel p1=new JPanel();
        p1.setLayout(new FlowLayout());
        
        String about= "<html>"+"<body>"+"Created by <br>"+
                "Rahul Kumar<br>"+"GCS/132412<br>"+"SLIET Longowal<br>"
                +"</body>"+"</html>";
        JLabel i=new JLabel();
        i.setText(about);
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
