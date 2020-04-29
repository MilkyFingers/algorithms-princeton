/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {
        Point[] newPoints = points.clone();
        if (duplicateOrNullPoints(newPoints)) {
            throw new IllegalArgumentException("no no no!");
        }
        int length = newPoints.length;
        ArrayList<LineSegment> ls = new ArrayList<LineSegment>(0);
        for (int i = 0; i < length - 3; i++) {
            for (int j = i + 1; j < length - 2; j++) {
                for (int n = j + 1; n < length - 1; n++) {
                    if (newPoints[i].slopeTo(newPoints[j]) != newPoints[j].slopeTo(newPoints[n])) {
                        continue;
                    }
                    else {
                        for (int z = n + 1; z < length; z++) {
                            if (newPoints[n].slopeTo(newPoints[z]) == newPoints[j]
                                    .slopeTo(newPoints[n])) {
                                LineSegment l = new LineSegment(newPoints[i], newPoints[z]);
                                ls.add(l);
                            }
                        }
                    }
                }
            }
        }
        segments = ls.toArray(new LineSegment[ls.size()]);
    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments.clone();
    }

    // Test client
    public static void main(String[] args) {

    }

    /*
    PRIVATE METHODS
     */

    private boolean duplicateOrNullPoints(Point[] points) {
        if (points == null) {
            return true;
        }
        Arrays.sort(points);
        if (points[points.length - 1] == null) {
            return true;
        }
        for (int i = 0; i < points.length - 1; i++) {
            if (points[i] == null || points[i].compareTo(points[i + 1]) == 0) {
                return true;
            }
        }
        return false;
    }
}
