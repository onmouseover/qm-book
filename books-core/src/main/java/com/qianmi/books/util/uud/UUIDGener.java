package com.qianmi.books.util.uud;

import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * <p>
 * </p>
 * 
 * @version 1.0
 */
public class UUIDGener {
    private static UIDFactory uuid = null;

    static {
        try {
            uuid = UIDFactory.getInstance("UUID");
        } catch (UIDNotSupportException unsex) {
        }

        ;
    }

    /** Constructor for the UUIDGener object */
    public UUIDGener() {
    }

    public static String getUUID() {
        return uuid.getNextUID();
    }

    public static void main(String[] args) {
        try {
            BufferedWriter fw = new BufferedWriter(new FileWriter("d:\\dd.txt"));

            for (int i = 1; i < 10000; i++) {
                fw.write(uuid.getNextUID());
                fw.write(System.getProperty("line.separator"));
            }

            fw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
