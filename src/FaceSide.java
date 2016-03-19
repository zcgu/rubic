import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by gu on 2016/3/16.
 */
public enum FaceSide {
    down, up, front, right, back, left;

    static private ArrayList<FaceSide> faceSideList = new ArrayList<>();

    static public Dictionary<FaceSide, Color> faceSideColorDictionary = new Hashtable<>();

    static public ArrayList<FaceSide> getList() {
        if (faceSideList.isEmpty()) {
            faceSideList.add(FaceSide.down);
            faceSideList.add(FaceSide.up);
            faceSideList.add(FaceSide.front);
            faceSideList.add(FaceSide.right);
            faceSideList.add(FaceSide.back);
            faceSideList.add(FaceSide.left);
        }

        return faceSideList;
    }

    static public void setFaceSideColorDictionary(Color upColor,
                                                  Color downColor,
                                                  Color frontColor,
                                                  Color rightColor,
                                                  Color backColor,
                                                  Color leftColor) {
        if (!faceSideColorDictionary.isEmpty()) System.out.println("error");

        faceSideColorDictionary.put(up, upColor);
        faceSideColorDictionary.put(down, downColor);
        faceSideColorDictionary.put(front, frontColor);
        faceSideColorDictionary.put(right, rightColor);
        faceSideColorDictionary.put(back, backColor);
        faceSideColorDictionary.put(left, leftColor);
    }

    @Override
    public String toString() {
        switch (this) {
            case down:
                return "down ";
            case up:
                return "up   ";
            case front:
                return "front";
            case right:
                return "right";
            case back:
                return "back ";
            case left:
                return "left ";
            default:
                return "error";
        }
    }
}
