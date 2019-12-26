import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import system.Leaf;
import system.Tree;

import static org.junit.Assert.*;

public class TreeTest {

    private Tree tree;

    @Before
    public void setUp() throws Exception {
        tree = new Tree("test");
        tree.children.put("child", new Tree("child"));
    }

    @Test
    public void childShouldBeAddedToChildren() {
        tree.GetChildByName("child2");
        assertTrue(tree.children.containsKey("child2"));
    }

    @Test
    public void childShouldBeRetrieved() {
        Tree child = tree.GetChildByName("child");
        assertSame(child, tree.children.get("child"));
    }

    @Test
    public void childParentShouldBeUpdated(){
        tree.GetChildByName("child2");
        assertSame(tree, tree.children.get("child2").parent);
    }
}