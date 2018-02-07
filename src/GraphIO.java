/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fvs_bf;

import static java.util.Arrays.*;

import java.io.*;
import java.util.*;

/**
 *
 * @author sheshang
 */
public class GraphIO {
    
    Map<String, Integer> ids = new TreeMap<>();
    ArrayList<String> names = new ArrayList<>();
    ArrayList<int[]> es = new ArrayList<>();
    
    public void read(String fname) throws FileNotFoundException {
        
        //File fp = new File("testcases/"+fname);
        File fp = new File(fname);
        Scanner sc = new Scanner(fp);
       
        while(sc.hasNext()){
                    
            String u = sc.next();
            if(u.startsWith("#")){
                sc.nextLine();
                continue;
            }
            String v = sc.next();
                es.add(new int[]{getID(ids, names, u), getID(ids, names, v)});
                    
        }
        
    }
    
    int getID(Map<String, Integer> ids, ArrayList<String> names, String s) {
	Integer id = ids.get(s);
	if (id == null) {
            id = names.size();
            ids.put(s, id);
            names.add(s);
	}
	return id;
    }
}
