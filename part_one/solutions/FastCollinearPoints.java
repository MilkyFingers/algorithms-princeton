/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import java.util.ArrayList;
import java.util.Arrays;

public class FastCollinearPoints {

    private ArrayList<LineSegment> segments = new ArrayList<LineSegment>();

    public FastCollinearPoints(Point[] points) {
        Point[] newPoints = points.clone();
        if (duplicateOrNullPoints(newPoints)) {
            throw new IllegalArgumentException("no no no!");
        }
        Arrays.sort(newPoints);
        for (int i = 0; i < newPoints.length - 3; i++) {
            Arrays.sort(newPoints);
            Arrays.sort(newPoints, newPoints[i].slopeOrder());

            for (int p = 0, first = 1, last = 2; last < newPoints.length; last++) {
                while (last < newPoints.length
                        && Double.compare(newPoints[p].slopeTo(newPoints[first]),
                                          newPoints[p].slopeTo(newPoints[last])) == 0) {
                    last++;
                }
                if (last - first >= 3 && newPoints[p].compareTo(newPoints[first]) < 0) {
                    segments.add(new LineSegment(newPoints[p], newPoints[last - 1]));
                }
                first = last;
            }
        }
    }

    public int numberOfSegments() {
        return segments.size();
    }

    public LineSegment[] segments() {
        LineSegment[] segment = segments.toArray(new LineSegment[segments.size()]);
        return segment;
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
