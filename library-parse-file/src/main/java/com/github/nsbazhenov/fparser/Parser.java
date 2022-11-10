package com.github.nsbazhenov.fparser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

/**
 * A class for creating a parser.
 *
 * @author Bazhenov Nikita
 */
public class Parser {

    private final Encoder encoder;
    private final Decoder decoder;

    private Parser(Encoder encoder, Decoder decoder) {
        this.encoder = encoder;
        this.decoder = decoder;
    }

    public Document readValue(File src) throws FileNotFoundException {
        return decoder.decode(new FileInputStream(src));
    }

    public void writeValue(File resultFile, Document document) throws FileNotFoundException {
        encoder.encode(new FileOutputStream(resultFile), document);
    }

    public static class Builder {
        Encoder encoder;
        Decoder decoder;

        private Builder() {
        }

        public Builder withEncoder(Encoder encoder) {
            this.encoder = encoder;
            return this;
        }

        public Builder withDecoder(Decoder decoder) {
            this.decoder = decoder;
            return this;
        }

        public Builder withXmlEncoder() {
            this.encoder = XmlEncoder.INSTANCE;
            return this;
        }

        public Builder withXmlDecoder() {
            this.decoder = XmlDecoder.INSTANCE;
            return this;
        }

        public Builder withBinEncoder() {
            this.encoder = BinEncoder.INSTANCE;
            return this;
        }

        public Builder withBinDecoder() {
            this.decoder = BinDecoder.INSTANCE;
            return this;
        }

        public Parser build(){
            return new Parser(encoder, decoder);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
