/**
 * Created by gu on 2016/3/17.
 */
public enum Move {
    downl, downr, upl, upr, frontl, frontr, rightl, rightr, backl, backr, leftl, leftr;

    @Override
    public String toString() {
        switch (this) {
            case downl:
                return "down counterclockwise";
            case downr:
                return "down clockwise";
            case upl:
                return "up counterclockwise";
            case upr:
                return "up clockwise";
            case frontl:
                return "front counterclockwise";
            case frontr:
                return "front clockwise";
            case rightl:
                return "right counterclockwise";
            case rightr:
                return "right clockwise";
            case backl:
                return "back counterclockwise";
            case backr:
                return "back clockwise";
            case leftl:
                return "left counterclockwise";
            case leftr:
                return "left clockwise";
            default:
                return "error move";
        }
    }

    public static Move inverseMove(Move move) {
        switch (move) {
            case downl:
                return downr;
            case downr:
                return downl;
            case upl:
                return upr;
            case upr:
                return upl;
            case frontl:
                return frontr;
            case frontr:
                return frontl;
            case rightl:
                return rightr;
            case rightr:
                return rightl;
            case backl:
                return backr;
            case backr:
                return backl;
            case leftl:
                return leftr;
            case leftr:
                return leftl;
            default:
                return null;
        }
    }
}
