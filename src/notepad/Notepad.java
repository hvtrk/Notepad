//               @author Rahul Kumar 
package notepad;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.print.attribute.*;
import java.awt.print.*;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import javax.swing.undo.*;
import javax.swing.event.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import java.util.*;
import java.text.SimpleDateFormat;

//import javafx.print.*;
//import javafx.print.PrinterJob;
import java.awt.print.PrinterException;
/**
 *
 * @author rahul
 */
public class Notepad extends JFrame implements ActionListener//, Printable
{  
    JMenuBar mbr;
    JMenu file,edit,format,view,help;
    JMenuItem New,open,save,saveas,pagesetup,print,exit;                            //File Menu Item
    JMenuItem undo,cut,paste,copy,delete,find,findnext,replace,g,selectall,time;    //edit menu item
    JMenuItem wordwrap,font;                                                        //format menu item
    JMenuItem statusbar,about,viewhelp;                                            //help & view menu item
    
    JTextArea ta1;
    String content,path="";
    
    static help abo;
    static about abt;
    static font_chooser fc;
    static find finder;
    
    Notepad()
    {
        setTitle("Notepad");
        setIconImage(Toolkit.getDefaultToolkit().getImage("b.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400,400);
        setVisible(true);
        
        ta1=new JTextArea (10,10);
        ta1.setBackground(Color.WHITE);
        add(ta1);
        
        mbr=new JMenuBar();
        setJMenuBar(mbr);
        
        //initalize the Menu
        {
            file=new JMenu("File");
            edit=new JMenu("Edit");
            format=new JMenu("Format");
            view=new JMenu("View");
            help=new JMenu("Help");
        }
        
        //initialize the Menu Item
        {
            New=new JMenuItem("New");
            open=new JMenuItem("Open");
            save=new JMenuItem("Save");
            saveas=new JMenuItem("Save As");
            pagesetup=new JMenuItem("Page Setup");
            print=new JMenuItem("Print");
            exit=new JMenuItem("Exit");
            
            undo=new JMenuItem("Undo");
            cut=new JMenuItem("Cut");
            copy=new JMenuItem("Copy");
            paste=new JMenuItem("Paste");
            delete=new JMenuItem("Delete");
            find=new JMenuItem("Find");
            findnext=new JMenuItem("Find Next");
            replace=new JMenuItem("Replace");
            g=new JMenuItem("Goto");
            selectall=new JMenuItem("Select All");
            time=new JMenuItem("Time/Date");
            
            wordwrap=new JMenuItem("Word Wrap");
            font=new JMenuItem("Font");
            statusbar=new JMenuItem("Status Bar");
            
            viewhelp=new JMenuItem("View Help");
            about=new JMenuItem("About Notepad");
        }
        
        //Adding The Menu_item to the Menu
        {
            file.add(New);
            file.add(open);
            file.add(save);
            file.add(saveas);
            file.addSeparator();
            file.add(pagesetup);
            file.add(print);
            file.addSeparator();
            file.add(exit);
            
            edit.add(undo);
            edit.addSeparator();
            edit.add(cut);
            edit.add(copy);
            edit.add(paste);
            edit.add(delete);
            edit.addSeparator();
            edit.add(find);
            edit.add(findnext);
            edit.add(replace);
            edit.add(g);
            edit.addSeparator();
            edit.add(selectall);
            edit.add(time);
            
            format.add(wordwrap);
            format.add(font);
            
            view.add(statusbar);
            
            help.add(viewhelp);
            help.addSeparator();
            help.add(about);
        }
        
        //Adding Menu to the menubar
        {
            mbr.add(file);
            mbr.add(edit);
            mbr.add(view);
            mbr.add(format);
            mbr.add(help);
        }
        
        //adding the actionListner to all the 
        {
            New.addActionListener((ActionListener) this);
            open.addActionListener((ActionListener) this);
            save.addActionListener((ActionListener) this);
            saveas.addActionListener((ActionListener) this);
            pagesetup.addActionListener((ActionListener) this);
            print.addActionListener((ActionListener) this);
            exit.addActionListener((ActionListener) this);
            
            undo.addActionListener((ActionListener) this);
            cut.addActionListener((ActionListener) this);
            copy.addActionListener((ActionListener) this);
            paste.addActionListener((ActionListener) this);
            delete.addActionListener((ActionListener) this);
            find.addActionListener((ActionListener) this);
            findnext.addActionListener((ActionListener) this);
            replace.addActionListener((ActionListener) this);
            selectall.addActionListener((ActionListener) this);
            
            wordwrap.addActionListener((ActionListener) this);
            font.addActionListener((ActionListener) this);
            about.addActionListener((ActionListener) this);
            viewhelp.addActionListener((ActionListener) this);
        }
        abo=new help(this);
        abt=new about(this);
        fc=new font_chooser(this);
        finder=new find(this);
        finder.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==New)
            file_new();
        if(e.getSource()==open)
            file_open();
        if(e.getSource()==save)
            file_save();
        if(e.getSource()==saveas)
            file_save_as();
        if(e.getSource()==pagesetup)
            page_setup();
        if(e.getSource()==print)
            file_print();
        if(e.getSource()==exit)
            file_close();
        
        if(e.getSource()==cut)
            edit_cut();
        if(e.getSource()==copy)
            edit_copy();
        if(e.getSource()==paste)
            edit_paste();
        if(e.getSource()==delete)
            edit_delete();
        if(e.getSource()==time)
            edit_timedate();
        if(e.getSource()==selectall)
            edit_selectall();
        if(e.getSource()==find)
            edit_find();
        if(e.getSource()==findnext)
            edit_find_next();
        
        if(e.getSource()==wordwrap)
            format_wordwrap();
        if(e.getSource()==font)
            format_font();
        
        if(e.getSource()==viewhelp)
            help_abo();
        if(e.getSource()==about)
            help_about();
    }

    //Function to Open a new Untitled blank Notepad file
    private void file_new() 
    {
        if(ta1.getText().equals("")|| ta1.getText().equals(content))
        {
            ta1.setText("");
            content="";
            path="";
            setTitle("Untitled_Notepad");
        }
        else
        {
            int a=JOptionPane.showConfirmDialog(null,"Changes will be lost! \n Do you want to save?");
            if(a==0)
                file_save();
            else if(a==1)
            {
                ta1.setText("");
                path="";
                setTitle("Untitled_Notepad");
            }
            else if(a==2)
                return;
        }
    }
    
    //function to OPEN a file 
    private void file_open() 
    {
        JFileChooser fc=new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        int r=fc.showOpenDialog(this);
        if(r == fc.CANCEL_OPTION)
        {
            return;
        }
        File myfile =fc.getSelectedFile();
        if(myfile==null||myfile.getName().equals(""))
        {
            JOptionPane.showMessageDialog(this,"Select a File","Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try
        {
            BufferedReader input= new BufferedReader(new FileReader(myfile));
            StringBuffer str=new StringBuffer();
            String line;
            while((line=input.readLine())!=null)
                str.append(line+"\n");
            ta1.setText(str.toString());
            content=ta1.getText();
            path=myfile.toString();
            setTitle(myfile.getName()+"_Notepad");
        }
        catch(FileNotFoundException e)
        {
            JOptionPane.showMessageDialog(null,"File Not Found: "+e);
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null,"IO Error: "+e);
        }
    }

    //Function to save a file
    private void file_save() 
    {
        if(path.equals(""))
        {
            file_save_as();
            return;
        }
        try
        {
            FileWriter fw;
            fw = new FileWriter(path);
            fw.write(ta1.getText());
            content=ta1.getText();
            fw.close();
        }
        catch(IOException i)
        {
            JOptionPane.showMessageDialog(this,"Failed to Save","Error",JOptionPane.ERROR_MESSAGE);
        }
    }
    

    //function to save as a file  
    private void file_save_as() 
    {
        JFileChooser fr= new JFileChooser();
        fr.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int r= fr.showSaveDialog(this);
        if(r==JFileChooser.CANCEL_OPTION)
            return;
        File myfile=fr.getSelectedFile();
        //System.out.println(myfile);
        if(myfile==null||myfile.getName().equals(""))
        {
            JOptionPane.showMessageDialog(this,"Please Enter a file name","Error",JOptionPane.ERROR_MESSAGE);
            return;
        }
        if(myfile.exists())
        {
            r=JOptionPane.showConfirmDialog(this,"A file already Exists with this name\n Do you want to Rewrit?");
            if(r!=0)
                return;
        }
        try
        {
            FileWriter fw;
            fw=new FileWriter(myfile);
            fw.write(ta1.getText());
            content=ta1.getText();
            setTitle(myfile.getName()+"_Notepad");
            fw.close();
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(this,"Failed to save","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    //Function to Setup page 
    private void page_setup() 
    {
        PrinterJob job;
        job = PrinterJob.getPrinterJob();
        PrintRequestAttributeSet aset=new HashPrintRequestAttributeSet();
        PageFormat pf=job.pageDialog(aset);
        job.setPrintable((Printable) obj,pf);
        boolean ok=job.printDialog(aset);
        if(ok)
        {
            try
            {
                job.print(aset);
            }
            catch(PrinterException ex)
            {
                //the Job did not complete
            }
        }
        System.exit(0);
    }
    
    //Print function
    public int print(Graphics g, PageFormat pf, int page)throws PrinterException
    {
        if(page>0)
        {
            return NO_SUCH_PAGE; 
            //Only one page and that page has no text in it(ZERO based)
        }
        /*since the user(0,0) is outside the viewable range,
          then we need to bring it to the imagable range by transporing
          the x and y coordinates iun the viewable range*/
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(pf.getImageableX(),pf.getImageableY());
        /*Now we can perform the rendring of the obtained image*/
        g.drawString(ta1.getText(), 100, 100);
        /*tell the user that the document has been printed*/
        return PAGE_EXISTS;
    }
int Exit;

    private void file_print() 
    {
        PrinterJob printer = PrinterJob.getPrinterJob();
        //printer.setPrintable(this);
        HashPrintRequestAttributeSet printAttr=new HashPrintRequestAttributeSet();
        printer.setPrintable((Printable) obj);
        if(printer.printDialog(printAttr))
        {
            try
            {
                printer.print(printAttr);
            }
            catch(PrinterException e)
            {
                JOptionPane.showMessageDialog(this,"Failed to print the file:"+e,"Error",JOptionPane.ERROR_MESSAGE); 
            }
        }
    }

    private void file_close() 
    {
        if(ta1.getText().equals(content))
        {
            ta1.setText("");
            path="";
            System.exit(0);
        }
        else if(ta1.getText().equals(" ")&& content==null)
        {
            ta1.setText("");
            path="";
            System.exit(0);
        }
        else
        {
            int a=JOptionPane.showConfirmDialog(null,"The text has been changed\n Do you went to save the change?");
            if(a==0)
            {
                file_save();
            }
            else if(a==1)
            {
                ta1.setText("");
                path="";
                setTitle("Untitled_NotePad");
            }
            else if(a==2)
                return;
        }
    }

    private void edit_cut() 
    {
        ta1.cut();
    }

    private void edit_copy() 
    {
        ta1.copy();
    }

    private void edit_paste() 
    {
        ta1.paste();
    }

    private void edit_delete() 
    {
        String temp=ta1.getText();
        ta1.setText(temp.substring(0,ta1.getSelectionStart())+temp.substring(ta1.getSelectionEnd()));
    }

    private void edit_timedate() 
    {
        try
        {
            int start=ta1.getSelectionStart();
            int end=ta1.getSelectionEnd();
            Calendar cal=Calendar.getInstance();
            SimpleDateFormat sdf=new SimpleDateFormat("dd/mm/yyyy h:m a");
            String now=sdf.format(cal.getTime());
            String temp1=ta1.getText().substring(0,start);
            String temp2=ta1.getText().substring(end);
            ta1.setText(temp1+" "+now+" "+temp2);
            ta1.select(start+1,start+1+now.length());
        }
        catch(NullPointerException e)
        {
            
        }
    }

    private void edit_selectall() 
    {
        ta1.selectAll();
    }
    
    public void format_wordwrap()
    {
        if(ta1.getLineWrap()==false)
        {
            ta1.setLineWrap(true);
        }
        else
        {
            ta1.setLineWrap(false);
        }
    }
    
    public void help_about()
    {
        abt.window.setVisible(true);
    }
    
     public void help_abo()
    {
        abo.window.setVisible(true);
    }
    
    public void format_font()
    {
        fc.window.setVisible(true);
    }
    
    private void edit_find() 
    {
        finder.setVisible(true);
    }
    
    private void edit_find_next() 
    {
        finder.setVisible(true);
    }    
    
    public void edit_replace()
    {
        finder.setVisible(true);
    }
    
    static Notepad obj;
    public static void main(String[] rah) 
    {
        obj=new Notepad();
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.setVisible(true);
    }
}
