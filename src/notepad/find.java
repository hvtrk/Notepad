package notepad;

import java.awt.Checkbox;
import java.awt.GraphicsEnvironment;
import java.awt.Label;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class find extends JFrame implements ActionListener
{
    int startIndex=0;
    Label l1,l2;
    TextField tf,tr;
    JButton find_btn,find_next,find_replace,replace_all,cancel;
    Notepad samp;
    public find(Notepad mynote)
    {
        super("Find/Replace");
        samp=mynote;
        l1=new Label("Find What: ");
        l2=new Label("Replace With: ");
        tf=new TextField(30);
        tr=new TextField(30);
        find_btn=new JButton("Find");
        find_next=new JButton("Next");
        find_replace=new JButton("Replace");
        replace_all=new JButton("Replace all");
        cancel=new JButton("Cancel");
        
        setLayout(null);
        int label_w =80;
        int label_h=20;
        int tf_w=120;
        l1.setBounds(10,10,label_h,label_w);
        add(l1);
        tf.setBounds(10+label_w,10,tf_w,20);
        add(tf);
        l2.setBounds(10,10+label_h+10,label_w,label_h);
        add(l2);
        tr.setBounds(10+label_w, 10+label_h+10, tf_w, 20);
        add(tr);
        
        find_btn.setBounds(220,10,100,20);
        add(find_btn);
        find_btn.addActionListener(this);
        find_next.setBounds(220,30,100,20);
        add(find_next);
        find_next.addActionListener(this);
        find_replace.setBounds(220,50,100,20);
        add(find_replace);
        find_replace.addActionListener(this);
        replace_all.setBounds(220, 70, 100, 20);
        add(replace_all);
        cancel.setBounds(220,90,100,20);
        add(cancel);
        cancel.addActionListener(this);
        
        int w=340;
        int h=150;
        
        setSize(w,h);
        Point center=GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        setLocation(center.x-w/2,center.y-h/2);
        setVisible(false);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==find_btn)
            find();
        else if(e.getSource()==find_next)
            find_next();
        else if(e.getSource()==find_replace)
            find_replace();
        else if(e.getSource()==replace_all)
            replace_all();
        else if(e.getSource()==cancel)
            this.setVisible(false);
    }
    
    public void find()
    {
        int select_start=samp.ta1.getText().indexOf(tf.getText());
        if(select_start==-1)
        {
            startIndex=0;
            JOptionPane.showMessageDialog(null,"Could not find"+tf.getText()+"!");
            return;
        }
        if(select_start==samp.ta1.getText().lastIndexOf(tf.getText()))
            startIndex=0;
        int select_end=select_start+tf.getText().length();
    }
    
    public void find_next()
    {
        String selection=samp.ta1.getSelectedText();
        try
        {
            selection.equals("");
        }
        catch(NullPointerException e)
        {
            selection=tf.getText();
            try
            {
                selection.equals("");
            }
            catch(NullPointerException e2)
            {
                selection=JOptionPane.showInputDialog("Find: ");
                tf.setText(selection);                
            }
        }
        try
        {
            int select_start=samp.ta1.getText().indexOf(selection,startIndex);
            int select_end=select_start+selection.length();
            samp.ta1.select(select_start,select_end);
            startIndex=select_end+1;
            
            if(select_start==samp.ta1.getText().lastIndexOf(selection))
                startIndex=0;
        }
        catch(NullPointerException e){}
    }
    
    public void find_replace()
    {
        try
        {
            find();
            samp.ta1.replaceSelection(tr.getText());
        }
        catch(NullPointerException e)
        {
            System.out.println("NullPointerException:"+e);
        }
    }
    
    public void replace_all()
    {
        samp.ta1.setText(samp.ta1.getText().replaceAll(tf.getText(),tr.getText()));
    }
}
