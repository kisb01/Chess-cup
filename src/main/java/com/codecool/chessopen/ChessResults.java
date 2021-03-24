package com.codecool.chessopen;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChessResults {

    public List<String> getCompetitorsNamesFromFile(String fileName) {
        List<String> results = new ArrayList<>();
        String line;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                results.add(line);
            }
        } catch (IOException e) {
            System.out.println("File not found!");
        }
        List<String> nameAndSum = getSumResults(results);
        sortOrder(nameAndSum);
        justNames(nameAndSum);
        return nameAndSum;
    }

    private List<String> getSumResults(List<String> results) {
        List<String> nameAndSum = new ArrayList<>();
        for (String result : results) {
            String[] inLine = result.split(",");
            int sum = Integer.parseInt(inLine[1]) +
                    Integer.parseInt(inLine[2]) +
                    Integer.parseInt(inLine[3]) +
                    Integer.parseInt(inLine[4]) +
                    Integer.parseInt(inLine[5]);
            nameAndSum.add(inLine[0] + sum);
        }
        return nameAndSum;
    }

    private void sortOrder(List<String> results) {
        for (int i = 0; i < results.size(); i++) {
            for (int j = 0; j < results.size() - 1; j++) {
                Integer num1 = Integer.parseInt(results.get(j).replaceAll("[^0-9]", ""));
                Integer num2 = Integer.parseInt(results.get(j + 1).replaceAll("[^0-9]", ""));
                if (num1 < num2) {
                    String temp = results.get(j + 1);
                    results.set(j + 1, results.get(j));
                    results.set(j, temp);
                }
            }
        }
    }

    private void justNames(List<String> results) {
        for (int i = 0; i < results.size(); i++) {
            String justName = results.get(i).replaceAll("[0-9,]", "");
            results.set(i, justName);
        }
    }

}
