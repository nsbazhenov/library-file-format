package com.github.nsbazhenov.fparser;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * A class for decoding .bin files.
 *
 * @author Bazhenov Nikita
 */
class BinDecoder implements Decoder {

    static final BinDecoder INSTANCE = new BinDecoder();

    private BinDecoder() {}

    /**
     * A method for decoding .bin files.
     */
    @Override
    public Document decode(InputStream inputStream) {
        List<Car> cars = new ArrayList<>();
        Document document = new Document();
        document.setCars(cars);

        try (DataInputStream dataInputStream = new DataInputStream(inputStream)) {
            dataInputStream.skipBytes(2);

            int recordsCount = dataInputStream.readInt();

            for (int i = 0; i < recordsCount; i++) {
                cars.add(getRecordsCar(dataInputStream));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return document;
    }

    /**
     * Method for deserializing the document object.
     */
    private static Car getRecordsCar(DataInputStream dataInputStream) {
        try {
            byte[] dateBytes = new byte[8];
            dataInputStream.read(dateBytes, 0, 8);
            String data = dateFormatCorrection(new String(dateBytes, StandardCharsets.UTF_8));

            byte[] brandNameLengthBytes = new byte[2];
            dataInputStream.read(brandNameLengthBytes, 0, 2);
            int brandNameLength = ((brandNameLengthBytes[1] & 0xff) << 8) | (brandNameLengthBytes[0] & 0xff);

            String brandName = dataInputStream.readUTF();
            if (brandNameLength == 0 && brandName.isEmpty()) {
                throw new IllegalStateException("Brand Name has a negative value");
            }

            int price = dataInputStream.readInt();
            if (price < 0) {
                throw new IllegalStateException("Price is a negative value");
            }

            return new Car(data, brandName, price);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method to convert the date to the correct format.
     */
    private static String dateFormatCorrection(String dateStr) {
        DateTimeFormatter invalidFormatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        DateTimeFormatter correctFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

        return LocalDate.parse(dateStr, invalidFormatter).format(correctFormatter);
    }
}
