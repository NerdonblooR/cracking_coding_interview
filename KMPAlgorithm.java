package cracking_coding_interview;

/**
 * Created by haotan on 2014-09-09.
 *
 * An KMP algorithm implementation from Introduction to Algorithm
 */

public class KMPAlgorithm {

    /**
     *  backtrack[i] means:
     *  1. if  matching failed at p[i+1], how many char can still be considered as matched
     *  2. length of longest suffix of pattern[0:i] which is also prefix of pattern
     * */
    public static int[] computeBackTrackTable(char[] pattern){
        int pl = pattern.length;
        int[] ret = new int[pl];
        ret[0] = 0;
        int k = 0;
        for (int i = 1; i < pl;i++){
            // if matched keep going
            if (pattern[k] == pattern[i]){
                k += 1;
            }else{
                //if not matched, back track
                while (k > 0 && pattern[k] != pattern[i]){
                    k = ret[k-1];
                }
                //increase k if pattern[i] is matchedsd
                if (pattern[k] == pattern[i]) k++;
            }
            ret[i] = k;
        }

        return ret;
    }

    public static int[] computePrefix(char[] pattern){
        int pl = pattern.length;
        int[] ret = new int[pl];
        ret[0] = 0;
        int k=0;
        for (int i= 1; i < pl; i++){
            if (pattern[k] == pattern[i]){
                k++;
            }else{
                while (k > 0 && pattern[k] != pattern[i]){
                    k = ret[k-1];
                }
                //increase k if pattern[i] is matchedsd
                if (pattern[k] == pattern[i]) k++;

            }

            ret[i] = k;
        }
        return ret;
    }


    public static int kmpSearch(char[] pattern, char[] base){
        int pl = pattern.length;
        int bl = base.length;
        int [] backtrack = computeBackTrackTable(pattern);
        int q = 0;
        for (int i= 0; i < bl; i++){
            if (pattern[q] == base[i]){
                q++;
            }else{
                while (q > 0 && pattern[q] != base[i]){
                    q = backtrack[q-1];
                }
                if (pattern[q] == base[i]) q++;

            }
            //q chars has been matched return
            if (q == pl){
                return i - q + 1;
            }
        }
        return -1;
    }

}
