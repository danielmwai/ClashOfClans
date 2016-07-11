package com.commonSocket.net.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

public abstract class NestedRuntimeException extends RuntimeException {

    private static final long serialVersionUID = -3206112455559137551L;
    private Throwable cause;

    public NestedRuntimeException(String msg) {
        super(msg);
    }

    public NestedRuntimeException(String msg, Throwable ex) {
        super(msg);
        this.cause = ex;
    }

    public Throwable getCause() {
        return this.cause != this ? this.cause : null;
    }

    public String getMessage() {
        if ((this.cause == null) || (this.cause == this)) {
            return super.getMessage();
        }
        return super.getMessage() + "; nested exception is " + this.cause.getClass().getName() + ": "
                + this.cause.getMessage();
    }

    public void printStackTrace(PrintStream ps) {
        if ((this.cause == null) || (this.cause == this)) {
            super.printStackTrace(ps);
        } else {
            ps.println(this);
            this.cause.printStackTrace(ps);
        }
    }

    public void printStackTrace(PrintWriter pw) {
        if ((this.cause == null) || (this.cause == this)) {
            super.printStackTrace(pw);
        } else {
            pw.println(this);
            this.cause.printStackTrace(pw);
        }
    }

    public boolean contains(Class exClass) {
        if (exClass == null) {
            return false;
        }
        for (Throwable ex = this; ex != null;) {
            if (exClass.isInstance(ex)) {
                return true;
            }
            if ((ex instanceof NestedRuntimeException)) {
                ex = ((NestedRuntimeException) ex).getCause();
            } else {
                ex = null;
            }
        }

        return false;
    }
}
