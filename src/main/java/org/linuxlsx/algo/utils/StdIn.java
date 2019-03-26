package org.linuxlsx.algo.utils;

import java.io.*;
import java.util.Scanner;

/**
 * User: niba
 * Date: 13-7-23
 * Time: 上午12:13
 */
public class StdIn {


    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static int readInt(){
        String input = readString();
        try {
            int result = Integer.parseInt(input);
            return result;
        }catch (Exception e){
            return 0;
        }

    }

    public static long readLong(){
        String input = readString();
        try {
            long result = Long.parseLong(input);
            return result;
        }catch (Exception e){
            return 0;
        }

    }

    public static String readString(){
        try {
            return reader.readLine();
        } catch (IOException e) {
            //Ignore
        }

        return "";
    }

    public static void main(String[] args) {
        System.out.println(readInt());

        System.out.println("-------");
        System.out.println(readLong());

        System.out.println("--------");

        System.out.println(readString());
    }
}
