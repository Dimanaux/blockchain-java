package blockchain.blocks;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class HardToMineBlockTest {
    SimpleBlock previous = new SimpleBlock(1L, "0", System.currentTimeMillis());
    SimpleBlock next = new SimpleBlock(2L, previous.hash(), System.currentTimeMillis());

    @Test
    public void isPreviousOf() {
        Optional<HardToMineBlock> hardToMineBlock = HardToMineBlock.create(next, 0, 23);
        assertTrue(hardToMineBlock.isPresent());
        assertTrue(hardToMineBlock.get().isNextFor(previous));
        assertFalse(previous.isNextFor(hardToMineBlock.get()));
    }
}