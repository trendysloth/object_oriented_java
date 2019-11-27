import edu.duke.*;

public class PerimeterRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        
        int numOfPoints = getNumPoints(s);
        System.out.println("Number of points is " + numOfPoints);
        
        double averageLength = getAverageLength(s);
        System.out.println("Average side length is " + averageLength);
        
        double largestSide = getLargestSide(s);
        System.out.println("The longest side is " + largestSide);
        
        double largestX = getLargestX(s);
        System.out.println("The largest X is " + largestX);
    }

    public static void main (String[] args) {
        PerimeterRunner pr = new PerimeterRunner();
        pr.testPerimeter();
    }
    
    public int getNumPoints(Shape s) {
        int numOfPoints = 0;
        for (Point currPt : s.getPoints()) {
            numOfPoints = numOfPoints + 1;
        }
        return numOfPoints;
    }
    
    public double getAverageLength (Shape s) {
        double totalLength = getPerimeter(s);
        int numOfSides = getNumPoints(s);
        double averageLength = totalLength / numOfSides;
        return averageLength;
    }
    
    public double getLargestSide (Shape s) {
        double largestSide = 0.0;
        Point prevPt = s.getLastPoint();
        for (Point currPt : s.getPoints()) {
            double currSide = prevPt.distance(currPt);
            if (currSide > largestSide) {
                largestSide = currSide;
            }
        }
        return largestSide;
    }
    
    public double getLargestX (Shape s) {
        double largestX = Double.NEGATIVE_INFINITY;
        double currX;
        for (Point currPt : s.getPoints()) {
            currX = currPt.getX();
            if (currX > largestX) {
                largestX = currX;
            }
        }
        return largestX;
    }
}
