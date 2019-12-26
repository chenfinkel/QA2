package Stubs;

import system.Leaf;
import system.OutOfSpaceException;
import system.Space;

public class SpaceStub extends Space {

    public boolean allocated = false;

    public SpaceStub(){
        super(5);
    }


    public void Alloc(int size, Leaf file) throws OutOfSpaceException {
        if (size > 5)
            throw new OutOfSpaceException();
        else
            allocated = true;
    }
}
