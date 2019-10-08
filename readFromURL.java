/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package readfromurl;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Matt
 */
public class ReadFromURL {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner input=new Scanner (System.in);
        System.out.println("input id");
        String id= input.next();
        String name="";
        
        
        String webAddress= "https://www.ecs.soton.ac.uk/people/"+id;
        try{
            URL webAddressURL=new URL(webAddress);
            BufferedReader URLReader = new BufferedReader( new InputStreamReader(webAddressURL.openStream()));
            
            for (int i = 0; i <=7; i++) {
                name=URLReader.readLine();
            }
            URLReader.close();
            name=name.substring(name.indexOf(">")+1,name.indexOf("|"));
        System.out.println(name);
            
        }
        catch(Exception e){
            System.out.println(e);
        }
       
        
    }
    
}
