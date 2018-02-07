/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fvs_bf;

import java.io.*;
import java.util.*;

/**
 *
 * @author sheshang
 */
public class GraphModification {

    static int[][] unionFind;
    
    GraphModification(int size){
        unionFind = new int[size][2];
    }
    
     
    
    public boolean recursiveSelectSubset(boolean[] subset, int n, int k, boolean isminimum, GraphIO io) throws Exception {
        
        if(!isminimum){
            
            try{
            
                if(k == n){
                    for(int i = 0; i < n; i++){
                        subset[i] = true;               
                    }
                    //printsub(subset, io);
                    isminimum = isAcyclic(subset, io);
                    
                }
        
                if(k == 0){
                    for(int i = 0; i < n; i++){
                        subset[i] = false;
                    }
                    //printsub(subset, io);
                    isminimum = isAcyclic(subset, io);
                    
                }
        
                if(k > 0 && k < n){
                    subset[n-1] = true;
                    isminimum = recursiveSelectSubset(subset, n-1, k-1, isminimum, io);
                    if(!isminimum){
                        subset[n-1] = false;
                        isminimum = recursiveSelectSubset(subset, n-1, k, isminimum, io);
                    }
                        
                }         
            }
        
            catch (Throwable e){
                System.err.println("Error = " + e.toString());
                e.printStackTrace();
            }
            
        }
        
        return isminimum;
        
    }
    
    static boolean isAcyclic(boolean[] subset, GraphIO io) throws Exception {
        
        boolean isacyclic = true;
        ArrayList<int[]> reducedes = new ArrayList<>();
        
        try{         
           
            for(int[] edge : io.es){
                if(!subset[edge[0]] && !subset[edge[1]]){
                    reducedes.add(edge);          
                }
            }
        
            //calling union-find function here
            resetUnionFind(unionFind, io.names.size());
            /*
            for(int i = 0; i < io.names.size(); i++){
                System.out.println(unionFind[i][0]+" "+unionFind[i][1]);
            }
            System.out.println("yes");*/
            
            for(int[] e : reducedes){
                if(isacyclic){
                    int x = find(unionFind, e[0]);
                    int y = find(unionFind, e[1]);
            
                    if(x == y){
                        isacyclic = false;
                    }
            
                    else{
                        union(unionFind, x, y);
                    }
                }
                
            }
        }
        catch(Throwable e){
            System.err.println("Error = " + e.toString());
            e.printStackTrace();
        }
        
        
        
        return isacyclic;
    }
    
    static void printsub(boolean[] subset, GraphIO io){
        String[] vertex = io.names.toArray(new String[io.names.size()]);
        System.out.print("{");
        for(int i = 0; i < subset.length; i++){
            if(subset[i]){
                //printing vertex id
                //System.out.print(i+1);
                //if you want vertex name
                System.out.print(vertex[i]);
            }
        }
        System.out.println("}");
    }
    
    static void resetUnionFind(int[][] unionFind, int n){
        
        //0th index = parent
        //1st index = rank
        //unionFind = new int[n][2];
        
        for(int i = 0; i < n; i++){
            unionFind[i][0] = i;
            unionFind[i][1] = 0;            
        }       
        
    }
    
    static int find(int[][] unionFind, int i){
        if(unionFind[i][0] != i){
            unionFind[i][0] = find(unionFind, unionFind[i][0]);
        }
        
        return unionFind[i][0];
    }
    
    static void union(int[][] unionFind, int x, int y){
        int xroot = find(unionFind, x);
        int yroot = find(unionFind, y);
        
        if(unionFind[xroot][1] < unionFind[yroot][1]){
            unionFind[xroot][0] = yroot;
        }
        else if(unionFind[yroot][1] < unionFind[xroot][1]){
            unionFind[yroot][0] = xroot;
        }
        else{
            unionFind[yroot][0] = xroot;
            unionFind[xroot][1] += 1;
        }
          
    }
}
