package blockchain.blocks;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CarrierBlockTest {
    SimpleBlock previous = new SimpleBlock(1L, "0", System.currentTimeMillis());
    SimpleBlock next = new SimpleBlock(2L, previous.hash(), System.currentTimeMillis());

    @Test
    public void isPreviousOf() {
        CarrierBlock carrierBlock = new CarrierBlock(next, "Hello", "");
        assertTrue(carrierBlock.isNextFor(previous));
        assertFalse(previous.isNextFor(carrierBlock));
    }
}
