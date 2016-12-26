package com.dismile.app.image;

import com.dismile.app.image.entity.ColorCriteria;
import com.dismile.app.image.entity.ImageAnalyzeCriteria;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Image processor
 */
public class ImageProcessor {

    public int process(File file, ImageAnalyzeCriteria analyzeProperties) throws IOException {

        BufferedImage image = ImageIO.read(file);

        // Начальная точка
        int x = analyzeProperties.getStartPoint().getX();
        int y = analyzeProperties.getStartPoint().getY();

        Color color;
        int pixelsCount = 0;

        // Анализируем пиксели вправо от начальной точки
        int xTemp = x;
        do {
            pixelsCount++;
            color = new Color(image.getRGB(xTemp, y));
            image.setRGB(xTemp, y, 0x000000);
            xTemp++;
        }
        while (isProperRgb(color, analyzeProperties));

        // Анализируем пиксели влево от начальной точки
        xTemp = x;
        do {
            xTemp--;
            pixelsCount++;
            color = new Color(image.getRGB(xTemp, y));
            image.setRGB(xTemp, y, 0x000000);
        } while (isProperRgb(color, analyzeProperties));

        return pixelsCount;
    }

    private boolean isProperRgb(Color color, ImageAnalyzeCriteria analyzeProperties) {
        return isProperRed(color.getRed(), analyzeProperties.getRedCriteria())
                && isProperGreen(color.getGreen(), analyzeProperties.getGreenCriteria())
                && isProperBlue(color.getBlue(), analyzeProperties.getBlueCriteria());
    }

    private boolean isProperRed(int color, ColorCriteria colorCriteria) {
        return color >= colorCriteria.getMinValue() && color <= colorCriteria.getMaxValue();
    }

    private boolean isProperGreen(int color, ColorCriteria colorCriteria) {
        return color >= colorCriteria.getMinValue() && color <= colorCriteria.getMaxValue();
    }

    private boolean isProperBlue(int color, ColorCriteria colorCriteria) {
        return color >= colorCriteria.getMinValue() && color <= colorCriteria.getMaxValue();
    }
}
