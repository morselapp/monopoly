import monopoly.DiceService;
import monopoly.models.DiceSuit;
import monopoly.models.DiceTuple;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DiceServiceTest extends BaseTest{

    private DiceService diceService;
    private DiceSuit diceSuit;
    private DiceTuple diceTuple;

    @Before
    public void setup(){
        diceSuit = mock(DiceSuit.class);
        diceService = new DiceService(diceSuit);

        diceTuple = new DiceTuple(3,5);
        when(diceSuit.roll()).thenReturn(diceTuple);
    }

    @Test
    public void rollTest_zeroDouble(){
        Assert.assertEquals(diceTuple.getFaceX()+diceTuple.getFaceY(), sum(diceService.roll()));
    }

    @Test
    public void rollTest_threeDouble(){
        diceTuple = new DiceTuple(4,4);
        when(diceSuit.roll()).thenReturn(diceTuple);
        Assert.assertEquals(-2, sum(diceService.roll()));
    }

    private int sum(List<DiceTuple> diceTuples){
        int suitOutcome = 0;

        for (DiceTuple tuple: diceTuples) {
            suitOutcome += tuple.getFaceX() +  tuple.getFaceY();
        }
        return suitOutcome;
    }

}
