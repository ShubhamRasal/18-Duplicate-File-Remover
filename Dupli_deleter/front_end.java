import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.LinkedList;
import javax.swing.border.*;
class front_end extends JFrame implements ActionListener
{
    List l1=new List();
    private JPanel panNorth,panCenter1,panCenter2,panCenter;
    JLabel name,welcome;
    JPanel left;
    JButton b1,b2;
    JTextField jtext;
    File f;
    JMenuBar menubar;
    JMenu menu1;
    JMenuItem menu2;
    JFrame splash;
    front_end()
    {
        splash=new JFrame("Welcome!");
      //  splash.setBackground(new Color(0, 0, 0));
        splash.setBounds(100, 100,700, 250);
        
        welcome=new JLabel("  Welcome to Duplicate File Remover!!");
        welcome.setFont(new Font("Courier New", Font.BOLD, 30)); 
        welcome.setForeground(new Color(100,0,0)); 
        splash.add(welcome);
        
        splash.setResizable(false);
        splash.setDefaultCloseOperation(splash.DO_NOTHING_ON_CLOSE);   //
        splash.setVisible(true);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        splash.setVisible(false);
        splash.dispose();

        menubar=new JMenuBar();
        menubar.setBorder(new BevelBorder(BevelBorder.RAISED));
        menu1=new JMenu("Help");
        menu2=new JMenuItem("About");
        menubar.add(menu1);
        menubar.add(menu2);
        setJMenuBar(menubar);

        menu2.addActionListener(this);
       

        name=new JLabel("Duplicate File Remover");
        name.setFont(new Font("Courier New", Font.BOLD, 30)); 
        name.setForeground(new Color(100,0,0)); 
    
        panNorth = new JPanel();
        panNorth.setBackground(new Color(127,112,219));
        //panNorth.setSize(500, 350);
        panNorth.add(name); 

        b1=new JButton("Choose Folder");
        b2=new JButton("Delete Duplicate Files");
        jtext=new JTextField(20);
        jtext.setBounds(5, 30, 180, 30);
        b1.setBounds(200,30,150,30);
        b2.setBounds(100,80,150,40);
        panCenter1 = new JPanel( );   //new GridLayout(2,1));
        panCenter1.setLayout(null);
                panCenter1.add(jtext);
                panCenter1.add(b1);
                panCenter1.add(b2);
                        
        l1.setBackground(new Color(205, 201, 201));
        l1.setBounds(300, 150, 800,400);
		panCenter2 = new JPanel(new GridLayout());
        panCenter2.add(l1);
            
        panCenter = new JPanel(new GridLayout(1,2));
        panCenter.setBackground(new Color(238,127,238));
        panCenter.add(panCenter1);
        panCenter.add(panCenter2);
        
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.add(panCenter,"Center");  
    this.add(panNorth,"North");
    setTitle("Duplicate File Remover");
    this.setBounds(100,100,800,400);
    this.setResizable(false);
 
        setVisible(true);

        l1.add("Original File:=>Duplicated File");
        l1.add("----------------------------------------------------------------------------------------------------------------------");

        b1.addActionListener(this);
        b2.addActionListener(this);
    }



    public void actionPerformed(ActionEvent e) {
		
        if(e.getSource()==b1)
        {
                System.out.println("Entered!");

               /* FileDialog fileDialog = new FileDialog(this, "Select file");
                fileDialog.setVisible(true);
                System.out.println(fileDialog.getDirectory() + fileDialog.getFile());
            */
            JFileChooser filechooser = new JFileChooser();
            filechooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
             if(filechooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) 
             {
                    f = filechooser.getSelectedFile();
                 //    System.out.println("This file was chosen for opening: " + f);
                    jtext.setText(f.getAbsolutePath());
            }
             else 
            {
                JOptionPane.showMessageDialog(this,"No file was chosen or an error occured.","ERROR",JOptionPane.ERROR_MESSAGE);     
               // System.out.println("No file was chosen or an error occured");
            }
       }else if (e.getSource()==b2)
       {
           try{
        Delete_Duplicate dobj=new Delete_Duplicate();
            dobj.list(f.getAbsolutePath());
        System.out.println("Delete clicked! ");
        LinkedList ll=dobj.getLlist();
        for(int i=0;i<ll.size();i++)
        {
            l1.add(ll.get(i).toString(),i);
        }
        l1.add("Deleted All duplicate files!!!");
        JOptionPane.showMessageDialog(this,"Task Completed!.");  

           }catch(Exception eobj)
       {
           System.out.println(e);
       }
     }//else
     else if(e.getSource()==menu2)
     {
         String msg="Application software (app for short) is";
         String msg1="software designed to perform task of ";
         String msg2="deleting all your duplicate files from \n";
         String msg3="\nthe given inputed folder.It checks files ";
         String msg4="checksum(unique code) on basis of file data.";
        String msg5="If there is another file having 'exactly same";
        String msg6=" data' in given path is deletd.";
        String  msg7="project by Shubham D. Rasal";
        JFrame fobj=new JFrame("About");
             fobj.getContentPane().setLayout(new FlowLayout());
        // JTextField field = new JTextField();
        // field.setText(msg+msg1);
        // field.setEditable(false);
        // fobj.add(field);
  
        JLabel jlab=new JLabel(msg);
        jlab.setFont(new Font("Courier New", Font.BOLD, 20)); 
        fobj.add(jlab);
        JLabel jlab1=new JLabel(msg1);
        jlab1.setFont(new Font("Courier New", Font.BOLD, 20)); 
        fobj.add(jlab1);
        JLabel jlab2=new JLabel(msg2);
        jlab2.setFont(new Font("Courier New", Font.BOLD, 20)); 
        fobj.add(jlab2);
        JLabel jlab3=new JLabel(msg3);
        jlab3.setFont(new Font("Courier New", Font.BOLD, 20)); 
        fobj.add(jlab3);
        JLabel jlab4=new JLabel(msg4);
        jlab4.setFont(new Font("Courier New", Font.BOLD, 20)); 
        fobj.add(jlab4);
        
        JLabel jlab5=new JLabel(msg5);
        jlab5.setFont(new Font("Courier New", Font.BOLD, 20)); 
        fobj.add(jlab5);
        JLabel jlab6=new JLabel(msg6);
        jlab6.setFont(new Font("Courier New", Font.BOLD, 20)); 
        fobj.add(jlab6);
        JLabel jlab7=new JLabel(msg7);
        jlab7.setFont(new Font("Courier New", Font.ITALIC+Font.BOLD, 15)); 
        fobj.add(jlab7);
        
        fobj.setBounds(100, 200, 550, 280);
         fobj.setVisible(true);
         fobj.setResizable(false);
         System.out.println("menu2 clicked");
     }

    }




    public static void main(String args[])
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }catch(Exception ex) {
            ex.printStackTrace();
        }
        new front_end();
    }
}