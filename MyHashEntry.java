package cracking_coding_interview;

/**
 * Created by haotan on 2014-09-19.
 */
public class MyHashEntry {

    private int key;
    private int value;
    private MyHashEntry next = null;

    public MyHashEntry(int _key, int _value){
        key = _key;
        value = _value;
    }

    public int getKey(){
        return key;
    }

    public int getValue(){
        return value;
    }

    public MyHashEntry getNext(){
        return next;
    }

    public void append(MyHashEntry _next){
        next = _next;
    }


}
