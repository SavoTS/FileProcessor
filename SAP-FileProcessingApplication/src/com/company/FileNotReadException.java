package com.company;

public class FileNotReadException extends Exception {
    public FileNotReadException(){
        super("The file did not read properly");
    }
}
