import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton; 
import javax.swing.JOptionPane; 
import javax.swing.border.*;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter; 
import javax.swing.UIManager;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener; 
import java.awt.event.ActionEvent;
import java.awt.Dimension; 
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit; 
import java.awt.image.BufferedImage; 
import java.io.File;
import java.util.Date;

public class AnandBind extends JFrame implements ActionListener{  
    JTextArea imageNameShowTA;
    JTextField locationTF;
    String defaultLocation="D:\\"; 
    String imageName="AnandBind"; 
    int count=0;
    JButton selectLocationBTN,captureImage;

    public AnandBind(String title)
    { 
        this.setTitle(title);
        try 
        { 
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception e){}
        
        JPanel mainPanel=new JPanel();
        mainPanel.setLayout(null); 
        mainPanel.setBackground(new Color(154, 171, 169));
        
        locationTF=new JTextField(defaultLocation);
        locationTF.setEditable(false);
        locationTF.setFont(new Font("Arial",Font.BOLD,12));
        locationTF.setForeground(new Color(255,255,255));
        locationTF.setBackground(new Color(79, 97, 110));
        locationTF.setBounds(15,25,260,22);
        mainPanel.add(locationTF);

        selectLocationBTN=new JButton("Browse");
        selectLocationBTN.setFont(new Font("Arial",Font.BOLD,13));
        selectLocationBTN.setForeground(new Color(0,0,0));
        selectLocationBTN.setBackground(new Color(79, 97, 110));
        selectLocationBTN.setBounds(285,25,100,22);
        selectLocationBTN.addActionListener(this);
        mainPanel.add(selectLocationBTN);
        
        imageNameShowTA=new JTextArea();  
        JScrollPane jScrollPane=new JScrollPane(imageNameShowTA);
        jScrollPane.setBounds(15,55,367,220); 
        mainPanel.add(jScrollPane);  

        captureImage=new JButton("Capture Image");  
        captureImage.setBounds(15,280,365,22);
        captureImage.setFont(new Font("Arial",Font.BOLD,13)); 
        captureImage.addActionListener(this);
        mainPanel.add(captureImage);

        JLabel createrName=new JLabel("Created By- Anand Bind");
        createrName.setBounds(55,315,250,20);
        createrName.setFont(new Font("Arial",Font.BOLD,20));
        createrName.setForeground(Color.red);
        mainPanel.add(createrName);


        this.add(mainPanel);
    }  

    public void actionPerformed(ActionEvent e)
    {   
        if(e.getSource()==captureImage)
        {
            try  
            {   
                String name=imageName+"_"+Long.toString(new Date().getTime())+".jpg"; 
                imageNameShowTA.append(name+"\n");
                Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); 
                BufferedImage screenImage = new Robot().createScreenCapture(new Rectangle(screenSize)); 
                ImageIO.write(screenImage, "JPG", new File(defaultLocation+"\\"+name)); 
            } 
            catch(Exception f) { } 
        }
        else if(e.getSource()==selectLocationBTN)
        {
            JFileChooser jFileChooser = new JFileChooser(defaultLocation);
            jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            jFileChooser.setDialogTitle("Folder Select"); 
            int r = jFileChooser.showOpenDialog(null);
            if (r == JFileChooser.APPROVE_OPTION) 
            {   
                defaultLocation=jFileChooser.getSelectedFile().getPath();
                locationTF.setText(defaultLocation);
            } 
            else
                JOptionPane.showMessageDialog(this,"No File Select");
        }
    }  
    public static void main(String args[])
    {  
        AnandBind frame=new AnandBind("Screenshot developed By Anand Bind"); 
        frame.setSize(417,385); 
        frame.setVisible(true); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }  
} 
