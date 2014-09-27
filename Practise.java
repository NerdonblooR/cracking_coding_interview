package cracking_coding_interview;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by haotan on 2014-09-23.
 */
public class Practise {
    enum Color { Red, Yellow, Blue};

    private class Point{
        public int x;
        public int y;

        public Point(int x, int y){ this.x = x; this.y = y;}

    }

    public void paintFillNoRecurse(Color[][] screen, int x, int y, Color origColor, Color newColor){
        if (screen == null) return;
        Queue<Point> toPaint = new LinkedList<Point>();
        //BFS traverse
        toPaint.add(new Point(x,y));
        while (!toPaint.isEmpty()){
            //paint the point
            Point curPoint = toPaint.poll();
            if (curPoint.x < 0 || curPoint.y < 0 || curPoint.x > screen[0].length || curPoint.y > screen.length){
                continue;
            }
            if (screen[curPoint.y][curPoint.x] == origColor){
                screen[curPoint.y][curPoint.x] = newColor;
                toPaint.add(new Point(x-1,y));
                toPaint.add(new Point(x+1,y));
                toPaint.add(new Point(x,y-1));
                toPaint.add(new Point(x,y+1));
            }

        }
        return;
    }

    int getWaysForChange(int cents, int deNorm){

        int nextDenorm = 0;
        int ret = 0;
        int remain = cents;

        switch (deNorm){
            case 25:
                nextDenorm = 10;
                break;
            case 10:
                nextDenorm = 5;
                break;
            case 5:
                nextDenorm = 1;
                break;

        }

        if (deNorm == 1){
            return 1;
        }

        while (remain >= deNorm){
            ret += getWaysForChange(remain,nextDenorm);
            remain -= deNorm;
        }

        return ret;

    }

    public boolean twoSum(int[] a, int target){
        //sort the array
        Arrays.sort(a);
        for (int i = 0; i < a.length; i++){
            int toSearch = target - a[i];
            if (binarySearch(a,toSearch,i+1,a.length)){
                return true;
            }
        }
        return false;
    }

    public boolean binarySearch(int[] a, int target, int low, int high){
        int mid = low + (high - low) / 2;

        if (low > high) return false;

        if (a[mid] == target){
            return true;
        }

        if (a[mid] < target) return  binarySearch(a, target, mid+1, high);

        return  binarySearch(a, target, low, mid-1);

    }


}
