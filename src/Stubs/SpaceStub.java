package Stubs;

import system.Leaf;
import system.OutOfSpaceException;
import system.Space;

public class SpaceStub extends Space {

    public boolean allocated = false;

    public SpaceStub(){
        super(1);
    }


    public void Alloc(int size, Leaf file) throws OutOfSpaceException {
        if (size > 1)
            throw new OutOfSpaceException();
        else
            allocated = true;
    }
}
