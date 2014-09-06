package cracking_coding_interview;

import java.util.*;

/**
 * Created by haotan on 2014-08-18.
 */
public class cc150 {

    enum Color { Red, Yellow, Blue};
    static int count = 1;
    /****************************CHAPTER1**********************************/

    public static boolean containAllUnique(String str){
        if (str.length() > 256) return false;
        boolean[] charMap = new boolean[256];
        for (int i = 0; i < str.length(); i++){
            if (charMap[str.charAt(i)]) {
                return false;
            }
            charMap[(int)str.charAt(i)] = true;
        }
        return true;
    }

    // one way is to sort the array to check if tow arrays are equal
    public static boolean isPermutaion(String str1, String str2){
        if (str1.length() != str2.length()) return false;
        int[] charCount = new int[256];
        for (int i=0; i < str1.length(); i++){
            charCount[str1.charAt(i)]++;

        }
        for (int j=0; j < 256; j++){
            charCount[str2.charAt(j)]--;
            if (charCount[str2.charAt(j)] < 0){
                return false;
            }
        }
        return true;

    }

    public static void replaceSpace(char[] s, int length){
        //scan the string twice
        //first scan: count how many spaces are there
        //second scan: modify string in reverse order
        //if in original order, we cannot modify the char array
        //in place, as replacing space with "%20" will overwrite the part
        //we have not processed yet.
        for (int i = 0; i < length; i++){
            int spaceNum = 0;
            int newlen = length;
            if (s[i] == ' '){
                spaceNum++;
            }
            newlen += spaceNum * 2;
            s[newlen] = '\0';
            for(int j = length -1; i >= 0; i--){
               if (s[j] == ' '){
                   s[newlen - 1] = '0';
                   s[newlen - 2] = '2';
                   s[newlen - 3] = '%';
                   newlen -= 3;
               }else{
                   s[newlen -1] = s[j];
                   newlen--;
               }
            }
        }
    }

    public static String compresString(String s){
        if ( s == null) return s;
        int count = 1;
        String ret = "";
        char last = s.charAt(0);
        for (int i = 1; i < s.length(); i++){
            if (s.charAt(i) == last){
                count++;
            }else{
                //concatenation is not efficient
                ret += String.valueOf(last) + String.valueOf(count);
                last = s.charAt(i);
                count = 1;
            }

        }
        return  ret += String.valueOf(last) + String.valueOf(count);

    }

    public static int calculateCompressSize(String s){
        int count = 1;
        int size = 0;
        char last = s.charAt(0);
        for (int i = 1; i < s.length(); i++){
            if (s.charAt(i) == last){
                count++;
            }else{
                last = s.charAt(i);
                size += 1 + String.valueOf(count).length();
                count = 1;
            }
        }
        return size + 1 + String.valueOf(count).length();

    }


    public void setArray(char[] array, int index, char last, int count){

    }

    public void rotateMatrix(int[][] m){
        int rowNum = m.length;
        for (int layer = 0; layer < rowNum/2; ++layer){
            //set up the first and last column to perform rotation
            int first = layer;
            int last = rowNum - 1 - layer;
            for (int i=first; i < last; i++){
                // distance from the starting point
                int offset = first - i;
                int top = m[layer][i];

                //left -> top
                m[first][i] = m[last - offset][first];
                //bottom -> left
                m[last - offset][first] = m[last][last - offset];
                //right -> bottom
                m[last][last - offset] = m[i][last];
                //top -> right
                m[i][last] = top;

            }
        }
    }

    public static void processMatrix(int[][] m){
        int rowNum = m.length;
        int colNum = m[0].length;

        //can replace the hashset to be a boolean array
        //which can be further optimized if using a bit vector

        HashSet<Integer> colNeedUpdate = new HashSet<Integer>();
        HashSet<Integer> rowNeedUpdate = new HashSet<Integer>();

        for (int i=0; i < rowNum; i++){
            for (int j=0; j < colNum; j++){
                if (m[i][j] == 0){
                    colNeedUpdate.add(j);
                    rowNeedUpdate.add(i);
                }
            }

        }

        for (Integer col: colNeedUpdate){
            for (int r=0; r < rowNum; r++){
                m[r][col] = 0;
            }
        }

        for (Integer row: rowNeedUpdate){
            for (int c=0; c < colNum; c++){
                m[row][c] = 0;
            }
        }

    }

    /****************************CHAPTER2**********************************/

    public static void removeDuplicate(ListNode head){
        if (head == null) return;
        ListNode current = head;
        while(current != null){
           ListNode runner = current;
           while (runner.next != null){
               if (runner.next.val == current.val){
                   runner.next = runner.next.next;
               }else{
                   runner = runner.next;
               }
           }
           current = current.next;

        }
        return;
    }

    public static ListNode kthToLastElement(ListNode head, int k){
        //waht if the
        ListNode walker1 = head;
        ListNode walker2 = head;
        for (int i=0; i < k-1; i++) {
            if (walker2 == null){
                return null;
            }
            walker2 = walker2.next;
        }
        if (walker2 == null) return null;
        while(walker2.next != null){
            walker1 = walker1.next;
            walker2 = walker2.next;
        }
        return walker1;
    }

    public static void deleteNode (ListNode middleNode){
        if (middleNode.next == null) return;
        middleNode.val = middleNode.next.val;
        middleNode.next = middleNode.next.next;
    }


    public static ListNode partitionLinkedList(ListNode head, int x){
        if (head == null){
            return null;
        }
        //make ListNode as head if its value is smaller than x
        ListNode curHead = head;
        ListNode walker = head;
        while (walker.next != null){
            if (walker.next.val < x){
                ListNode temp = walker.next;
                walker.next = walker.next.next;
                temp.next = curHead;
                curHead = temp;

            }else{
                walker = walker.next;
            }
        }
        return curHead;
    }

    public static int transferToNum(ListNode n1){
        int ret = 0;
        int digit = 1;
        ListNode walker = n1;
        while(walker != null){
            ret += digit* walker.val;
            walker = walker.next;
            digit *= 10;
        }
        return ret;

    }

    public static ListNode transferToList(int num){
        ListNode head = new ListNode(num % 10);
        ListNode newNode;
        num = num / 10;
        while(num > 10){
            newNode = new ListNode(num % 10);
            head.next = newNode;
            head = head.next;
            num /= 10;
        }
        return head;
    }

    public static ListNode linkedListSum(ListNode n1, ListNode n2){
        return transferToList(transferToNum(n1) + transferToNum(n2));
    }

    public static boolean isPalidrome(ListNode n1){

    }











    /****************************CHAPTER9**********************************/

    public static int countStep(int n) {
        int[] stepArray = new int[n+1];
        int ret;
        stepArray[0] = 1;
        for (int i = 1; i < n+1; i++) {
            ret = 0;
            for (int j = 1; j<4; j++){
                if (i - j >= 0){
                    ret += stepArray[i-j];
                }
            }
            stepArray[i] = ret;
        }
        return stepArray[n];
    }

    public static int magicIndex(int[] intArray, int start, int end){

        int mid = (start + end)/2;
        int midValue = intArray[mid];

        if (mid == midValue){
            return mid;
        }
        //search left
        int leftIndex = Math.min(midValue,mid - 1);
        int rightIndex = Math.max(midValue, mid + 1);

        int left = magicIndex(intArray, start, leftIndex);

        if (left > -1){
            return left;
        }

        //search right
        int right = magicIndex(intArray, rightIndex, end);

        if (right > -1){
            return right;
        }



        //if there's no magic index return -1
        return -1;

    }

    public static int magicIndex(int[] intArray){
        return magicIndex(intArray, 0, intArray.length);
    }


    public static List<String> permuteString(String s){

        List<String> pList = new ArrayList<String>();


        if (s == null){
            return null;
        }


        if (s.length() <= 1){
            pList.add(s);
            return pList;
        }

        String first = s.substring(0,1);
        String res = s.substring(1);

        List<String> pListRest = permuteString(res);

        for (int i = 0; i < pListRest.size(); i++){
            String p = pListRest.get(i);
            for (int j = 0; j <= p.length(); j++){
                pList.add(insertAt(p, first, j));
            }

        }

        return pList;
    }

    private static String insertAt(String s, String insert, int i){
        String p1 = s.substring(0,i);
        String p2 = s.substring(i);
        return p1 + insert + p2;
    }

    public static List<String> getValidParen(int n){
        //Using set
        List<String> p1 = new ArrayList<String>();

        if (n == 0){
            return p1;
        }

        if (n == 1){
            p1.add("()");
            return p1;
        }

        List<String> p2 = getValidParen(n - 1);
        for (String vp : p2){
            for (int i = 0; i < vp.length(); i++){
                if (vp.substring(i,i+1).equals("(")){
                    p1.add(insertAt(vp, "()", i+1));
                }
            }
            p1.add(vp + "()");
        }
        return p1;
    }

    public static void paintFill(Color[][] screen, int x, int y, Color origColor, Color newColor){
        if (x < 0 || y < 0){
            return;
        }

        if (screen[y][x] == origColor){
            screen[y][x] = newColor;
            paintFill(screen, x+1, y, origColor, newColor);
            paintFill(screen, x-1, y, origColor, newColor);
            paintFill(screen, x, y-1, origColor, newColor);
            paintFill(screen, x, y-1, origColor, newColor);
        }

        return;

    }

    public static int getWaysForChange(int cents, int deNorm){
        int nextDeNorm = 0;
        int ret = 0;
        int itNum;

        switch (deNorm){
            case 25:
                nextDeNorm = 10;
                break;
            case 10:
                nextDeNorm = 5;
                break;
            case 5:
                nextDeNorm = 1;
                break;
        }

        if (deNorm == 1){
            return 1;
        }else{
            itNum = cents / deNorm + 1;
            for(int i=0; i < itNum; i++){
                ret += getWaysForChange(cents - i*deNorm, nextDeNorm);
            }

        }
        return ret;

    }

    public static void printBoard(int[][] board){
        for (int i = 0; i < board.length; i++){
            String row = "";
            for (int j = 0; j < board[i].length; j++){
                if (board[i][j] == 2){
                    row += "Q ";
                }else{
                    row += "* ";
                }

            }
            System.out.println(row);
        }
        System.out.println("========================" + count);
        count++;
    }

    public static void eightQueenProblem(int[][] board, int row){
        for (int i = 0; i < 8; i++){
            if (board[row][i] == 0){
                if (row == 7){
                    printBoard(markBoard(board,i,row));
                }else{
                    eightQueenProblem(markBoard(board,i,row),row+1);
                }

            }
        }
        return;

    }

    public static int[][] markBoard(int[][]board, int x, int y){
        int [][] retBoard = new int[board.length][board[0].length];
        for (int i=0;i<board.length;i++){
            for(int j=0;j<board[i].length;j++){
                retBoard[i][j] = board[i][j];
            }
        }

        for (int i = 0; i < 8; i++){
            retBoard[i][x] = 1;
            retBoard[y][i] = 1;
        }

        //top right corner
        int dl = Math.min(x+1,y+1);
        for (int i=1; i < dl; i++){
            retBoard[y-i][x-i] = 1;
        }
        //top left corner
        dl = Math.min(8-x,y+1);
        for (int i=1; i < dl; i++){
            retBoard[y-i][x+i] = 1;
        }

        //top left corner
        dl = Math.min(x+1,8-y);
        for (int i=1; i < dl; i++){
            retBoard[y+i][x-i] = 1;
        }

        //bot right corner
        dl = Math.min(8-x,8-y);
        for (int i=1; i < dl; i++){
            retBoard[y+i][x+i] = 1;
        }
        retBoard[y][x] = 2;
        return retBoard;

    }

    public static void eightQueenProblem(){
        int[][] board = new int[8][8];
        eightQueenProblem(board,0);
    }

    /****************************CHAPTER11**********************************/

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

    /****************************CHAPTER17**********************************/

    public static int tailingZeros(int n){
        int num5 = 0;
        for (int i = n; i > 0; i--){
            int temp = i;
            for (;temp > 0; ){
                if (temp % 5 == 0) num5++;
                temp /= 5;
            }

        }

        return num5;
    }

    public static int sign (int n){
        //convert 0 to 1; 1 to 0;
        return (1^((n >> 31) & 0x1));
    }

    public static int maxNum(int a, int b){
        int sa = sign(a);
        int sb = sign(b);
        int sc = sign(a - b);


        int not_equal = sa^sb;

        // if a b have different signs, we want k to be same as sa
        int k = not_equal * sa + (1^not_equal) * sc;
        int q = 1^k;



        return a * k + b * q;
    }

    private static int findLeftEnd(int[] intArray){
        for (int i = 0; i < intArray.length - 1; i++){
            if (intArray[i] > intArray[i+1]){
                return i;
            }
        }
        return intArray.length - 1;
    }

    private static int findRightStart(int[] intArray){
        for (int i = intArray.length - 1; i > 0; i++) {
            if (intArray[i-1] > intArray[i]){
                return i;
            }
        }
        return 0;
    }

    private static int shrinkLeft(int start, int minIndex ,int[] intArray){
        int minValue = intArray[minIndex];
        while (intArray[start] > minValue && start > 0){
            start--;
        }
        return start;
    }

    private static int shrinkRight(int start, int maxIndex ,int[] intArray){
        int maxValue = intArray[maxIndex];
        while (intArray[start] < maxValue && start < intArray.length - 1){
            start++;
        }
        return start;
    }


    public static void unSortedPart(int[] intArray){
        //int[] ret = new int[2];

        //naieve approach: sort the whole array and compare the sorted array
        // with the original array, first index where value is different from orignal
        //would be ret[0], the first index i after ret[0] such that intArray[i] is same as
        //original value would be ret[i];

        int leftEnd = findLeftEnd(intArray);

        if (leftEnd == intArray.length - 1) return;

        int rightStart = findRightStart(intArray);

        int minIndex = leftEnd + 1;
        int maxIndex = rightStart - 1;

        for (int i = leftEnd; i <= rightStart; i++){
            if (intArray[i] < intArray[minIndex]) minIndex = 1;
            if (intArray[i] > intArray[maxIndex]) maxIndex = 1;
        }

        int start = shrinkLeft(leftEnd, minIndex, intArray);




        //return ret;
    }


    public static int maxContiguousSequence(int[] array){
        //maximum sum of the contiguous sequence end at index i
        int[] maxSum = new int[array.length];
        maxSum[0] = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++){
            maxSum[i] = Math.max(maxSum[i-1] + array[i], array[i]);
            if (max < maxSum[i]) max = maxSum[i];

        }

        return max;

    }


    public String convertToEnglish(int n){
        return "";
    }



    public static TreeNode convert(TreeNode root){
        TreeNode head = root;
        if (root == null) return root;
        if (root.left != null) head = convert(root.left);
        if (head != root){
            //traverse to right most node
        }
        return head;
    }






    /**************************Chapter18************************************/



    public static void shuffleArray(int[] cards){
        //after every iteration, [0..i] is sorted
        int temp, index;
        for (int i = 0; i < cards.length; i++){
            index = (int) (Math.random() * (cards.length - i)) + i;
            temp = cards[i];
            cards[i] = cards[index];
            cards[index] = temp;
        }
    }

    public static int[] randomGenerate(int m, int[] pool){
        int temp, index;
        int[] ret = new int[m];
        for (int i=0; i < m; i++){
            index = (int) (Math.random() * (pool.length - i)) + i;
            temp = pool[i];
            pool[i] = pool[index];
            pool[index] = temp;
            ret[i] = pool[i];
        }
        return ret;

    }


    public static int countTwo(int upperboud){
        int ret = 0;
        int scale = 1;
        int prevRem = 0;
        int mul, rem;
        while (upperboud > 0){
            mul = upperboud / 10;
            rem = upperboud % 10;
            ret += scale * (mul + (rem > 2 ? 1 : 0));
            if (rem == 2){
                ret += scale / 10 * (prevRem + 1);
            }
            upperboud = mul;
            scale *= 10;
            prevRem = rem;
        }
        return ret;
    }




}
