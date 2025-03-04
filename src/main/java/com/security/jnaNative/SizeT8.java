package com.security.jnaNative;

import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface SizeT8 extends Library {
    void __gmpz_import(mpz_t rop, int count, int order, int size, int endian, int nails, Pointer buffer);

    Pointer __gmpz_export(Pointer rop, Pointer countp, int order, int size, int endian, int nails, mpz_t op);
}
