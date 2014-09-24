package cracking_coding_interview;

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


}
