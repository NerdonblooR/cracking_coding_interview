package cracking_coding_interview;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.LinkedList;

/**
 * Created by haotan on 2014-07-21.
 */
public class CharpterEleven {

    public static int[] mergeArrays(int[] a, int[]b, int aSize, int bSize){
        int newSize = aSize + bSize;
        int cur_index = newSize - 1;
        while(aSize > 0 && bSize > 0){
            if (b[bSize - 1] >= a[aSize -1]){
                a[cur_index] = b[bSize -1];
                bSize--;
                cur_index--;
            }
            else{
                a[cur_index] = a[aSize -1];
                aSize--;
                cur_index--;
            }

        }
        for(int i = 0; i < bSize; i++){
            a[i] = b[i];
        }

        return a;
    }

    public static String getSignature(String s){
        char[] cArray = s.toCharArray();
        Arrays.sort(cArray);
        return new String(cArray);
    }


    public static void sortString(String[] sArray){
        Hashtable<String,LinkedList<String>> hash =
                new Hashtable<String,LinkedList<String>>();
        //construct hashtable
        for(String s : sArray){
            String key = getSignature(s);
            if(!hash.containsKey(key)){
                hash.put(key,new LinkedList<String>());
            }
            System.out.println(key);
            hash.get(key).push(s);
        }
        //some comment
        int idx = 0;
        for (String k: hash.keySet()){
            for(String s:hash.get(k)){
                sArray[idx] = s;
                idx++;
            }
        }

    }

}
