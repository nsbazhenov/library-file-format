package com.github.nsbazhenov.fparser;


import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.io.OutputStream;

/**
 * A class for encoding .xml files.
 *
 * @author Bazhenov Nikita
 */
class XmlEncoder implements Encoder {

    static final XmlEncoder INSTANCE = new XmlEncoder();

    private final XmlMapper xmlMapper = new XmlMapper();

    private XmlEncoder() {}

    /**
     * Method for serializing a document object.
     */
    @Override
    public void encode(OutputStream outputStream, Document document) {
        try {
            xmlMapper.writeValue(outputStream, document);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
