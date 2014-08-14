package cracking_coding_interview;

import java.util.*;
public class leetCode {

    public static int longestValidParentheses(String s) {
        Stack<Character> pStack = new Stack <Character>();
        int ans = 0;
        char last;
        for (char c : s.toCharArray()){
            if (c == '('){
                pStack.push(c);
            }else{
                if (! pStack.empty()){
                    last = pStack.peek();
                    if (last == '('){
                         ans += 2;
                        pStack.pop();
                    }else{
                        pStack.push(c);
                    }
                }else{
                    pStack.push(c);
                }
            }
        }
        return ans;
     }

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

    public static int subtract(int subtracted, int subtractor){
        if (subtractor == 0){
            return subtracted;
        }
        int ret = subtracted ^  subtractor;
        int carrybit = (~subtracted & subtractor) << 1;
        int temp = ret;
        while (carrybit != 0){
            ret = ret^carrybit;
            carrybit = (~temp & carrybit) << 1;
            temp = ret;
        }
        return ret;
    }

    public static int candy(int[] ratings) {
        int[] candys = new int[ratings.length];
        if (ratings.length <= 1){
            return ratings.length;
        }
        int count = 1;
        int ret = 0;
        for (int i = 0; i < ratings.length;){
            int j = i;
            if ( j == ratings.length - 1){
                candys[j] = 1;
                if (ratings[j] > ratings[j-1]){
                    candys[j] = candys[j -1] + 1;
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
            candys[i] = count;
            if (i > 0 && ratings[i-1] < ratings[i]){
                if (count <= candys[i-1]) candys[i] = candys[i-1] + 1;
            }
            int peekValue = count - 1;
            for (int k = i+1; k < i + count; k++){
                candys[k] = peekValue--;
            }
            i += count;
            count = 1;
        }

        for (int n : candys){
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

}