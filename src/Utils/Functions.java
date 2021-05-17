package Utils;

public class Functions {

    /**
     * Returns an array of integers representing the positions of points
     * along a line evenly spaced out
     * @param lineWidth the width of the whole line
     * @param numPoints the number of points to space on the line
     * @param includeLeft should the first point be 0?
     * @param includeRight should the last point be lineWidth?
     */
    public static float[] getPointsOnLine(float lineWidth, int numPoints, boolean includeLeft, boolean includeRight) {

        // make sure the line width and num points are within the right amount
        assert lineWidth > 0;
        assert numPoints > (includeLeft ? 1 : 0) + (includeRight ? 1 : 0);

        // array of positions equal to the number of points on the line
        float[] positions = new float[numPoints];

        // get all of the center points
        if (!includeLeft && !includeRight) {
            for (int i = 0; i < numPoints; i++)
                positions[i] = lineWidth * (i + 1) / (numPoints + 1);
        }

        // get the center points along with 0
        else if (includeLeft && !includeRight) {
            for (int i = 0; i < numPoints; i++)
                positions[i] = lineWidth * i / numPoints;
        }

        // get the center points along with lineWidth
        else if (!includeLeft) {
            for (int i = 0; i < numPoints; i++)
                positions[i] = lineWidth * (i + 1) / numPoints;
        }

        // get all of the points including the edges
        else {
            for (int i = 0; i < numPoints; i++)
                positions[i] = lineWidth * i / (numPoints - 1);
        }

        // return the positions
        return positions;
    }

    /**
     * Returns an array of integers representing the positions of the left side of
     * lines along a larger line evenly spaced out
     * @param lineWidth the width of the whole line
     * @param numPoints the number of points to space on the line
     * @param sublineWidth the width of the lines within the larger lines
     * @param includeLeft should the first point be 0?
     * @param includeRight should the last point be lineWidth?
     */
    public static float[] getSublinePoints(float lineWidth, int numPoints, float sublineWidth,
                                      boolean includeLeft, boolean includeRight) {

        // get the positions of the left points
        float[] positions = getPointsOnLine(lineWidth, numPoints, includeLeft, includeRight);

        // if neither are included, move them left by half the subline width
        if (!includeLeft && !includeRight) {
            for (int i = 0; i < numPoints; i++)
                positions[i] -= sublineWidth / 2;
        }

        // if both are included, make the points shift slightly closer to the left
        else if (includeLeft && includeRight) {
            for (int i = 0; i < numPoints; i++)
                positions[i] *= lineWidth / (lineWidth + sublineWidth);
        }

        // if only the right side is included, move everything left by the subline width
        else if (!includeLeft) {
            for (int i = 0; i < numPoints; i++)
                positions[i] -= sublineWidth;
        }

        // return the positions
        return positions;
    }

    /**
     * Returns an array of integers representing the positions of the left side of
     * lines along a larger line touching each other
     * @param lineWidth the width of the whole line
     * @param numPoints the number of points to space on the line
     * @param sublineWidth the width of the lines within the larger lines
     * @param includeLeft should the first point be 0?
     * @param includeRight should the last point be lineWidth?
     */
    public static float[] getTouchingSublines(float lineWidth, int numPoints, float sublineWidth,
                                               boolean includeLeft, boolean includeRight) {

        // create the array of positions
        float[] positions = new float[numPoints];

        // keep the lines in the center
        if (!includeLeft && !includeRight) {
            // find the margin on the left for all the points
            float marginLeft = (lineWidth - numPoints * sublineWidth) / 2;
            for (int i = 0; i < numPoints; i++)
                positions[i] = marginLeft + i * sublineWidth;
        }

        // keep the lines on the left side
        else if (includeLeft) {
            for (int i = 0; i < numPoints; i++)
                positions[i] = i * sublineWidth;
        }

        // keep the lines on the right side
        else {
            for (int i = 0; i < numPoints; i++)
                positions[i] = lineWidth - i * sublineWidth;
        }

        // return the positions
        return positions;
    }

}