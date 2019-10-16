/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barebonesinterpreter;


import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 *
 * @author Matt
 */
public class BareBonesInterpreter {

   public ArrayList<Integer> data=new ArrayList<>(); 
   public ArrayList<String> names=new ArrayList<>();
   public ArrayList<Integer> Loops=new ArrayList<>();
   public int loopCount=0; 
    
    public static void main(String[] args) {
        int lineCount=0;
        BareBonesInterpreter BBI=new BareBonesInterpreter();
        try{
            Stream<String> all_lines1 = Files.lines(Paths.get("C:\\Users\\Matt\\southampton\\BareCode.txt"));
            List<String> all_lines=all_lines1.collect(Collectors.toList());
            String line;
            String[] parts; 
            int size=all_lines.size();
            while(lineCount<size){
                line = all_lines.get(lineCount);
                parts=line.split(" ");
                if(parts[0].contains("clear")){
                    BBI.clear(parts[1]);
                }
                if(parts[0].contains("incr")){
                    BBI.incr(parts[1]);
                }
                if(parts[0].contains("decr")){
                    BBI.decr(parts[1]);
                }
                if(parts[0].contains("while")){
                    if((BBI.numb(parts[1]+";"))!=0){
                        lineCount=BBI.loop(parts[1],all_lines,BBI, lineCount+1);
                    }
                }
             lineCount++;
            }
            BBI.read();
        }catch(Exception e ){
            System.out.println(e);
        }
    }
    public void read(){
        for (int i = 0; i < data.size(); i++) {
            System.out.print(names.get(i));
            System.out.println(data.get(i));
            
        }
    }
    public int numb(String var){
        return data.get(names.indexOf(var));
    }
    public void clear(String var){
        int index=names.indexOf(var);
        if(index<0){
            names.add(var);
            data.add(0);
        }
        else{
            data.set(index, 0);
        }
    }
    public int get(String var){
        return names.indexOf(var);
    }
    public void incr(String var){
        int index=names.indexOf(var);
        data.set(index, (data.get(index)+1));
    }
    public void decr(String var){
        int index=names.indexOf(var);
        data.set(index, (data.get(index)-1));
        
    }
    public int loop(String var,List<String> all_lines,BareBonesInterpreter BBI,int lineCount){
        if(loopCount==0){
            Loops.add(lineCount);
        }
        lineCount=Loops.get(loopCount);
        
        if(var.contains(";")==false){
            var=var+";";
        }
        String[] parts;
        try{
            String line;
            
            while((line = all_lines.get(lineCount)) != null){
                if(loopCount<0){
                    break;
                }
                 parts=line.split(" ");
                if(line.contains("clear")){
                    BBI.clear(parts[parts.length-1]);
                }
                if(line.contains("incr")){
                    BBI.incr(parts[parts.length-1]);
                }
                if(line.contains("decr")){
                    BBI.decr(parts[parts.length-1]);
                }
                if(line.contains("while")){
                    if((BBI.numb(var))!=0){
                        loopCount++;
                        Loops.add(lineCount);
                        Loops.set(loopCount, lineCount+1);
                        lineCount=BBI.loop(parts[parts.length-4],all_lines,BBI,lineCount+1);
                    }
                    else{
                        while(line.contains("end")==false){//find the part with while in to find correct end
                            lineCount++;
                            line = all_lines.get(lineCount);
                        }
                    }
                }
                if(line.contains("end")){
                    if((data.get(names.indexOf(var)))!=0){
                        BBI.loop(var, all_lines,BBI,lineCount);
                        break;
                    }
                    else{
                        loopCount--;
                        if(loopCount<0){
                            Loops.set(0, lineCount);
                        }
                        Loops.set(loopCount+1, lineCount);
                        break;                     
                    }
                } 
             lineCount++;   
            }
        }catch(Exception e ){
            System.out.println(e);
        }
        return Loops.get(loopCount+1);
        
    }
}
