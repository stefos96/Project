package Positions;

public class Positions {
    private double xPosition;
    private double yPosition;

    public Positions(double x, double y) {
        xPosition = x;
        yPosition = y;
    }

    public void updatePositions(double x, double y) {
        xPosition = x;
        yPosition = y;
    }
}
