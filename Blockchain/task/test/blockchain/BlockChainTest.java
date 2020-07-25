package blockchain;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;

public class BlockChainTest {
    private BlockChain blockChain;

    @Before
    public void createBlockChain() {
        blockChain = new BlockChain(0);
        for (int i = 0; i < 5; i++) {
            blockChain.createBlock();
        }
    }

    @Test
    public void should_survive_reflection_access()
            throws ReflectiveOperationException {
        List<Block> blocks = getBlocks(blockChain);
        blocks.set(1, new Block(2, 2, blocks.get(0).hash(), 2));
        setPreviousBlockHash(blocks.get(2), blocks.get(1).hash());

        assertFalse(blockChain.isValid());
    }

    private ArrayList<Block> getBlocks(BlockChain blockChain)
            throws ReflectiveOperationException {
        Field blocksField = BlockChain.class.getDeclaredField("blocks");
        blocksField.setAccessible(true);
        return (ArrayList<Block>) blocksField.get(blockChain);
    }

    private void setPreviousBlockHash(Block block, String hash)
            throws ReflectiveOperationException {
        Field previousBlockHashField =
                Block.class.getDeclaredField("previousBlockHash");
        previousBlockHashField.setAccessible(true);
        previousBlockHashField.set(block, hash);
    }
}