package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OpenFile {
    FileInputStream FileI=null;
    String path;
    int NumLines=0, NumberWordsOnLine=0;
    List<String> words1Line;
    List<List> wordsAllLines=new LinkedList<>();
    public List<List> getWordsAllLines() {
        return wordsAllLines;
    }
    public void setPath(String path){
        this.path=path;
    }
    public String getPath(){
        return path;
    }
    public void setWordsAllLines(List<List> wordsAllLines) { this.wordsAllLines = wordsAllLines; }

    //Opens the file
    public boolean MyOpenFile () throws IOException, FileNotReadException {
        try{
            FileI=new FileInputStream(path);
        } catch (FileNotFoundException FNFE){
            System.err.println("There is no such file on the specified path");
            return false;
        }
        catch (Exception E){
            System.err.println("Unknown Error");
        } finally {
            if(FileI==null){
                FileI.close();
            }
        }
        SaveToList();
        CloseFile CF=new CloseFile(FileI);
        return true;
    }


    //Saves the file information in the list
    public void SaveToList() throws FileNotReadException {
        String tempString="";
        Scanner file = new Scanner(FileI);
        for (NumLines=0;; NumLines++) {
            tempString=file.nextLine();
            words1Line= new LinkedList<>();
            Scanner tempScanner = new Scanner(tempString);
            for(NumberWordsOnLine=0;;NumberWordsOnLine++){
                words1Line.add((NumberWordsOnLine), tempScanner.next());
                if(!tempScanner.hasNext()){
                    break;
                }
            }
            wordsAllLines.add((NumLines),words1Line);
            if(!file.hasNextLine()){
                break;
            }
            if (wordsAllLines==null){
                throw new FileNotReadException();
            }
        }
    }


}
