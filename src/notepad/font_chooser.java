package notepad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import java.util.Vector;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class font_chooser implements ActionListener, ListSelectionListener
{
    static JFrame window=new JFrame("Font Chooser");
    Notepad samp;
    
    JLabel flist_label,fsize_label,fstyle_label,fprev_label,preview;
    JList flist,fsize,fstyle;
    ScrollPane flist_sc, fstyle_sc,fsize_sc;
    JButton ok,cancel;
    
    GraphicsEnvironment ge;
    String font_names[];
    Font sample;
    
    String font_name;
    int font_size,font_style;
    
    public font_chooser(Notepad ref)
    {
        samp=ref;
        window.setLayout(null);
        
        //Creating the font
        ge=GraphicsEnvironment.getLocalGraphicsEnvironment();
        font_names=ge.getAvailableFontFamilyNames();
        flist=new JList(font_names);
        flist.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        flist_label=new JLabel("Font: ");
        window.add(flist_label);
        flist_label.setBounds(10, 10, 120, 20);
        flist_sc=new ScrollPane();
        flist_sc.add(flist);
        flist_sc.setBounds(10, 30, 120, 200);
        window.add(flist_sc);
        flist.addListSelectionListener(this);
        
        //font style box
        String styles[]={"Regular","Bold","Italic","Bold Italic"};
        fstyle=new JList(styles);
        fstyle.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fstyle_label=new JLabel("Style: ");
        window.add(fstyle_label);
        fstyle_label.setBounds(140,10,80,20);
        fstyle_sc=new ScrollPane();
        fstyle_sc.add(fstyle);
        fstyle_sc.setBounds(140,30,80,70);
        window.add(fstyle_sc);
        fstyle.addListSelectionListener(this);
        
        //font size box
        Vector<String> a=new Vector<String>(40,1);
        int i;
        for(i=8;i<=100;i+=2)
        {
            a.addElement(String.valueOf(i));
        }
        fsize=new JList(a);
        fsize.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fsize_label=new JLabel("Size: ");
        fsize_label.setBounds(140,110,80,20);
        window.add(fsize_label);
        fsize_sc=new ScrollPane();
        fsize_sc.add(fsize);
        fsize_sc.setBounds(140,130,80,100);
        window.add(fsize_sc);
        fsize.addListSelectionListener(this);
        
        //ok and cancel button
        ok=new JButton("Ok");
        ok.setBounds(230,30,75,20);
        ok.addActionListener(this);
        window.add(ok);
        cancel=new JButton("Cancel");
        cancel.setBounds(230,50,75,20);
        cancel.addActionListener(this);
        window.add(cancel);
        
        fprev_label=new JLabel("Preview: ");
        fprev_label.setBounds(10,230,300,20);
        window.add(fprev_label);
        
        //Preview
        preview=new JLabel("Sample Text");
        preview.setBounds(10,250,290,85);
        preview.setHorizontalAlignment(SwingConstants.CENTER);
        preview.setOpaque(true);
        preview.setBackground(Color.white);
        preview.setBorder(new LineBorder(Color.BLACK,1));
        window.add(preview);
        int w=320,h=380;
        window.setSize(w,h);
        
        //set window Positionn
        Point center=GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
        window.setLocation(center.x-w/2,center.y-h/2+25);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        window.setVisible(false);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==ok)
            ok();
        else if(e.getSource()==cancel)
            cancel();
    }
    
    public void valueChanged(ListSelectionEvent i)
    {
        if(i.getSource()==flist)
        {
            preview.setText(flist.getSelectedValue().toString());
            changeFontSample();
        }
        else if(i.getSource()==fsize)
        {
            changeFontSample();
        }
        else if(i.getSource()==fstyle)
        {
            changeFontSample();
        }
    }
    
    
    private void changeFontSample()
    {
        try
        {
            font_name=flist.getSelectedValue().toString();
        }
        catch(NullPointerException npe)
        {
            font_name="Verdana";
        }
        try
        {
            font_style=getStyle();
        }
        catch(NullPointerException npe)
        {
            font_style=Font.PLAIN;
        }
        try
        {
            font_size=Integer.parseInt(fsize.getSelectedValue().toString());
        }
        catch(NullPointerException npe)
        {
            font_size=12;
        }
        sample=new Font(font_name,font_style,font_size);
        preview.setFont(sample);
    }
    private int getStyle()
    {
        if(fstyle.getSelectedValue().toString().equals("Bold"))
        {
            return Font.BOLD;
        }
        if(fstyle.getSelectedValue().toString().equals("Italic"))
        {
            return Font.ITALIC;
        }
        if(fstyle.getSelectedValue().toString().equals("Bold Italic"))
        {
            return Font.BOLD+Font.ITALIC;
        }
        
        return Font.PLAIN;
    }
    private void ok()
    {
        try
        {
            samp.ta1.setFont(sample);
        }
        catch(NullPointerException npe)
        {
            
        }   
        this.window.setVisible(false);
    }
    
    private void cancel()
    {
        this.window.setVisible(false);
    }
}

