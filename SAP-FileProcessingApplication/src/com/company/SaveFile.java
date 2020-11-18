package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class SaveFile {
    FileWriter FileO;
    String path;
    List<List> wordsAllLines= new LinkedList<List>();
    public void setWordsAllLines(List<List> wordsAllLines){
        this.wordsAllLines=wordsAllLines;
    }
    public void setPath(String path) {
        this.path = path;
    }

    //saves the file
    public void SaveFile() throws IOException, FileNotReadException {
        if(wordsAllLines==null){
            throw new FileNotReadException();
        }
        List<String> tempList;
        String tempString;
        FileO= new FileWriter(path);
        for(int c=0; c< wordsAllLines.size();c++){
            tempList=wordsAllLines.get(c);
            for(int i=0;i<tempList.size();i++){
                tempString=tempList.get(i);
                FileO.write(tempString+" ");
            }
            FileO.write("\n");
        }
        CloseFile CF = new CloseFile(FileO);

    }
}
