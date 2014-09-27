package cracking_coding_interview;

/**
 * Created by haotan on 2014-09-25.
 */
public class TrieNode {

    private char key;

    private int value;

    private TrieNode[] children;

    public TrieNode(){

        children = new TrieNode[26];
    }


    public TrieNode(char _key){
        key = _key;
        children = new TrieNode[26];
    }

    public void setValue(int v){
        value = v;
    }

    public int getValue(){
        return value;
    }

    public TrieNode getChild(int key){
        return  children[key];
    }


    public void insert(String s, int val){


        if (s.isEmpty()) return;

        String cp = s.toLowerCase();

        int charpos = cp.charAt(0) - 'a';

        if (children[charpos] == null){
            children[charpos] = new TrieNode(cp.charAt(0));
        }

        if (cp.length() == 1) children[charpos].setValue(val);

        children[charpos].insert(s.substring(1),val);

        return;
    }


    public int getWord(String s){
        String cp = s.toLowerCase();

        TrieNode last = this;

        for (int i = 0; i < s.length(); i++){
            int charpos = cp.charAt(i) - 'a';
            last = last.getChild(charpos);
            if (last == null){
                return -1;//hard-coded error value, should raise exception
            }
        }

        return last.getValue();

    }
}
