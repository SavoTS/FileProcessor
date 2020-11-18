package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class MainUI extends JFrame {

    //creates the UI
    private JPanel panel1;
    private JButton SwapWordsbnt;
    private JScrollPane WritingPane;
    private JButton SwapLinesbnt;
    private JButton OpenFilebnt;
    private JButton SaveFilebnt;
    private JTextField FieldA;
    private JTextField FieldB;
    private JTextField FileLocation;
    private JTextPane textPane1;
    private JTextField FieldL1;
    private JTextField FieldL2;
    private JFileChooser FC = new JFileChooser();

    public MainUI() throws IOException {

        //creates objects and the UI
        OpenFile OF = new OpenFile();
        Processing FP = new Processing();
        SaveFile SF = new SaveFile();
        setTitle("FileProcessingApplication");
        add(panel1);
        textPane1.setText("Hello World!\n\n!!!WARNING - the application was designed to work with txt files, use other formats at your own risk!!!\n\nTo swap 2 lines, write them (numbers) in the bottom fields\nTo swap 2 words write them (numbers) in the upper fields and the line on which they are (numbers) in the bottom field, left line for left word and right line for right word");
        setSize(800,450);


        //Actions when you press OpenFile button
        OpenFilebnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //asks if you want to close the information from the previous opening
                int confirm;
                confirm = JOptionPane.showConfirmDialog(panel1, "Do you want to close the opened data first?", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (confirm == JOptionPane.YES_OPTION) {
                    FP.setWordsAllLines(null);
                    OF.setWordsAllLines(new LinkedList<>());
                }

                //opens file chooser
                FC=new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                if(FC.showSaveDialog(null)==JFileChooser.APPROVE_OPTION){
                    FileLocation.setText(FC.getSelectedFile().getAbsolutePath());
                    try {

                        //opens the file
                        String tempString = FC.getSelectedFile().getAbsolutePath();
                        OF.setPath(tempString);
                        try {
                            OF.MyOpenFile();
                        } catch (FileNotFoundException FNFE) {
                            JOptionPane.showMessageDialog(panel1, "There is no such file in the specified location");
                        } catch (FileNotReadException FNRE) {
                            JOptionPane.showMessageDialog(panel1, "The file wasn't read properly");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel1, "File Error");
                    }

                    //saves the file in the list and prints the saved list
                FP.setWordsAllLines(OF.getWordsAllLines());
                WriteInTxtPane(FP.PrintToString());
            }
        }});

        //Actions when you press SaveFile button
        SaveFilebnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Saves the file in the location given by the FileLocationField
                    try {
                        SF.setWordsAllLines(FP.getWordsAllLines());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel1, "File reading error");
                    }
                    SF.setPath(getFileLocation());
                    try {
                        SF.SaveFile();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(panel1, "File saving error");
                    }

            }
        });

        //Actions when you press SwapLines button
        SwapLinesbnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //swaps the lines by given index, numbers, of 2 lines
                try{
                    FP.ChangeLines(Integer.parseInt(getFieldL1()),Integer.parseInt(getFieldL2()));
                }catch (FileNotReadException FNRE){
                    JOptionPane.showMessageDialog(panel1, "File reading error");
                }catch (NumberFormatException NFE){
                    JOptionPane.showMessageDialog(panel1, "You didn't input numbers");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(panel1, "Unknown error");
                }
                WriteInTxtPane(FP.PrintToString());
            }
        });

        //Actions when you press SwapWords button
        SwapWordsbnt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //swaps 2 words by given word index, number, and line index, number
                try{
                    FP.ChangeWords(Integer.parseInt(getFieldL1()),Integer.parseInt(getFieldL2()),Integer.parseInt(getFieldA()),Integer.parseInt(getFieldB()));
                }catch (FileNotReadException FNRE){
                    JOptionPane.showMessageDialog(panel1, "File reading error");
                }catch (NumberFormatException NFE){
                    JOptionPane.showMessageDialog(panel1, "You didn't input numbers");
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(panel1, "Unknown error");
                }
                WriteInTxtPane(FP.PrintToString());
            }
        });

        //Action when you start writing in the FileLocation field
        FileLocation.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if(getFileLocation().equals("Path to the file you want to open"))
                FileLocation.setText("");
                super.focusGained(e);
            }
        });

        //Action when you start writing in the WordA field
        FieldA.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                FieldA.setText("");
                super.focusGained(e);
            }
        });

        //Action when you start writing in the WordB field
        FieldB.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                FieldB.setText("");
                super.focusGained(e);
            }
        });

        //Action when you start writing in the LineA field
        FieldL1.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                FieldL1.setText("");
                super.focusGained(e);
            }
        });

        //Action when you start writing in the LineB field
        FieldL2.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                FieldL2.setText("");
                super.focusGained(e);
            }
        });
    }

    //returns the text from the fields
    public String getFieldA(){
        return FieldA.getText();
    }
    public String getFieldB(){
        return FieldB.getText();
    }
    public String getFileLocation(){
        return FileLocation.getText();
    }
    public String getFieldL1(){return FieldL1.getText();}
    public String getFieldL2(){return FieldL2.getText();}


    //Writes the given string in the text pane
    public void WriteInTxtPane(String write){
        textPane1.setText(write);
    }


}
