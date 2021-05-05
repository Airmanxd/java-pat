package com.example.Practice12;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
@Component
public class Main implements CommandLineRunner {
    private File first_file;
    private File second_file;
    private Hasher hasher;
    @PostConstruct
    public void hasherInit(){
        this.hasher = new Hasher();
    }
    @Override
    public void run(String args[]) throws Exception{
        if(args.length == 2){
            this.first_file = new File(args[0]);
            this.second_file = new File(args[1]);
        }
        else {
            throw new Exception("Not enough file names");
        }
        outputHash(readInput());
    }
    public String readInput() throws FileNotFoundException {
        String data = "";
        if (this.first_file.exists()){
            Scanner myReader = new Scanner(first_file.getName());
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();
        }
        else {
            data = "null";
        }
        System.out.println(data);
        return data;
    }
    public void outputHash(String data) throws NoSuchAlgorithmException, IOException {
        String result = hasher.hashString(data);
        FileWriter fileWriter = new FileWriter(second_file.getName());
        fileWriter.write(result);
        fileWriter.close();
    }
    @PreDestroy
    public void delete_first_file(){
        first_file.delete();
    }
}
