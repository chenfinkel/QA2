import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import system.FileSystem;
import system.Leaf;
import system.OutOfSpaceException;
import system.Space;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SpaceTest {

    Space space;

    @Before
    public void setUp(){
        FileSystem.fileStorage = new Space(10);
        space = FileSystem.fileStorage;
    }

    @Test
    public void fileShouldBeAddedToBlocks() throws OutOfSpaceException {
        Leaf leaf = new Leaf("leaf",0);
        leaf.size = 3;
        space.Alloc(3, leaf);
        Leaf[] blocks = space.getAlloc();
        assertThat(Arrays.asList(blocks), CoreMatchers.hasItem(leaf));
    }

    @Test
    public void fileShouldBeAllocated() throws OutOfSpaceException {
        Leaf leaf = new Leaf("leaf",0);
        leaf.size = 3;
        space.Alloc(3, leaf);
        int[] expectedAlloc = {0,1,2};
        assertArrayEquals(leaf.allocations, expectedAlloc);
    }

    @Test
    public void dealloc() {
    }

    @Test
    public void allSpaceShouldBeFree() {
        assertEquals(10, space.countFreeSpace());
    }

    @Test
    public void getAlloc() {
    }
}