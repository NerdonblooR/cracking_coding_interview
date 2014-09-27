package cracking_coding_interview;

import java.util.*;
public class leetCode {

    public static int removeElement(int[] A, int elem) {
        int l = A.length;
        int new_len = l - 1;
        int n = 0;
        while (n <= new_len){
            if (A[n] == elem){
                while (A[new_len] == elem){
                    if (new_len == 0){
                        return 0;
                    }
                    new_len--;
                }
                if (new_len > n){
                    A[n] = A[new_len--];
                }
            }
            n++;
        }
        return new_len + 1;
    }

    public static int subtract(int subtracted, int subTractor){
        if (subTractor == 0){
            return subtracted;
        }
        int ret = subtracted ^  subTractor;
        int carryBit = (~subtracted & subTractor) << 1;
        int temp = ret;
        while (carryBit != 0){
            ret = ret^carryBit;
            carryBit = (~temp & carryBit) << 1;
            temp = ret;
        }
        return ret;
    }

    public static int candy(int[] ratings) {
        int[] candies = new int[ratings.length];
        if (ratings.length <= 1){
            return ratings.length;
        }
        int count = 1;
        int ret = 0;
        for (int i = 0; i < ratings.length;){
            int j = i;
            if ( j == ratings.length - 1){
                candies[j] = 1;
                if (ratings[j] > ratings[j-1]){
                    candies[j] = candies[j -1] + 1;
                }
                break;
            }
            while (ratings[j] > ratings[j+1]){
                if (ratings[j] > ratings[j+1]){
                    j++;
                    count++;
                    if ( j == ratings.length - 1){
                        break;
                    }
                }
            }
            candies[i] = count;
            if (i > 0 && ratings[i-1] < ratings[i]){
                if (count <= candies[i-1]) candies[i] = candies[i-1] + 1;
            }
            int peekValue = count - 1;
            for (int k = i+1; k < i + count; k++){
                candies[k] = peekValue--;
            }
            i += count;
            count = 1;
        }

        for (int n : candies){
            ret += n;
        }
        return ret;
    }

    public boolean isInterleave(String s1, String s2, String s3) {
        int l1 = s1.length();
        int l2 = s2.length();
        int l3 = s3.length();
        boolean[][] res = new boolean[l1+1][l2+1];
        if (l1 + l2 != l3) return false;
        for (int i=0; i <= l2; i++) res[0][i] = s2.substring(0,i).equals(s3.substring(0,i));
        for (int i=0; i <= l1; i++) res[i][0] = s1.substring(0,i).equals(s3.substring(0,i));
        for (int i=1; i <= l1; i++ ){
            for (int j=1; j <= l2; j++){
                res[i][j] = (res[i-1][j] && (s1.charAt(i-1) == s3.charAt(i+j-1))) ||
                        (res[i][j-1] && (s2.charAt(j-1) == s3.charAt(i+j-1)));

            }
        }
        return res[l1][l2];
    }

    public List<Integer> getRow(int rowIndex) {
        Integer[] tmp = new Integer[rowIndex+1];
        Integer[] ret = new Integer[rowIndex+1];
        ret[0] = 1;
        for (int i = 1;i < rowIndex + 1; i++){
            for (int j=0; j <= rowIndex; j++){
                tmp[j] = ret[j];
            }
            ret[0] = 1;
            for (int j=1; j < rowIndex; j++){
                ret[j] = tmp[j-1] + tmp[j];
            }
            ret[rowIndex] = 1;
        }

        return new ArrayList<Integer>(Arrays.asList(ret));

    }

    public static ListNode insertionSortList(ListNode head) {

        if (head == null) return head;
        ListNode walker = head.next;
        ListNode sortedHead = head;
        ListNode sortedTail = head;
        ListNode sortWalker;
        ListNode prev;


        while (walker != null){
            sortWalker = sortedHead;
            prev = sortedHead;
            if (walker.val <= sortedHead.val){
                sortedTail.next = walker.next;
                walker.next = sortedHead;
                sortedHead = walker;
            }else{
                while(walker.val >= sortWalker.val && sortWalker != sortedTail.next){
                    prev = sortWalker;
                    sortWalker = sortWalker.next;
                }
                if (sortWalker == sortedTail.next){
                    sortedTail = walker;
                }else{
                    prev.next = walker;
                    sortedTail.next = walker.next;
                    walker.next = sortWalker;

                }
            }
            walker = sortedTail.next;
        }
        return sortedHead;
    }


    public static void reorderList(ListNode head) {
        ListNode walker1 = head;
        ListNode walker2 = head;
        if (head == null) return;
        //walk to the end of the list
        while(walker1 != null){
            walker1 = walker1.next;
            if (walker1 != null){
                walker1 = walker1.next;
            }else{
                break;
            }
            walker2 = walker2.next;
        }
        walker1 = head;
        ListNode temp = walker2.next;
        walker2.next = null;
        walker2 = temp;

        //reverse the second half
        ListNode n1 = walker2;
        ListNode curHead = walker2;
        ListNode n2;
        while (walker2 !=null){
            if (walker2.next == null){
                break;
            }
            n1 = walker2.next;
            n2 = n1.next;
            walker2.next = n2;
            n1.next = curHead;
            curHead = n1;
        }
       walker2 = curHead;

        while (walker2 != null){
            ListNode temp1 = walker1.next;
            ListNode temp2 = walker2.next;
            walker1.next = walker2;
            walker2.next = temp1;
            walker1 = temp1;
            walker2 = temp2;
        }
        return;
    }


    public static ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        ListNode suc = null;

        while (head != null){
            for (suc = head.next; suc!=null&&suc.val == head.val; suc = suc.next);
            if (head.next == suc){
                cur.next = head;
                cur = head;
                cur.next = null;
            }
            head = suc;
        }

        return dummy.next;
    }

    public static String longestPalindrome(String s) {
        int length = 0; int max = 0;
        String longest = "";
        for (int i = 0; i < s.length(); i++){
            int k = i; int before = 0;
            length = 1;
            for (int j=1; i-j >= 0 && k + j < s.length(); j++){
                if (s.charAt(i-j) == s.charAt(k+j)){
                    before++;
                    length += 2;
                }else{
                    //break if palidrome ends
                    break;
                }
            }
            max = Math.max(length, max);
            if (max == length) longest = s.substring(i - before, k + before + 1);
            //another case;
            if (i + 1 != s.length() && s.charAt(i) == s.charAt(i+1)){
                k = i + 1;
                length = 2;
                before = 0;
                for (int j=1; i-j >= 0 && k + j < s.length(); j++){
                    if (s.charAt(i-j) == s.charAt(k+j)){
                        before++;
                        length += 2;
                    }else{
                        break;
                    }
                }
                max = Math.max(length, max);
                if (max == length) longest = s.substring(i - before, k + before + 1);
            }
        }
        return longest;
    }

    public static RandomListNode copyRandomList(RandomListNode head) {
        if (head == null){
            return null;
        }

        RandomListNode walker = head;

        while (walker != null){
            RandomListNode next = walker.next;
            walker.next = new RandomListNode(walker.label);
            walker.next.next = next;
            walker = next;
        }

        walker = head;

        while (walker != null){
            walker.next.random = (walker.random == null)? null : walker.random.next;
            walker = walker.next.next;
        }

        RandomListNode ret = head.next;
        RandomListNode walker2 = ret;
        walker = head;

        while(true){
            walker.next = walker.next.next;
            if (walker.next ==null){
                break;
            }
            walker2.next = walker2.next.next;
            walker = walker.next;
            walker2 = walker2.next;
        }

        return ret;

    }

}
