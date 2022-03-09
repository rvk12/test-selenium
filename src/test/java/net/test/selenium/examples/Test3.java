package net.test.selenium.examples;

import java.util.Arrays;

public class Test3 {

//    Постановка задачи
//    Написать функцию, принимающую 2 аргумента - некое слово и букву. Функция должна посчитать сколько раз эта буква встречается
//    в данном слове и вернуть полученное число вхождений.
//    Данные для проверки
//            ('а', 'колбаса') → 2
//
//            ('о', 'колбаса') → 1
//
//            ('ы', 'колбаса') → 0

    public static void main(String[] args) {
//        System.out.println(countLetter('А', "колбаса"));
//        System.out.println(countLetter('О', "колбаса"));
//        System.out.println(countLetter('Ы', "колбаса"));

        System.out.println(isAnagramma("жуткая", "утяжка"));
        System.out.println(isAnagramma("канонада", "анаконда"));
        System.out.println(isAnagramma("стол", "стул"));
    }

    public static boolean isAnagramma(String first, String second) {
        if (first.length() != second.length()) {
            return false;
        }
        char[] firstChar = first.toCharArray();
        char[] secondChar = second.toCharArray();
        Arrays.sort(firstChar);
        Arrays.sort(secondChar);
        if (Arrays.compare(firstChar, secondChar) != 0) {
            return false;
        }
//        for (int i = 0; i < firstChar.length - 1; i++) {
//            if (firstChar[i] != secondChar[i]) {
//                return false;
//            }
//        }
        return true;
    }

}
