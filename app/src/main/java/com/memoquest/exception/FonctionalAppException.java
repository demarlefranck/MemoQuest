package com.memoquest.exception;

public class FonctionalAppException extends Exception {

    private String Text;

    public FonctionalAppException(String message) {
        this.Text = message;
    }

    public String getText() {
        return Text;
    }
}
