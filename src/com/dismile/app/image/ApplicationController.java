package com.dismile.app.image;

import com.dismile.app.image.entity.ColorCriteria;
import com.dismile.app.image.entity.ImageAnalyzeCriteria;
import com.dismile.app.image.entity.Point;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Application controller
 */
public class ApplicationController {

    private BufferedReader reader;
    private String filesFolderPath;
    private String fileMask;
    private Integer min;
    private Integer max;
    private Integer numbers;
    private String extension;
    private ImageProcessor imageProcessor;
    private String outFileName;
    private ImageAnalyzeCriteria analyzeProperties;

    public ApplicationController() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        imageProcessor = new ImageProcessor();
    }

    public void process() throws IOException {
        inputFolder();
        inputMask();
        inputMin();
        inputMax();
        inputNumbers();
        inputExtension();
        inputAnalyzeCriteria();
        inputOutFile();

        List<Integer> results = new LinkedList<>();

        System.out.println("\nResults:");
        for (int i = min; i <= max; i++) {
            File file = new File(formFilePath(i));
            int fileResult = imageProcessor.process(file, analyzeProperties);
            results.add(fileResult);
        }

        writeToFile(results);
        reader.readLine();
    }

    private void writeToFile(List<Integer> results) throws IOException {
        FileWriter writer = new FileWriter(filesFolderPath + outFileName);

        results.forEach((value) -> {
            System.out.println(value);
            try {
                writer.write(value.toString() + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        writer.flush();
    }

    private void inputOutFile() throws IOException {
        System.out.println("Введите имя файла с результатами:");
        outFileName = reader.readLine();
    }

    private void inputExtension() throws IOException {
        System.out.println("Введите расширение файлов (.jpg, .png):");
        extension = reader.readLine();
    }

    private void inputNumbers() throws IOException {
        System.out.println("Введите число числовых знаков:");
        numbers = inputInteger();
    }

    private void inputMax() throws IOException {
        System.out.println("Введите конец цифрового диапазона изменяющейся части файла:");
        max = inputInteger();
    }

    private void inputMin() throws IOException {
        System.out.println("Введите начало цифрового диапазона изменяющейся части файла:");
        min = inputInteger();
    }

    private void inputMask() throws IOException {
        System.out.println("Введите маску имени файла без изменяющейся части:");
        fileMask = reader.readLine();
    }

    private Integer inputInteger() throws IOException {
        try {
            String input = reader.readLine();
            return Integer.parseInt(input);
        } catch (NumberFormatException ex) {
            System.out.println("Это не число! Введите число.");
            return inputInteger();
        }
    }

    private void inputFolder() throws IOException {
        System.out.println("Введите путь к папке с изображениями: (D:\\SomeFolder\\)");
        filesFolderPath = reader.readLine();
        File folderPath = new File(filesFolderPath);
        while (!folderPath.isDirectory()) {
            System.out.println("Введенный путь не является папкой! Повторите еще раз.");
            filesFolderPath = reader.readLine();
            folderPath = new File(filesFolderPath);
        }
    }

    private void inputAnalyzeCriteria() throws IOException {
        analyzeProperties = new ImageAnalyzeCriteria();
        analyzeProperties.setStartPoint(inputPoint());
        analyzeProperties.setRedCriteria(inputColorCriteria("red"));
        analyzeProperties.setGreenCriteria(inputColorCriteria("green"));
        analyzeProperties.setBlueCriteria(inputColorCriteria("blue"));
    }

    private Point inputPoint() throws IOException {
        System.out.println("Введите координату x начальной точки");
        Integer x = inputInteger();
        System.out.println("Введите координату y начальной точки");
        Integer y = inputInteger();
        return new Point(x, y);
    }

    private ColorCriteria inputColorCriteria(String colorName) throws IOException {
        System.out.println("Введите минимальное значение " + colorName + " (0>=value>=255):");
        Integer min = inputInteger();
        System.out.println("Введите максимальное значение " + colorName + " (0>=value>=255):");
        Integer max = inputInteger();
        return new ColorCriteria(min, max);
    }

    private String formFilePath(int number) {
        String path = filesFolderPath + fileMask;
        String numberString = String.format("%0" + numbers + "d", number);
        path += numberString + extension;
        return path;
    }
}
