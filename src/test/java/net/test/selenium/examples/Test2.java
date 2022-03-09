package net.test.selenium.examples;

import org.jcodings.util.Hash;

import java.util.ArrayList;
import java.util.HashMap;

public class Test2 {

    //
    public static void main(String[] args) {
        int index = 4;
        int znamenatel = 2;
        //System.out.println(getValueGeometricheskayaProgressia(index, znamenatel));
        System.out.println(isPolyndrom("потоп"));
        System.out.println(isPolyndrom("потом"));
    }

    public static int getValueGeometricheskayaProgressia(int index, int znamenatel) {
        int result = 1;
        for (int i = 1; i < index; i++) {
            result = result * znamenatel;
        }
        return result;
    }

    public static boolean isPolyndrom(String value) {
        int size = value.length();
        for (int i = 0; i < size; i++) {
            if (value.charAt(i) != value.charAt(size - 1 - i)) {
                return false;
            }
        }
        return true;
    }
}
