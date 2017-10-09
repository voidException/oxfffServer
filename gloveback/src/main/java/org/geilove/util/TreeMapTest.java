package org.geilove.util;

/**
 * Created by aihaitao on 29/4/2017.
 */

import java.util.Iterator;
        import java.util.Map;
        import java.util.Map.Entry;
        import java.util.TreeMap;

public class TreeMapTest {

    public static void main(String[] args){
        TreeMap<String,String> map=new TreeMap<String,String>();
        map.put("5","qwe");
        map.put("2", "asd");
        map.put("3", "zxcc");
        Iterator iterator=map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<String, String>  entry=(Entry<String, String>) iterator.next();
            System.out.println("key:"+entry.getKey()+""+"value:"+entry.getValue());

        }
        for(Map.Entry<String, String> entry: map.entrySet()){
            System.out.println("key:"+entry.getKey()+" "+"value:"+entry.getValue());
        }
    }

}