package com.oraclechain.pocketvkt.blockchain.types;

import java.util.Collection;

/**
 * Created by swapnibble on 2017-09-12.
 */

public interface VktType {
    class InsufficientBytesException extends Exception {

        private static final long serialVersionUID = 1L;
    }

    interface Packer {
        void pack(VktType.Writer writer);
    }

    interface Unpacker {
        void unpack(VktType.Reader reader) throws VktType.InsufficientBytesException;
    }

    interface Reader {
        byte get() throws VktType.InsufficientBytesException;
        int getShortLE() throws VktType.InsufficientBytesException;
        int getIntLE() throws VktType.InsufficientBytesException;
        long getLongLE() throws VktType.InsufficientBytesException;
        byte[] getBytes(int size) throws VktType.InsufficientBytesException;
        String getString() throws VktType.InsufficientBytesException;

        long getVariableUint() throws VktType.InsufficientBytesException;
    }

    interface Writer {
        void put(byte b);
        void putShortLE(short value);
        void putIntLE(int value);
        void putLongLE(long value);
        void putBytes(byte[] value);
        void putString(String value);
        byte[] toBytes();
        int length();

        void putCollection(Collection<? extends Packer> collection);

        void putVariableUInt(long val);
    }
}
