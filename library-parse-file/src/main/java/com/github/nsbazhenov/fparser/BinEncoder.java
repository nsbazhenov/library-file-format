package com.github.nsbazhenov.fparser;


import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * A class for encoding .bin files.
 *
 * @author Bazhenov Nikita
 */
class BinEncoder implements Encoder {

    static final BinEncoder INSTANCE = new BinEncoder();
    private static final char HEADER = 0x2526;

    private BinEncoder() {}

    /**
     * A method for encoding .bin files.
     */
    @Override
    public void encode(OutputStream outputStream, Document document) {
        try (DataOutputStream dataOutputStream = new DataOutputStream(outputStream)) {
            addRecords(dataOutputStream, document);
            dataOutputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method for serializing a document object.
     */
    private void addRecords(DataOutputStream dataOutputStream, Document document) {
        int recordsCount = document.getCars().size();

        try {
            dataOutputStream.writeChar(HEADER);
            dataOutputStream.writeInt(recordsCount);

            for (int i = 0; i < recordsCount; i++) {
                Car car = document.getCars().get(i);

                dataOutputStream.write(car.date().replaceAll("\\.", "").getBytes());

                int brandNameLength = car.brandName().length();
                byte[] brandNameLengthBytes = new byte[]{(byte) (brandNameLength & 0xFF), (byte) ((brandNameLength >> 8) & 0xFF)};
                dataOutputStream.write(brandNameLengthBytes);

                dataOutputStream.writeUTF(car.brandName());
                dataOutputStream.writeInt(car.price());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

