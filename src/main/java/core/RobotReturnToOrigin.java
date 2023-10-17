package core;

public class RobotReturnToOrigin {

    public boolean judgeCircle (String moves) {
        int x = 0;
        int y = 0;
        for (char c : moves.toCharArray()) {
            if(c == 'U') {
                x++;
            } else if (c == 'D'){
                x--;
            } else if (c == 'L'){
                y--;
            } else if (c == 'R') {
                y++;
            }
        }
        if (x==0 && y==0) {
            return true;
        }
        return false;
    }

    public static void main (String[] args) {
        RobotReturnToOrigin robotReturnToOrigin = new RobotReturnToOrigin();
        System.out.println("Output is ::-->"+ robotReturnToOrigin.judgeCircle("RRULLDUD"));
    }

}
