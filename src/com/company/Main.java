package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String string = scanner.nextLine();

        Map<String, Boolean> keyWords = new HashMap<>();
        ArrayList<String> xmlCode = new ArrayList<>();
        xmlCode.add("{");
        while (!string.equals("")) {
            string = String.format("  %s", string);
            if (!string.contains("?xml version")) {

                if (string.contains("id=\"")) {
                    String word = string.substring(1, string.indexOf("id") - 1).replace("<", "").trim();
                    if (!keyWords.containsKey(word)) {
                        keyWords.put(word, true);
                        xmlCode.add(string.substring(0, string.indexOf('<'))
                                + "\"" + word
                                + "\":{");
                    }
                    xmlCode.add(string.substring(0, string.indexOf('<')) + "{");
                    string = "  " + string.substring(0, string.indexOf('<')) + "\"-id\":" + string.substring(string.indexOf('=') + 1, string.length() - 1) + ",";
                }
                if (string.indexOf('>') == string.length() - 1 && (string.contains("<") || string.contains("</")) && !string.contains(".</")) {
                    String word;
                    String word2 = string.substring(0, string.indexOf('<'));
                    if (string.contains("</"))
                        word = string.substring(1, string.length() - 1).replace("</", "").trim();
                    else word = string.substring(1, string.length() - 1).replace("<", "").trim();
                    if (!keyWords.containsKey(word)) {
                        keyWords.put(word, true);
                        string = string.substring(0, string.indexOf('<')) + "\"" + word + "\":{";
                    } else if (!string.contains("</")) string = word2 + "{";
                    else string = word2 + "},";

                } else if ((string.contains("<") && string.contains(">") || (string.contains("</") && string.contains(">")))) {
                    if (string.contains(">") && string.contains("</")) {
                        string = string.replace(">", "\":\"").replace("</", "\",").replace("<", "\"");
                        int q = string.indexOf("\",");
                        string = string.substring(0, q + 2);
                    } else if (string.contains("<") && string.contains(">")) {
                        string = string.replace("<", "\"").replace(">", "\":\"");
                    }
                }
                xmlCode.add(string);
            }
            string = scanner.nextLine();
        }
        xmlCode.add("}");
        for (int s = 0; s < xmlCode.size() - 1; s++) {
            String q1 = xmlCode.get(s);
            String q2 = xmlCode.get(s + 1);
            if (q2.contains("}")) {
                q1 = q1.substring(0, q1.length() - 1);
                xmlCode.remove(s);
                xmlCode.add(s, q1);
            }
        }
        for (String s : xmlCode) {
            System.out.println(s);
        }
    }
}
