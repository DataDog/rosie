package io.codiga.utils;

import io.codiga.model.common.Position;

import java.util.*;

/**
 * Find a Position in the code for a given offset.
 * This is used for the Pattern and TreeSitter parser to get the position of an element according to their
 * offset in the code.
 */
public class PositionFinder {
    private final List<String> codeLines;
    private final Map<Integer, Position> foundPosition;

    public PositionFinder(String code) {
        this.codeLines = new ArrayList<>();
        this.foundPosition = new HashMap<>();

        Scanner scanner = new Scanner(code);

        while (scanner.hasNextLine()) {
            this.codeLines.add(scanner.nextLine());
        }
        scanner.close();
    }

    public Position getCodePosition(int index) {
        // TODO - check why this is failing
//        if (this.foundPosition.containsKey(index)) {
//            return this.foundPosition.get(index);
//        }
        int lineNumber = 1;
        for (String line : codeLines) {
            if (index <= line.length() + 1) {
                Position position = new Position(lineNumber, index);
                this.foundPosition.put(index, position);
                return position;
            }
            index = index - line.length() - 1;
            lineNumber = lineNumber + 1;
        }
        return null;
    }
}
