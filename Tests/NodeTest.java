import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import system.Node;
import system.Tree;

import static org.junit.Assert.*;

public class NodeTest {

    private Node node;

    @BeforeClass
    public void setUp(){
        Tree tree1 = new Tree("root");
        Tree tree2 = tree1.GetChildByName("child");
        node = tree2.GetChildByName("node");
    }

    @Test
    public void pathShouldBeCorrect(){
        String[] pathExpected =  {"root", "child", "node"};
        assertArrayEquals(pathExpected, node.getPath());
    }
}