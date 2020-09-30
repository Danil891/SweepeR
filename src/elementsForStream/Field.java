package elementsForStream;

public class Field {
    private Box[][] matrix;

    Field(Box defaultBox) {
        matrix = new Box[FieldsFiller.getSize().x][FieldsFiller.getSize().y];
        for (Coordinates coord : FieldsFiller.getAllCoords()) matrix[coord.x][coord.y] = defaultBox;
    }

    Box get(Coordinates coord) {
        if (FieldsFiller.inRange(coord)) return matrix[coord.x][coord.y];
        return null;
    }

    void set(Coordinates coord, Box box) {
        if (FieldsFiller.inRange(coord)) matrix[coord.x][coord.y] = box;
    }
}
