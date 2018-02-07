/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fvs_bf;

import java.io.*;
import java.util.concurrent.TimeUnit;
/**
 *
 * @author sheshang
 */
public class FVS_BF {

    /**
     */
    public static boolean isminimum = false;
    
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        String fname = args[0];
        //System.out.println(fname);
        
        try{
            GraphIO io = new GraphIO();
            io.read(fname);
            
            int size = io.names.size();
            boolean[] subset = new boolean[size];
            
            GraphModification g = new GraphModification(size);
            
            long lStartTime = System.nanoTime();
            
            for(int i =  0; i <= size && !isminimum; i++){
                isminimum = g.recursiveSelectSubset(subset, size, i, isminimum, io);
            }
            
            long lEndTime = System.nanoTime();
            
            long executionTime = lEndTime - lStartTime;
            double executionTimeSecond = (double)executionTime/1000000000.0;
            //System.out.println(executionTime/1000000);
            
            //GraphModification.printsub(subset, io);
            //printing minimum feedback vertex set
            String[] vertex = io.names.toArray(new String[io.names.size()]);
            String fvset = "{";
            int fvsetSize = 0;
            for(int i = 0; i< size; i++){
                
                if(subset[i]){
                    fvset += vertex[i].toString() +" ";
                    fvsetSize += 1;
                }
                
            }
            fvset += "}";
            
            File outfp = new File("outputFile.txt");
            outfp.createNewFile();
            BufferedWriter out = new BufferedWriter(new FileWriter(outfp, true));
            fname = fname.split("/")[1];
            out.write(fname+":"+size+":"+fvset+":"+fvsetSize+":"+executionTimeSecond+"\r\n");
            out.close();
            
            
        }
        catch (Throwable e){
            System.err.println("Error = " + e.toString());
            e.printStackTrace();
        }
    }
    
}
