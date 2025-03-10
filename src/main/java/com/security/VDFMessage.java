package com.security;

import com.google.common.base.Objects;

import java.io.Serializable;
import java.util.Arrays;

public class VDFMessage implements Serializable {
    private byte[] VDFSolution;

    public VDFMessage(byte[] buffer) {
        this.VDFSolution = buffer;
    }

    public VDFMessage() {
    }

    public byte[] getVDFSolution() {
        return VDFSolution;
    }

    public void setVDFSolution(byte[] VDFSolution) {
        this.VDFSolution = VDFSolution;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VDFMessage that = (VDFMessage) o;
        return Arrays.equals(VDFSolution, that.VDFSolution);
    }

    public int length() {
        return 100 + VDFSolution.length;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(VDFSolution);
    }

    @Override
    public String toString() {
        return "VDFMessage{" +
                "buffer=" + Arrays.toString(VDFSolution) +
                '}';
    }
}
