package com.company;

import java.util.LinkedList;
import java.util.List;

public class Processing {
    List<String> words1Line;
    List<List> wordsAllLines= new LinkedList<List>();

    public void setWordsAllLines(List<List> wordsAllLines) {
        this.wordsAllLines = wordsAllLines;
    }
    public List<List> getWordsAllLines() {
        return wordsAllLines;
    }
    public void PrintInfo(){
        for(int c=0;c<wordsAllLines.size();c++){
            words1Line=wordsAllLines.get(c);
            System.out.println(words1Line);
        }
    }
    public String PrintToString(){
        String temp="";
        for(int c=0; c<wordsAllLines.size();c++){
            temp=(temp+"\n"+wordsAllLines.get(c));
        }
        System.out.println(wordsAllLines);
        return temp;
    }

    //changes the information on 2 lines
    public void ChangeLines(int a, int b) throws FileNotReadException{
        if(wordsAllLines==null){
            throw new FileNotReadException();
        }
        a--;
        b--;
        List<String> temp1;
        List<String> temp2;
        temp1=wordsAllLines.get(a);
        temp2=wordsAllLines.get(b);
        wordsAllLines.set(a,temp2);
        wordsAllLines.set(b,temp1);
    }


    //changes 2 words on 2 lines
    public void ChangeWords(int l1, int l2, int a, int b) throws FileNotReadException{
        if(wordsAllLines==null){
            throw new FileNotReadException();
        }
        l1--;
        l2--;
        a--;
        b--;
        List<String> tempList1=wordsAllLines.get(l1);
        List<String> tempList2=wordsAllLines.get(l2);
        String tempString1, tempString2;
        tempString1=tempList1.get(a);
        tempString2=tempList2.get(b);
        tempList1.set(a,tempString2);
        tempList2.set(b,tempString1);
        wordsAllLines.set(l1,tempList1);
        wordsAllLines.set(l2,tempList2);
    }


}
