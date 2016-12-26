package com.dismile.app.image.entity;

/**
 * Image Analyze Criteria
 */
public class ImageAnalyzeCriteria {
    private Point startPoint;
    private ColorCriteria redCriteria;
    private ColorCriteria greenCriteria;
    private ColorCriteria blueCriteria;

    public ImageAnalyzeCriteria() {
    }

    public ImageAnalyzeCriteria(Point startPoint, ColorCriteria redCriteria, ColorCriteria greenCriteria, ColorCriteria blueCriteria) {
        this.startPoint = startPoint;
        this.redCriteria = redCriteria;
        this.greenCriteria = greenCriteria;
        this.blueCriteria = blueCriteria;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(Point startPoint) {
        this.startPoint = startPoint;
    }

    public ColorCriteria getRedCriteria() {
        return redCriteria;
    }

    public void setRedCriteria(ColorCriteria redCriteria) {
        this.redCriteria = redCriteria;
    }

    public ColorCriteria getGreenCriteria() {
        return greenCriteria;
    }

    public void setGreenCriteria(ColorCriteria greenCriteria) {
        this.greenCriteria = greenCriteria;
    }

    public ColorCriteria getBlueCriteria() {
        return blueCriteria;
    }

    public void setBlueCriteria(ColorCriteria blueCriteria) {
        this.blueCriteria = blueCriteria;
    }
}
