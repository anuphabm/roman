/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author anuphabm
 */
public class RomanCalculator {

    private final static HashMap<String, Integer> hm = new HashMap<String, Integer>();
    private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();

    static {

        map.put(1000, "M");
        map.put(900, "CM");
        map.put(500, "D");
        map.put(400, "CD");
        map.put(100, "C");
        map.put(90, "XC");
        map.put(50, "L");
        map.put(40, "XL");
        map.put(10, "X");
        map.put(9, "IX");
        map.put(5, "V");
        map.put(4, "IV");
        map.put(1, "I");

        hm.put("I", 1);
        hm.put("V", 5);
        hm.put("X", 10);
        hm.put("L", 50);
        hm.put("C", 100);
        hm.put("D", 500);
        hm.put("M", 1000);

    }

    public final static String toRoman(int number) {
        int floor = map.floorKey(number);
//        System.out.println("floor:" + l);
        if (number == floor) {
            return map.get(number);
        }
        return map.get(floor) + toRoman(number - floor);
    }

    public static int romanToInteger(String roman) {
        int valInt = 0;

        while (roman.length() > 0) {
            if (!isFormatRoman(roman)) {
                return 0;
            }
            if (roman.length() == 1) {
                valInt += (int) hm.get(roman);
                roman = roman.substring(0, roman.length() - 1);
                continue;
            }
            String roman1 = roman.substring(roman.length() - 1);
            String roman2 = roman.substring(roman.length() - 2, roman.length() - 1);
            if ((int) hm.get(roman1) > (int) hm.get(roman2)) {
                valInt += (int) hm.get(roman1) - (int) hm.get(roman2);
            }
            if ((int) hm.get(roman1) < (int) hm.get(roman2)) {
                valInt += (int) hm.get(roman1) + (int) hm.get(roman2);
            }
            if ((int) hm.get(roman1) == (int) hm.get(roman2)) {
                valInt += (int) hm.get(roman1) + (int) hm.get(roman2);
            }
            roman = roman.substring(0, roman.length() - 2);
        }
        return valInt;
    }

    public static String calulator(String calRoman) {
        if (calRoman.contains("+")) {
            String split[] = calRoman.split("\\+");
            int result = 0;
            for(String romanbuff: split){
                result += romanToInteger(romanbuff);
            }
            return toRoman(result);
        }
        return "";
    }

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        boolean flag = true;
        Scanner sc1 = new Scanner(System.in);
        while (flag) {
            System.out.println("Press 1 to Key Roman from keyboard!");
            System.out.println("Press 2 to Read Roman from text file!");
            System.out.println("Press 3 to Exit");
            System.out.print("Enter your choice: ");

            if (sc1.hasNextInt()) {
                int choice = sc1.nextInt();
                //more code here
                switch (choice) {
                    case 1:
                        System.out.print("Roman Input:");
                        Scanner scan = new Scanner(System.in);
                        String strRoman = scan.nextLine();
                        //print charector * 40 item
                        System.out.println(new String(new char[40]).replace('\0', '*'));
                        System.out.println("Result Roman: " + strRoman + " = " + calulator(strRoman));
                        //print charector * 40 item
                        System.out.println(new String(new char[40]).replace('\0', '*'));
                        break;
                    case 2:
                        System.out.print("Input file:");
                        Scanner scanFile = new Scanner(System.in);
                        String file = scanFile.nextLine();
                        //print charector * 40 item
                        System.out.println(new String(new char[40]).replace('\0', '*'));
                        printOut(file);
                        //print charector * 40 item
                        System.out.println(new String(new char[40]).replace('\0', '*'));
                        break;
                    case 3:
                        flag = false;
                        System.out.println("Exit program Thank!");
                        break;
                }
            } else {
                System.out.println("Please enter proper Integer input!\n");
                sc1.next();
            }
        }

    }

    private static boolean isFormatRoman(String roman) {
        if (roman.length() < 4) {
            return true;
        }
        String roman1 = roman.substring(roman.length() - 1);
        String roman2 = roman.substring(roman.length() - 2, roman.length() - 1);
        String roman3 = roman.substring(roman.length() - 3, roman.length() - 2);
        String roman4 = roman.substring(roman.length() - 4, roman.length() - 3);
        HashMap uniq = new HashMap();
        uniq.put(roman1, roman1);
        uniq.put(roman2, roman2);
        uniq.put(roman3, roman3);
        uniq.put(roman4, roman4);
        return uniq.size() != 1;
    }

    private static void printOut(String file) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(file);
        DataInputStream dataInputStream = new DataInputStream(fileInputStream);
        BufferedReader bufferReader = new BufferedReader(new InputStreamReader(dataInputStream));
        String line;

        while ((line = bufferReader.readLine()) != null) {
            System.out.println("Result Roman: " + line + " = " + calulator(line));
        }
    }

}
