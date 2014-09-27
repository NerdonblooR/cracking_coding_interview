package cracking_coding_interview;

/**
 * Created by haotan on 2014-09-25.
 */
public class Trie {
    private TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public void add(String s, int val){
        root.insert(s,val);
    }

    public int get(String s){
        return root.getWord(s);
    }
}
