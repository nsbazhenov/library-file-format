package com.github.nsbazhenov.fparser;

import java.io.OutputStream;

/**
 * Interface for encoding files.
 *
 * @author Bazhenov Nikita
 */
public interface Encoder {

    void encode(OutputStream outputStream, Document document);

}
