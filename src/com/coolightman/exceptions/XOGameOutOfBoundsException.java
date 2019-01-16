package com.coolightman.exceptions;

public class XOGameOutOfBoundsException extends XOException {

    @Override
    public String getMessage() {
        return "Out of bounds exception! Try move again from 0 to 8:";
    }
}
