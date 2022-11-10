package com.github.nsbazhenov.fparser;

import java.io.InputStream;

/**
 * Interface for decoding files.
 *
 * @author Bazhenov Nikita
 */
public interface Decoder {

    Document decode(InputStream inputStream);

}
