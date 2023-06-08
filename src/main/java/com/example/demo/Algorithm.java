package com.example.demo;

import org.apache.commons.lang3.RandomStringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 一些算法的实现
 */

public class Algorithm {
    static HashMap<String, Integer>  map = new HashMap<String, Integer>(){
        {
            put("A", 14);
            put("2", 15);
            put("3", 3);
            put("4", 4);
            put("5", 5);
            put("6", 6);
            put("7", 7);
            put("8", 8);
            put("9", 9);
            put("10", 10);
            put("J", 11);
            put("Q", 12);
            put("K", 13);
            put("joker", 16);
            put("JOKER", 17);
        }
    };
    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {

            threeN(str);
        }

    }

    private static void threeN(String a){
        Integer x = Integer.valueOf(a);
        int i= 0;
        while (x != 1){
            i++;
            if(x%2 == 0){
                x = x/2;
            }else {
                x = (3*x + 1)/2;
            }

        }
        System.out.println(i);

    }

    private static void huoChe(String str){

    }

    private static void pukeMax(String str) {
        if (str.contains("joker JOKER")) {
            System.out.println("joker JOKER");
            return;
        }

        String[] aaa = str.split("-");
        String[] zuo = aaa[0].split(" ");
        String[] you = aaa[1].split(" ");

        int length1 = zuo.length;
        int length2 = you.length;


        if (length1 != length2 && length1 != 4 && length2 != 4) {
            System.out.println("ERROR");
            return;
        }else if(length1 != length2 && length1 == 4 && length2 != 4){
            System.out.println(aaa[0]);
            return;
        }else if(length1 != length2 && length1 != 4 && length2 == 4){
            System.out.println(aaa[1]);
            return;
        }
        if (map.get(zuo[0]) > map.get(you[0])) {
            System.out.println(aaa[0]);
            return;
        } else {
            System.out.println(aaa[1]);
        }
    }

    //梅花桩
    private static void HJ103(String[] arr, int len) {
        int[] ints = new int[len];
        for (int i = 0; i < arr.length; i++) {
            ints[i] = Integer.parseInt(arr[i]);
        }
        count(ints);
    }

    private static void count(int[] intArr) {

        int[] k = new int[intArr.length];
        for (int j = 1; j < intArr.length; j++) {
            for (int i = 0; i < j; i++) {
                if (intArr[i] < intArr[j]) {
                    k[j] = Math.max(k[j], k[i] + 1);
                }
            }
        }
        Arrays.sort(k);
        System.out.println(k[k.length - 1] + 1);
    }

    //字符串排序
    private static String sort(String str) {
        List<Character> letter = new ArrayList<Character>();
        for (char c : str.toCharArray()) {
            if (Character.isLetter(c)) {
                letter.add(c);
            }

        }
        letter.sort(new Comparator<Character>() {
            public int compare(Character o1, Character o2) {
                return Character.toLowerCase(o1) - Character.toLowerCase(o2);
            }
        });
        StringBuilder res = new StringBuilder();
        for (int i = 0, j = 0; i < str.length(); i++) {
            if (Character.isLetter(str.charAt(i))) {
                res.append(letter.get(j++));
            } else {
                res.append(str.charAt(i));
            }
        }
        return res.toString();
    }

    //质数因子
    private static void zhishu(long num) {

        StringBuilder sb = new StringBuilder();
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                sb.append(i).append(" ");
                num = num / i;
                i--;
            }
        }
        sb.append(num).append(" ");
        System.out.println(sb.toString());

    }

    //浮点近似值
    private static void jinsizhi(float a) {
        Float f = new Float(a);
        int i = f.intValue();
        if (f - i >= 0.5) {
            System.out.println(i + 1);
        } else {
            System.out.println(i);
        }
    }

    private static void tanxin(char[] chars) {
        int[] num = new int[26];
        for (int i = 0; i < num.length; i++) {
            int a = i + 97;
            for (int i1 = 0; i1 < chars.length; i1++) {
                if (chars[i1] == a) {
                    num[i]++;
                }
            }
        }
        Arrays.sort(num);
        int sum = 0;
        for (int i = 0; i < num.length; i++) {
            sum = sum + (i + 1) * num[i];

        }
        System.out.println(sum);
    }

    private static void lifang(double num) {
        double rnum = num < 0 ? num * -1 : num;

        double f = 0;
        while (f * f * f < rnum) {
            f++;
        }

        if ((f * f * f) == rnum) {
            System.out.println(num > 0 ? f : f * -1);
            return;
        }
        double b = f - 1;
        double mid = b + (f - b) / 2;
        double mul = mid * mid * mid;

        while (f - b > 0.1) {
            if (mul > rnum) {
                f = mid;

            } else if (mul < rnum) {
                b = mid;
            }
            mid = b + (f - b) / 2;
            mul = mid * mid * mid;
        }
        if (num < 0) {
            mid = -mid;
        }
        System.out.println(String.format("%.1f", mid));
    }

    private static void zuobiao(String[] strings) {
        Map<String, Long> map = new HashMap<>(4);
        String rex = "[ADWS]\\d{1}\\d?";
        for (int i = 0; i < strings.length; i++) {
            if (strings[i].matches(rex)) {
                String key = strings[i].substring(0, 1);
                if (map.get(key) == null) {
                    map.put(key, Long.parseLong(strings[i].substring(1)));
                } else {
                    map.put(key, map.get(key) + Long.parseLong(strings[i].substring(1)));
                }

            }
        }
        long x = map.get("D") - map.get("A");
        long y = map.get("W") - map.get("S");
        System.out.println(x + "," + y);

    }


}
