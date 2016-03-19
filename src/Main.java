import java.util.ArrayList;
import java.util.Random;

/**
 * Created by gu on 2016/3/18.
 */
public class Main {

    public static void main(String args[]) {
        FaceSide.setFaceSideColorDictionary(Color.yellow, Color.white, Color.orange, Color.blue, Color.red, Color.green);

        Model model = initModel(1234567890, 50);

        System.out.print(model);
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
