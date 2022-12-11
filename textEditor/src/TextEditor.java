import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    JFrame frame;     //    Frame for textEditor
    JMenuBar menuBar;

    JMenu file,edit;   // Menus

    JMenuItem newFile,openFile,saveFile;   //Menu items for file menu
    JMenuItem cut,copy,paste,selectAll,close;

    //Area for writing text
    JTextArea textArea;



    TextEditor(){    //    Constructor

        frame=new JFrame();                //    Initialize Frame
        menuBar=new JMenuBar();           //        Initialize menu bar

        // Initilize text area
        textArea=new JTextArea();

        // initialze menues
        file=new JMenu("File");
        edit=new JMenu("Edit");

        // initialize menu items for file menu
        newFile=new JMenuItem("New");
        openFile=new JMenuItem("open");
        saveFile=new JMenuItem("Save");

        //Add action listener to file menu items
        newFile.addActionListener(this);
        openFile.addActionListener(this);
        saveFile.addActionListener(this);

        //Adding menu items to file menu
        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);

        //Initialize menu items for edit menu
        cut=new JMenuItem("Cut");
        copy=new JMenuItem("Copy");
        paste=new JMenuItem("Paste");
        selectAll=new JMenuItem("Select All");
        close=new JMenuItem("Close Window");

        //Adding action listener to edit menu bar
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);



        //Adding menu items to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Adding menues to menu bar
        menuBar.add(file);
        menuBar.add(edit);

        frame.setJMenuBar(menuBar);

        frame.add(textArea);

//        Set the dimension for frame
        frame.setBounds(100,100,800,500);

//        mark frame as visible
        frame.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        //if source is cut
        if(actionEvent.getSource()==cut){
            textArea.cut();   //perform  cut event
        }
        //if source is copy
        if(actionEvent.getSource()==copy){
            textArea.copy();   //perform  copy event
            }
        //if source is paste
        if(actionEvent.getSource()==paste){
            textArea.paste();   //perform  paste event
        }
        //if source is selectAll
        if(actionEvent.getSource()==selectAll){
            textArea.selectAll();   //perform  selectAll event
        }
        //if source is close
        if(actionEvent.getSource()==close){
            System.exit(0);     //perform close window -> we want to close whole code so System.exit(0);
        }
        if(actionEvent.getSource()==newFile){
            TextEditor newWindow=new TextEditor();  //perform new window
        }
        //If source is open
        if(actionEvent.getSource()==openFile)
        {
            //Open a text file
            //Initialize file chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            //Get
            int chooseOption=fileChooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file =fileChooser.getSelectedFile();
                String filePath=file.getPath();
                try {
                    BufferedReader bufferedReader=new BufferedReader(new FileReader(filePath));
                    String intermediate="",output="";
                    //Read containt line by line
                    while((intermediate = bufferedReader.readLine())!=null){
                        output=output+intermediate+"\n";
                    }
                    //Set out to text area
                    textArea.setText(output);
                }
                catch(Exception exception){
                    exception.printStackTrace();
                }
            }
        }
        //If source is save file
        if(actionEvent.getSource()==saveFile){
            //Save a file
            //Create a file chooser
            JFileChooser fileChooser=new JFileChooser("C:");
            int chooseOption=fileChooser.showSaveDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    BufferedWriter outfile = new BufferedWriter(new FileWriter(file));
                    textArea.write(outfile);
                    outfile.close();;
                }
                catch (Exception exception){
                    exception.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
//        Initialize text editor
        TextEditor textEditor=new TextEditor();
    }
}
