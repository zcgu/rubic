import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by gu on 2016/3/18.
 */
public class Main {

    public static void main(String args[]) {
        FaceSide.setFaceSideColorDictionary(Color.yellow, Color.white, Color.orange, Color.blue, Color.red, Color.green);

        Model model = initModel(1234567890, 10);

        if (args.length == 1) {
            model = initModel(1234567890, Integer.valueOf(args[0]));
        } else if (args.length == 2) {
            model = initModel(Integer.valueOf(args[1]), Integer.valueOf(args[0]));
        }

        System.out.print(model);

        DoubleDepthSearch doubleDepthSearch = new DoubleDepthSearch(model);

        System.out.print(doubleDepthSearch.search());

    }

    static private Model initModel(long seed, int times) {
        Model model = new Model();
        Move[] allMoves = Move.values();

        Random random = new Random(seed);

        for (int i = 0; i < times; i++) {
            Move move = allMoves[random.nextInt(allMoves.length)];
            model.performMove(move);
            System.out.print(move + ", ");
        }

        System.out.println();
        System.out.println();

        return model;
    }
}
