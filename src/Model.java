import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by gu on 2016/3/16.
 */
public class Model {
    public HashMap<FaceSide, Face> faces;

    public Move lastMove;

    Model() {
        faces = new HashMap<>();
        for (FaceSide faceSide : FaceSide.values()) {
            faces.put(faceSide, new Face(faceSide));
        }
    }

    @Override
    public Object clone() {
        Model newModel = new Model();

        for (FaceSide faceSide : FaceSide.values()) {
            newModel.faces.put(faceSide, (Face) this.faces.get(faceSide).clone());
        }

        newModel.lastMove = this.lastMove;

        return newModel;
    }

    @Override
    public String toString() {
        String res = "";

        for (FaceSide faceSide : FaceSide.values()) {
            res += "Face " + faceSide.toString() + ":\n";
            res += faces.get(faceSide).toString();
        }

        res += "\n";
        return res;
    }

    @Override
    public int hashCode() {
        int res = 0;
        for (FaceSide faceSide : FaceSide.values()) {
            res += faces.get(faceSide).hashCode();
        }
        return res;
    }

    @Override
    public boolean equals(Object object) {
        if (getClass() != object.getClass()) return false;

        Model other = (Model) object;

        for (FaceSide faceSide : FaceSide.values()) {
            if (!other.faces.get(faceSide).equals(faces.get(faceSide))) return false;
        }

        return true;
    }

    public void performMove(Move move) {

        switch (move) {
            case downl:
                move(FaceSide.front, TLBR.bottom, false,
                        FaceSide.left, TLBR.bottom, false,
                        FaceSide.back, TLBR.bottom, false,
                        FaceSide.right, TLBR.bottom, false);
                faces.get(FaceSide.down).rotate(TLBR.left);
                break;

            case downr:
                move(FaceSide.front, TLBR.bottom, false,
                        FaceSide.right, TLBR.bottom, false,
                        FaceSide.back, TLBR.bottom, false,
                        FaceSide.left, TLBR.bottom, false);
                faces.get(FaceSide.down).rotate(TLBR.right);
                break;

            case upl:
                move(FaceSide.front, TLBR.top, false,
                        FaceSide.right, TLBR.top, false,
                        FaceSide.back, TLBR.top, false,
                        FaceSide.left, TLBR.top, false);
                faces.get(FaceSide.up).rotate(TLBR.left);
                break;

            case upr:
                move(FaceSide.front, TLBR.top, false,
                        FaceSide.left, TLBR.top, false,
                        FaceSide.back, TLBR.top, false,
                        FaceSide.right, TLBR.top, false);
                faces.get(FaceSide.up).rotate(TLBR.right);
                break;

            case frontl:
                move(FaceSide.down, TLBR.top, true,
                        FaceSide.right, TLBR.left, false,
                        FaceSide.up, TLBR.bottom, true,
                        FaceSide.left, TLBR.right, false);
                faces.get(FaceSide.front).rotate(TLBR.left);
                break;

            case frontr:
                move(FaceSide.down, TLBR.top, false,
                        FaceSide.left, TLBR.right, true,
                        FaceSide.up, TLBR.bottom, false,
                        FaceSide.right, TLBR.left, true);
                faces.get(FaceSide.front).rotate(TLBR.right);
                break;

            case rightl:
                move(FaceSide.down, TLBR.right, true,
                        FaceSide.back, TLBR.left, true,
                        FaceSide.up, TLBR.right, false,
                        FaceSide.front, TLBR.right, false);
                faces.get(FaceSide.right).rotate(TLBR.left);
                break;

            case rightr:
                move(FaceSide.down, TLBR.right, false,
                        FaceSide.front, TLBR.right, false,
                        FaceSide.up, TLBR.right, true,
                        FaceSide.back, TLBR.left, true);
                faces.get(FaceSide.right).rotate(TLBR.right);
                break;

            case backl:
                move(FaceSide.down, TLBR.bottom, false,
                        FaceSide.left, TLBR.left, true,
                        FaceSide.up, TLBR.top, false,
                         FaceSide.right, TLBR.right, true);
                faces.get(FaceSide.back).rotate(TLBR.left);
                break;

            case backr:
                move(FaceSide.down, TLBR.bottom, true,
                        FaceSide.right, TLBR.right, false,
                        FaceSide.up, TLBR.top, true,
                        FaceSide.left, TLBR.left, false);
                faces.get(FaceSide.back).rotate(TLBR.right);
                break;

            case leftl:
                move(FaceSide.down, TLBR.left, false,
                        FaceSide.front, TLBR.left, false,
                        FaceSide.up, TLBR.left, true,
                        FaceSide.back, TLBR.right, true);
                faces.get(FaceSide.left).rotate(TLBR.left);
                break;

            case leftr:
                move(FaceSide.down, TLBR.left, true,
                        FaceSide.back, TLBR.right, true,
                        FaceSide.up, TLBR.left, false,
                        FaceSide.front, TLBR.left, false);
                faces.get(FaceSide.left).rotate(TLBR.right);
                break;
        }
    }

    private void move(FaceSide faceSide1, TLBR tlbr1, Boolean reverse1,
                      FaceSide faceSide2, TLBR tlbr2, Boolean reverse2,
                      FaceSide faceSide3, TLBR tlbr3, Boolean reverse3,
                      FaceSide faceSide4, TLBR tlbr4, Boolean reverse4
                      ) {
        ArrayList<FaceSide> tmp = faces.get(faceSide1).getThree(tlbr1, reverse1);
        faces.get(faceSide1).setThree(tlbr1, faces.get(faceSide4).getThree(tlbr4, reverse4));
        faces.get(faceSide4).setThree(tlbr4, faces.get(faceSide3).getThree(tlbr3, reverse3));
        faces.get(faceSide3).setThree(tlbr3, faces.get(faceSide2).getThree(tlbr2, reverse2));
        faces.get(faceSide2).setThree(tlbr2, tmp);
    }
}
