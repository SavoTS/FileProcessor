package com.company;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;


public class CloseFile {

    //closes the input stream
    public CloseFile(FileInputStream path){
        try{
            path.close();
        }catch (IOException IOE){
            System.err.println("Error closing input file stream");
        }catch (Exception E){
            System.err.println("Unknown error");
        }
        System.out.println("File input stream closed successfully");
    }

    //closes the fileWriter
    public CloseFile(FileWriter path){
        try{
            path.close();
        }catch (IOException IOE){
            System.err.println("Error closing output file stream");
        }catch (Exception E){
            System.err.println("Unknown error");
        }
        System.out.println("File output stream closed successfully");
    }
}
