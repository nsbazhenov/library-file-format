package com.github.nsbazhenov.fparser;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * A class for decoding .xml files.
 *
 * @author Bazhenov Nikita
 */
class XmlDecoder implements Decoder {

    static final XmlDecoder INSTANCE = new XmlDecoder();

    private final XmlMapper xmlMapper = new XmlMapper();

    private XmlDecoder() {}

    /**
     *  A method for decoding .xml files.
     */
    @Override
    public Document decode(InputStream inputStream) {
        try {
            return xmlMapper.readValue(inputStream, Document.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
