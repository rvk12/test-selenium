package net.test.selenium;

import org.jruby.RubyProcess;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.StringReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
Implement a method to perform basic string compression using the counts of repeated characters.
For example, the string aabcccccaaa would become a2b1c5a3.
If the "compressed" string would not become smaller than the original string,
your method should return the original string.

You can assume the string has only uppercase and lowercase letters (a - z).
*/
public class Test {

        public static void main (String[] args) throws IOException, URISyntaxException, ParseException, XPathExpressionException, SAXException, ParserConfigurationException {
            System.out.println(compressString("abc"));
            System.out.println(compressString(""));
            System.out.println(compressString("123"));
            System.out.println(compressString("aabcccccaaa"));
            System.out.println(compressString("regex below:"));
            System.out.println(compressStringRegex("aaaabbdcccccaaa"));
            System.out.println(compressStringRegex("aabbc"));

            System.out.println(sortString("avjoqmmnopo"));
            System.out.println(sortString("6357599845670651"));

            System.out.println(Arrays.toString(bubbleSort(new int[]{4,4,2,8,0,1,5,5,7,3})));

            System.out.println(readLog());

            System.out.println(parseXml());
        }

        public static String compressString(String initialString) {
            int counter = 0;
            int position = 0;
            String result = initialString;
            int length = initialString.length();
            for(int i = 0; i < length; i++) {
                if(length > i + 1 && initialString.charAt(i) == initialString.charAt(i + 1)) {
                    if(counter == 0) {
                        position = i;
                        counter++;
                    }
                    counter++;
                } else if(counter > 2) {
                    final StringBuilder sb = new StringBuilder();
                    for(int k = 1; k <= counter; k++) {
                        sb.append(initialString.charAt(position));
                    }
                    String rep = sb.toString();
                    result = result.replace(rep,initialString.charAt(position) + String.valueOf(counter));
                    counter = 0;
                    position = 0;
                } else {
                    counter = 0;
                    position = 0;
                }
            }
            return result;
        }

        public static String compressStringRegex(String initialString) {
            String result = initialString;
            // \1 - backreference to group (обратная ссылка на группу)
            // \1{2,} - \1 это символ + еще 2 повтора, итого будет работать от 3х повторов
            // чтобы работало от 2х повторов символов надо \1{2,} или \1+
            Pattern pattern = Pattern.compile("([A-Za-z])\\1+");
            Matcher m = pattern.matcher(initialString);
            while (m.find()) {
                result = result.replaceFirst(m.group(), m.group(1) + m.group().length());
            }
            if (result.length() == initialString.length()) {
                result = initialString;
            }
            return result;
        }

        public static String sortString(String initialString) {
            String result = initialString;
            List<Character> l = result.chars().mapToObj(i -> (char) i).sorted().collect(Collectors.toList());
            StringBuilder stringBuilder = new StringBuilder();
            for (Character ch : l) {
                stringBuilder.append(ch);
            }
            result = stringBuilder.toString();
            //or
//            char[] array = result.toCharArray();
//            Arrays.sort(array);
//            result = new String(array);
            return result;
        }

        public static int[] bubbleSort(int[] array) {
            int[] result = array.clone();
            for(int k = 0; k < result.length; k++) {
                for(int i = 1; i < result.length; i++) {
                    if(result[i - 1] > result[i]) {
                        int temp = result[i];
                        result[i] = result[i - 1];
                        result[i - 1] = temp;
                    }
                }
            }
            return result;
        }

        public static String readLog() throws URISyntaxException, IOException, ParseException {
            List<String> lines = Files.readAllLines(Path.of(Test.class.getResource("/app.log").toURI()));
//            byte[] ba = Files.readAllBytes(Path.of(Test.class.getResource("/app.log").toURI()));
//            System.out.println(new String(ba));
            StringBuilder sb = new StringBuilder();

            for(String line : lines) {
                Pattern pa = Pattern.compile("\\d{2}.\\d{2}.\\d{4} \\d{2}:\\d{2}:\\d{2}");
                Matcher ma = pa.matcher(line);
                if(ma.find()) {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
                    Calendar cal1 = Calendar.getInstance();
                    cal1.setTime(new Date());
                    cal1.add(Calendar.MINUTE, -35);
                    Calendar cal2 = Calendar.getInstance();
                    cal2.setTime(new Date());
                    Date date1 = cal1.getTime();
                    Date date2 = cal2.getTime();
                    Date dateF = sdf.parse(ma.group());
                    if(dateF.after(date1) && dateF.before(date2)) {
                        sb.append(line + "\n");
                    }
                }
            }
            return sb.toString();
        }

        public static String parseXml() throws ParserConfigurationException, IOException, SAXException, XPathExpressionException, URISyntaxException {
            InputSource source = new org.xml.sax.InputSource(new StringReader(Files
                    .readString(Paths.get(Test.class.getResource("/log.xml").toURI()))));
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document document = db.parse(source);
            XPathFactory xpathFactory = XPathFactory.newInstance();
            XPath xpath = xpathFactory.newXPath();
            return xpath.evaluate("//div//div", document);
        }

}
