package com.payerNews;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import static com.payerNews.CSVUtils.parseLine;

public class FilesCreation {

    public static void main(String[] args) throws IOException {
        noPayersSpacy();
        sameNumberDifferentPayers();
        differentNumberOfPayers();
        samePayersInBothSystems();
    }

    private static void noPayersSpacy() throws IOException {
        String csvFile = "resources/articles.csv";
        Scanner scanner = new Scanner(new File(csvFile));
        List<List<String>> rows = new ArrayList<>();
        while (scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine());
            if (line.get(1).equals("0")) {
                List<String> articleData = Collections.singletonList(
                        appendQuotes(line.get(0)) + ", " + appendQuotes(line.get(1)) + ", "
                                + appendQuotes(line.get(2)) + ", " + appendQuotes(line.get(3)) + ", "
                                + appendQuotes(line.get(4)) + ", " + appendQuotes(line.get(5))
                );
                rows.add(articleData);
            }
        }
        scanner.close();
        writeToCSV("resources/noPayersSpacyArticles.csv", rows);
    }

    private static void differentNumberOfPayers() throws IOException {
        String csvFile = "resources/articles.csv";
        Scanner scanner = new Scanner(new File(csvFile));
        List<List<String>> rows = new ArrayList<>();
        while (scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine());
            if (!line.get(1).equals(line.get(3)) && !line.get(1).equals("0")) {
                List<String> articleData = Collections.singletonList(
                        appendQuotes(line.get(0)) + ", " + appendQuotes(line.get(1)) + ", "
                                + appendQuotes(line.get(2)) + ", " + appendQuotes(line.get(3)) + ", "
                                + appendQuotes(line.get(4)) + ", " + appendQuotes(line.get(5))
                );
                rows.add(articleData);
            }
        }
        scanner.close();
        writeToCSV("resources/differentNumberOfPayersArticles.csv", rows);
    }

    private static void samePayersInBothSystems() throws IOException {
        String csvFile = "resources/articles.csv";
        Scanner scanner = new Scanner(new File(csvFile));
        List<List<String>> rows = new ArrayList<>();
        while (scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine());
            if (sortString(line.get(0)).equals(sortString(line.get(2))) && line.get(1).equals(line.get(3))) {
                List<String> articleData = Collections.singletonList(
                        appendQuotes(line.get(0)) + ", " + appendQuotes(line.get(1)) + ", "
                                + appendQuotes(line.get(2)) + ", " + appendQuotes(line.get(3)) + ", "
                                + appendQuotes(line.get(4)) + ", " + appendQuotes(line.get(5))
                );
                rows.add(articleData);
            }
        }
        scanner.close();
        writeToCSV("resources/samePayersInBothArticles.csv", rows);
    }

    private static void sameNumberDifferentPayers() throws IOException {
        String csvFile = "resources/articles.csv";
        Scanner scanner = new Scanner(new File(csvFile));
        List<List<String>> rows = new ArrayList<>();
        while (scanner.hasNext()) {
            List<String> line = parseLine(scanner.nextLine());
            if (!sortString(line.get(0)).equals(sortString(line.get(2))) && line.get(1).equals(line.get(3))) {
                List<String> articleData = Collections.singletonList(
                        appendQuotes(line.get(0)) + ", " + appendQuotes(line.get(1)) + ", "
                                + appendQuotes(line.get(2)) + ", " + appendQuotes(line.get(3)) + ", "
                                + appendQuotes(line.get(4)) + ", " + appendQuotes(line.get(5))
                );
                rows.add(articleData);
            }
        }
        scanner.close();
        writeToCSV("resources/sameNumberDifferentPayersArticles.csv", rows);
    }

    private static String appendQuotes(String string) {
        return "\"" + string + "\"";
    }

    private static String sortString(String inputString) {
        char tempArray[] = inputString.toCharArray();
        Arrays.sort(tempArray);
        return new String(tempArray);
    }

    private static void writeToCSV(String filePath, List<List<String>> rows) throws IOException {
        FileWriter csvWriter = new FileWriter(filePath);
        csvWriter.append("Spacy Payers");
        csvWriter.append(",");
        csvWriter.append("Count");
        csvWriter.append(",");
        csvWriter.append("Medix Payers");
        csvWriter.append(",");
        csvWriter.append("Count");
        csvWriter.append(",");
        csvWriter.append("Title");
        csvWriter.append(",");
        csvWriter.append("Text");
        csvWriter.append("\n");
        for (List<String> rowData : rows) {
            csvWriter.append(String.join(",", rowData));
            csvWriter.append("\n");
        }
        csvWriter.flush();
        csvWriter.close();
    }

}






