import Stubs.SpaceStub;

import java.nio.file.DirectoryNotEmptyException;
import java.util.Arrays;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import system.BadFileNameException;
import system.FileSystem;
import system.Leaf;
import system.OutOfSpaceException;

import javax.swing.*;

import static org.junit.Assert.*;

public class FileSystemTest {

    private FileSystem fs;
    private String[] name = {"root","name1"};

    @Before
    public void setUp() throws Exception {
        fs = new FileSystem(5);
    }

    @After
    public void tearDown() throws Exception {
        fs.fileStorage = null;
        fs = null;
    }

    @Test
    public void dir() throws BadFileNameException {
        fs.dir(name);
        assertNotNull(fs.DirExists(name));
    }


    @Test
    public void disk() throws BadFileNameException, OutOfSpaceException {/**
     String[] name2 = {"root","name2"};
     String[] name3 = {"root","name3"};
     String[] name4 = {"root","name4"};
     String[][] names = {name, name2, name3, name4};
     for (String[] n: names) {
     fs.file(n,1);
     }
     String[][] res = fs.disk();
     for (String[] a: res
     ) {
     System.out.println(a);
     }
     assertTrue(Arrays.deepEquals(names, res));*/
    }

    @Test (expected = BadFileNameException.class)
    public void badFileName() throws BadFileNameException, OutOfSpaceException {
        String[] name = {"name","name1"};
        fs.file(name, 1);
    }

    @Test (expected = OutOfSpaceException.class)
    public void fileOutOfSpace() throws BadFileNameException, OutOfSpaceException {
        fs.file(name, 6);
    }

    //The lsdir method supposed to return only files, therefore i created files
    //but if there is a directory inside the name1 directory it will print it too
    @Test
    public void lsdir() throws BadFileNameException, OutOfSpaceException {
        String[] name2 = {"root","name1","name2"};
        String[] name3 = {"root","name1","name3"};
        String[] name4 = {"root","name1","name4"};
        String[][] names = {name2, name3, name4};
        fs.dir(name);
        for (String[] n : names) {
            fs.file(n, 1);
        }
        String[] res = fs.lsdir(name);
        String[] expected = {"name2", "name3", "name4"};
        assertTrue(Arrays.deepEquals(res,expected));
    }

    @Test
    public void rmfile() throws OutOfSpaceException, BadFileNameException {
        fs.file(name, 2);
        fs.rmfile(name);
        assertNull(fs.FileExists(name));
    }

    @Test
    public void rmdir() throws BadFileNameException, DirectoryNotEmptyException {
        fs.dir(name);
        fs.rmdir(name);
        assertNull(fs.DirExists(name));
    }

    @Test (expected = DirectoryNotEmptyException.class)
    public void rmdirNotEmpty() throws BadFileNameException, DirectoryNotEmptyException {
        String[] name1 = {"root", "name", "name1"};
        fs.dir(name);
        fs.dir(name1);
        fs.rmdir(name);
    }

    @Test
    public void fileExists() throws OutOfSpaceException, BadFileNameException {
        int k = 2;
        fs.file(name, k);
        Leaf leaf = fs.FileExists(name);
        assertNotNull(leaf);
        assertTrue(Arrays.deepEquals(leaf.getPath(),name));
    }

    @Test
    public void fileDoesntExist() {
        assertNull(fs.FileExists(name));
    }

    @Test
    public void dirExists() throws BadFileNameException {
        fs.dir(name);
        assertTrue(fs.DirExists(name) != null);
    }

    @Test
    public void dirDoesntExist() throws BadFileNameException {
        String[] name = {"root","first"};
        String[] name2 = {"root","second"};
        fs.dir(name);
        assertNull(fs.DirExists(name2));
    }

}