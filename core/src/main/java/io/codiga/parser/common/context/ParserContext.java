package io.codiga.parser.common.context;


import io.codiga.model.common.Position;

/**
 * Represent the data and method you need for parsing.
 */
public interface ParserContext {

    public Position getStartPosition();

    public Position getEndPosition();

    public int getStartIndex();

    public int getEndIndex();
}
