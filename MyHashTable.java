package cracking_coding_interview;

/**
 * Created by haotan on 2014-09-19.
 */
public class MyHashTable {

    private static final int capacity = 128;

    private MyHashEntry[] table;

    public MyHashTable(){
        table = new MyHashEntry[capacity];
        for (int i=0; i < capacity; i++){
            table[i] = null;
        }
    }

    private int hashFunction(int inputValue){
        return inputValue % capacity;
    }


    public Integer get(int key){
        int hash = hashFunction(key);
        MyHashEntry walker = table[hash];
        while(walker != null){
            if (walker.getKey() == key){
                return walker.getValue();
            }
            walker = walker.getNext();
        }
        return null;
    }

    public void put(int key, int value){
        int hash = hashFunction(key);
        MyHashEntry newEntry = new MyHashEntry(key,value);
        MyHashEntry walker = table[hash];

        if (walker == null){
            table[hash] = newEntry;
        }else{
            while (walker.getNext() != null){
                walker = walker.getNext();
            }
            walker.append(newEntry);
        }


    }







}
