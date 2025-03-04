package com.security.jnaNative;

import java.math.BigInteger;
import java.util.Random;


public class GInteger extends BigInteger {

    private static final long serialVersionUID = 1L;

    public GInteger(BigInteger other) {
        super(other.toByteArray());
    }

    public GInteger(byte[] val) {
        super(val);
    }

    public GInteger(int signum, byte[] magnitude) {
        super(signum, magnitude);
    }

    public GInteger(String val, int radix) {
        super(val, radix);
    }

    public GInteger(String val) {
        super(val);
    }

    public GInteger(int numbits, Random r) {
        super(numbits, r);
    }

    public GInteger(int bitlength, int certainty, Random r) {
        super(bitlength, certainty, r);
    }
}
