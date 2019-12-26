import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;
import system.*;

import java.util.Arrays;

import static org.junit.Assert.*;

public class SpaceTest {

    private Space space;
    private Leaf leaf;

    @Before
    public void setUp() throws OutOfSpaceException{
        FileSystem.fileStorage = new Space(10);
        space = FileSystem.fileStorage;
        leaf = new Leaf("leaf",0);
        leaf.size = 3;
        space.Alloc(3, leaf);
    }

    @Test
    public void fileShouldBeAddedToBlocks(){
        Leaf[] blocks = space.getAlloc();
        assertThat(Arrays.asList(blocks), CoreMatchers.hasItem(leaf));
    }

    @Test
    public void fileShouldBeAllocated(){
        int[] expectedAlloc = {0,1,2};
        assertArrayEquals(leaf.allocations, expectedAlloc);
    }

    @Test
    public void tenShouldBeFree() {
        Tree tree = new Tree("parent");
        leaf.parent = tree;
        tree.children.put("leaf", leaf);
        space.Dealloc(leaf);
        assertEquals(10, space.countFreeSpace());
    }

    @Test
    public void blocksShouldBeFree() {
        Tree tree = new Tree("parent");
        leaf.parent = tree;
        tree.children.put("leaf", leaf);
        space.Dealloc(leaf);
        Leaf[] expectedAlloc = {null,null,null,null,null,null,null,null,null,null};
        assertArrayEquals(expectedAlloc, space.getAlloc());
    }

    @Test
    public void parentShouldHaveDeletedLeaf(){
        Tree tree = new Tree("parent");
        leaf.parent = tree;
        tree.children.put("leaf", leaf);
        space.Dealloc(leaf);
        assertTrue(tree.children.isEmpty());
    }

    @Test
    public void sevenShouldBeFree() {
        assertEquals(7, space.countFreeSpace());
    }

    @Test
    public void getAlloc() {
        Leaf[] expectedAlloc = {leaf, leaf, leaf, null, null, null, null, null, null, null};
        assertArrayEquals(expectedAlloc,space.getAlloc());
    }
}