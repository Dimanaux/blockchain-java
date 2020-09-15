package blockchain.blocks;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleBlockTest {
    SimpleBlock previous = new SimpleBlock(1L, "0", System.currentTimeMillis());
    SimpleBlock next = new SimpleBlock(2L, previous.hash(), System.currentTimeMillis());

    @Test
    public void isPreviousOf() {
        assertTrue(next.isNextFor(previous));
        assertFalse(previous.isNextFor(next));
    }
}
