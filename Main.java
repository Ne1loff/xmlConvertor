package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();
        List<String> xmlCode = new ArrayList<>();
        while(!string.equals("")){
            xmlCode.add(string);
            string = scanner.nextLine();
        }
        Queue<String> jsonCode = new ArrayDeque<>();

        for (String s: xmlCode) {
            System.out.println(s);
        }
    }
}
