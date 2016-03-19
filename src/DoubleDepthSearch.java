import com.sun.org.apache.xpath.internal.operations.Mod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

/**
 * Created by gu on 2016/3/19.
 */
public class DoubleDepthSearch {

    private Model inputModel;
    private Model goalModel;

    DoubleDepthSearch(Model inputModel) {
        this.inputModel = inputModel;
        goalModel = new Model();
    }

    private HashSet<Model> inputSearchSpace;
    private ArrayList<Model> inputQue;
    private HashSet<Model> goalSearchSpace;
    private ArrayList<Model> goalQue;

    public ArrayList<Move> search() {
        inputSearchSpace = new HashSet<>();
        inputQue = new ArrayList<>();
        goalSearchSpace = new HashSet<>();
        goalQue = new ArrayList<>();


        //

        inputQue.add(inputModel);
        goalQue.add(goalModel);

        //

        while(true) {
            Model current = inputQue.get(0);
            inputQue.remove(0);

            if (goalSearchSpace.contains(current)) {

                inputSearchSpace.add(current);
                return sucessAndOutput(current);

            } else if (!inputSearchSpace.contains(current)) {

                inputSearchSpace.add(current);
                for (Move move : Move.values()) {
                    Model newMovel = (Model) current.clone();
                    newMovel.performMove(move);
                    newMovel.lastMove = move;
                    inputQue.add(newMovel);
                }
            }

            current = goalQue.get(0);
            goalQue.remove(0);

            if (inputSearchSpace.contains(current)) {

                goalSearchSpace.add(current);
                return sucessAndOutput(current);

            } else if (!goalSearchSpace.contains(current)) {

                goalSearchSpace.add(current);
                for (Move move : Move.values()) {
                    Model newMovel = (Model) current.clone();
                    newMovel.performMove(move);
                    newMovel.lastMove = move;
                    goalQue.add(newMovel);
                }

            }


        }
    }

    private ArrayList<Move> sucessAndOutput(Model model) {

        ArrayList<Model> inputSearchList = new ArrayList<>(inputSearchSpace);
        ArrayList<Model> goalSearchList = new ArrayList<>(goalSearchSpace);

        ArrayList<Move> res = new ArrayList<>();

        Model current = (Model) inputSearchList.get( inputSearchList.indexOf(model) ).clone();

        while (!current.equals(inputModel)) {
            res.add(current.lastMove);
            current.performMove(Move.inverseMove(current.lastMove));
            current = (Model) inputSearchList.get( inputSearchList.indexOf(current) ).clone();
        }

        Collections.reverse(res);

        current = (Model) goalSearchList.get( goalSearchList.indexOf(model) ).clone();

        while (!current.equals(goalModel)) {
            res.add(Move.inverseMove(current.lastMove) );
            current.performMove(Move.inverseMove(current.lastMove));
            current = (Model) goalSearchList.get( goalSearchList.indexOf(current) ).clone();
        }

        return res;
    }


}
