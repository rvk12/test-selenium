package net.test.selenium.examples;

import java.util.HashMap;
import java.util.Map;

public class Test1 {

    public static void main(String[] args) {
//        System.out.println(charArrayToMap(new char[]{'c', 's', 'e', 'c'}));
//        System.out.println(charArrayToMap(new char[]{'a', 'b', 'c'}));
//        System.out.println(charArrayToMap(new char[]{'c', 'c', 'c', 'c'}));

        System.out.println(checkContains3OrMoreSequentNumbers(new int[]{2, 4, 5, 6, 2}));
        System.out.println(checkContains3OrMoreSequentNumbers(new int[]{0, 22, 23, 24, 29}));
        System.out.println(checkContains3OrMoreSequentNumbers(new int[]{1, 2, 3, 4}));
        System.out.println(checkContains3OrMoreSequentNumbers(new int[]{1, 2, 4}));

        System.out.println(checkContains3OrMoreSequentNumbers2(new int[]{2, 4, 5, 6, 2}));
        System.out.println(checkContains3OrMoreSequentNumbers2(new int[]{0, 22, 23, 24, 29}));
        System.out.println(checkContains3OrMoreSequentNumbers2(new int[]{1, 2, 3, 4}));
        System.out.println(checkContains3OrMoreSequentNumbers2(new int[]{1, 2, 4}));
        System.out.println(checkContains3OrMoreSequentNumbers2(new int[]{1, 2, 4, 6, 7}));
    }

    /**
     * Дан массив Char. Написать метод, возвращающий Map<Char, Boolean>, где ключ - символ, значение - true, если символ встречается в массиве больше одного раза.
     *
     * Например :
     * someMethod(['c', 's', 'e', 'c']) → {"с": true, "s": false, "e": false}
     * someMethod(['a', 'b', 'c']) → {"a": false, "b": false, "c": false}
     * someMethod(['c', 'c', 'c', 'c']) → {"с": true}
     * */
    public static Map<Character, Boolean> charArrayToMap(char[] arrayToConvert) {
        Map<Character, Boolean> result = new HashMap<>();
        int arrayLength = arrayToConvert.length;
        for(int i = 0; i < arrayLength; i++) {
            if(result.containsKey(arrayToConvert[i])) {
                result.put(arrayToConvert[i], true);
            } else {
                result.put(arrayToConvert[i], false);
            }
        }

        return result;
    }


    /**
     * Написать метод, который будет возвращать true, если поданный на вход массив int содержит 3 и более последовательных числа в любом месте.
     *
     * Например :
     * someMethod([2, 4, 5, 6, 2]) → true
     * someMethod([0, 22, 23, 24, 29]) → true
     * someMethod([1, 2, 3, 4]) → true
     * someMethod([1, 2, 4]) → false
     * */
    public static boolean checkContains3OrMoreSequentNumbers(int[] array) {
        boolean result = false;
        int counter = 0;
        for(int i = 0; i < array.length; i++) {
            if(i > 0 && (array[i - 1] + 1) == array[i]) {
                counter++;
            } else {
                counter = 0;
            }
            if(counter >= 2) {
                result = true;
                break;
            }

        }

        return result;
    }

    public static boolean checkContains3OrMoreSequentNumbers2(int[] array) {
        boolean result = false;
        int counter = 0;
        int iterator = 0;
        int temp = 0;
        for(int e : array) {
            if(iterator > 0) {
                if(temp + 1 == e) {
                    counter++;
                } else
                    counter = 0;
            }
            if(counter >= 2) {
                result = true;
                break;
            }
            iterator++;
            temp = e;

        }
        return result;
    }

}
