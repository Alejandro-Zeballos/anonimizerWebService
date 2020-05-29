/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.anonymousmaker;

import com.sun.jndi.toolkit.url.Uri;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


/**
 *
 * @author janet
 */
@WebService(serviceName = "MakeNamesAnonimous")
public class MakeNamesAnonimous {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "Anonimize")
    public String anonimize(@WebParam(name = "text") String txt) {
        
        List<String> names = readNamesFromCsvFile("names.csv");
        String[] textWords = txt.split(" ");
        for(int k=0; k<textWords.length; k++){
            if(names.contains(textWords[k])){
                textWords[k] = generateAmpersans(textWords[k]);
           }
        }
        
        String newString = generateNewString(textWords);
        
        return newString;
    } 
    
    private List<String> readNamesFromCsvFile(String filename){
        List<String> names = new ArrayList<>();
        
        Path path = FileSystems.getDefault().getPath(filename);
    
        try(BufferedReader br = Files.newBufferedReader(path, StandardCharsets.US_ASCII)){
            String line = br.readLine();
            while(line!=null){
                names.add(line);
                line = br.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        
        return names;
        
    }
    
    private String generateAmpersans(String word){

        String hiddenWord = "";
        for(int k=0; k<word.length(); k++){
            hiddenWord = hiddenWord.concat("*");
        }

        return hiddenWord;
    }
    
    private String generateNewString(String[] words){
        String newText = "";

        for(int k=0; k < words.length; k++){
            System.out.println(newText);
            newText = newText.concat(words[k]+ " ");
            
        }
        return newText;
    }
}
