import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by gu on 2016/3/17.
 */
public class Face {
    public ArrayList<FaceSide> faceSides = new ArrayList<>();

    Face(FaceSide faceSide) {
        for (int i = 0; i < 9 ; i++ ) faceSides.add(faceSide);
    }

    @Override
    public int hashCode() {
        ArrayList<FaceSide> faceSideList = FaceSide.getList();

        int hashCode = 0;

        for (int i = 0; i < 9; i++) {
            hashCode += faceSideList.indexOf(faceSides.get(i)) * i;
        }

        return hashCode;
    }

    @Override
    public boolean equals(Object object) {
        if (getClass() != object.getClass()) return false;

        return this.faceSides.equals(((Face) object).faceSides);
    }

    @Override
    public Object clone() {
        Face newFace = new Face(faceSides.get(4));
        newFace.faceSides = new ArrayList<>(this.faceSides);
        return newFace;
    }

    @Override
    public String toString() {
        String res = "";
        res += FaceSide.faceSideColorDictionary.get(faceSides.get(0)) + " " + FaceSide.faceSideColorDictionary.get(faceSides.get(1))
                + " " + FaceSide.faceSideColorDictionary.get(faceSides.get(2)) + "\n";
        res += FaceSide.faceSideColorDictionary.get(faceSides.get(3)) + " " + FaceSide.faceSideColorDictionary.get(faceSides.get(4))
                + " " + FaceSide.faceSideColorDictionary.get(faceSides.get(5)) + "\n";
        res += FaceSide.faceSideColorDictionary.get(faceSides.get(6)) + " " + FaceSide.faceSideColorDictionary.get(faceSides.get(7))
                + " " + FaceSide.faceSideColorDictionary.get(faceSides.get(8)) + "\n";
        return res;
    }

    private ArrayList<FaceSide> getTopThree() {
        ArrayList<FaceSide> res = new ArrayList<>();
        res.add(faceSides.get(0));
        res.add(faceSides.get(1));
        res.add(faceSides.get(2));
        return res;
    }

    private ArrayList<FaceSide> getBottomThree() {
        ArrayList<FaceSide> res = new ArrayList<>();
        res.add(faceSides.get(6));
        res.add(faceSides.get(7));
        res.add(faceSides.get(8));
        return res;
    }
    private ArrayList<FaceSide> getLeftThree() {
        ArrayList<FaceSide> res = new ArrayList<>();
        res.add(faceSides.get(0));
        res.add(faceSides.get(3));
        res.add(faceSides.get(6));
        return res;
    }
    private ArrayList<FaceSide> getRightThree() {
        ArrayList<FaceSide> res = new ArrayList<>();
        res.add(faceSides.get(2));
        res.add(faceSides.get(5));
        res.add(faceSides.get(8));
        return res;
    }

    private void setTopThree(ArrayList<FaceSide> values) {
        faceSides.set(0, values.get(0));
        faceSides.set(1, values.get(1));
        faceSides.set(2, values.get(2));
    }
    private void setBottomThree(ArrayList<FaceSide> values) {
        faceSides.set(6, values.get(0));
        faceSides.set(7, values.get(1));
        faceSides.set(8, values.get(2));
    }
    private void setLeftThree(ArrayList<FaceSide> values) {
        faceSides.set(0, values.get(0));
        faceSides.set(3, values.get(1));
        faceSides.set(6, values.get(2));
    }
    private void setRightThree(ArrayList<FaceSide> values) {
        faceSides.set(2, values.get(0));
        faceSides.set(5, values.get(1));
        faceSides.set(8, values.get(2));
    }

    public void setThree(TLBR tlbr, ArrayList<FaceSide> values) {
        switch (tlbr) {
            case top:
                setTopThree(values);
                break;
            case left:
                setLeftThree(values);
                break;
            case right:
                setRightThree(values);
                break;
            case bottom:
                setBottomThree(values);
                break;
        }
    }

    public ArrayList<FaceSide> getThree(TLBR tlbr, Boolean reverse) {
        ArrayList<FaceSide> res = new ArrayList<>();

        switch (tlbr) {
            case top:
                res = getTopThree();
                break;
            case left:
                res = getLeftThree();
                break;
            case right:
                res = getRightThree();
                break;
            case bottom:
                res = getBottomThree();
                break;
        }

        if (reverse) Collections.reverse(res);

        return res;
    }

    public void rotate(TLBR leftTight) {
        switch (leftTight) {
            case left:
                FaceSide tmp = faceSides.get(0);
                faceSides.set(0, faceSides.get(2));
                faceSides.set(2, faceSides.get(8));
                faceSides.set(8, faceSides.get(6));
                faceSides.set(6, tmp);

                tmp = faceSides.get(1);
                faceSides.set(1, faceSides.get(5));
                faceSides.set(5, faceSides.get(7));
                faceSides.set(7, faceSides.get(3));
                faceSides.set(3, tmp);

                return;
            case right:
                rotate(TLBR.left);
                rotate(TLBR.left);
                rotate(TLBR.left);
                return;

            default:
                System.out.println("error");
                return;
        }
    }
}
