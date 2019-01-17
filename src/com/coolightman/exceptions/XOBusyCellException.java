package com.coolightman.exceptions;

public class XOBusyCellException extends XOException{
    @Override
    public String getMessage() {
        return "Cell is busy. Try another moves:";
    }
}
