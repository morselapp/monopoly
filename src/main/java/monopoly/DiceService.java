package monopoly;

import monopoly.models.DiceSuit;
import monopoly.models.DiceTuple;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DiceService {

    private DiceSuit diceSuit;

    public DiceService(DiceSuit diceSuit) {
        this.diceSuit = diceSuit;
    }

    public List<DiceTuple> roll() {
        List<DiceTuple> rolls = new ArrayList<>();
        DiceTuple diceTuple = null;
        int rollFrequency = 0;
        do {

            diceTuple = diceSuit.roll();
            rollFrequency++;

            if (rollFrequency == 3 && isDouble(diceTuple)) {
                return Collections.singletonList(new DiceTuple(-1, -1));
            }
            rolls.add(diceTuple);

        } while (isDouble(diceTuple) && rollFrequency < 3);
        return rolls;
    }

    private boolean isDouble(DiceTuple diceTuple) {
        return diceTuple.getFaceX() == diceTuple.getFaceY();
    }
}
