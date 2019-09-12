/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novaes.contacts;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 *
 * @author kbos
 */
public class Solution {

    /*
     * Complete the contacts function below.
     */
    static int[] contacts(String[][] queries) {
        List<Integer> result = new ArrayList<>();
        Map<String, Integer> contacts = new HashMap<>();
        for (String[] query : queries) {
            String command = query[0];
            String value = query[1];
            if(command.equals("add")) {
                partition(value).forEach(s -> {
                    if(contacts.containsKey(s)) {
                        contacts.put(s, contacts.get(s) + 1);
                    } else {
                        contacts.put(s,1);
                    }
                });
            } else if(command.equals("find")) {
                Integer q = contacts.get(value);
                result.add(q != null ? q : 0);
            }
        }
        return result.stream().mapToInt(Integer::intValue).toArray();
    }
    
    static List<String> partition(String s) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            sb.append(c+"");
            result.add(sb.toString());
        }
        return result;
    }
    
    
        
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new PrintWriter(System.out));

        int queriesRows = Integer.parseInt(scanner.nextLine().trim());

        String[][] queries = new String[queriesRows][2];

        for (int queriesRowItr = 0; queriesRowItr < queriesRows; queriesRowItr++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");

            for (int queriesColumnItr = 0; queriesColumnItr < 2; queriesColumnItr++) {
                String queriesItem = queriesRowItems[queriesColumnItr];
                queries[queriesRowItr][queriesColumnItr] = queriesItem;
            }
        }

        int[] result = contacts(queries);

        for (int resultItr = 0; resultItr < result.length; resultItr++) {
            bufferedWriter.write(String.valueOf(result[resultItr]));

            if (resultItr != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();
    }
}
