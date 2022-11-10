package com.github.nsbazhenov.fparser;

import org.junit.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class BinParseTest {
    private static final String PATH_NAME = "src/test/resources/cars.bin";

    private final Parser binParser = Parser.builder()
            .withBinDecoder()
            .withBinEncoder()
            .build();

    @After
    public void runAfterTestMethod() {
        File file = new File(PATH_NAME);
        file.delete();
    }

    @Test
    public void whenGettingAnObjectToSave_givenBinFile_theExpectedFileShouldBeReturned() throws FileNotFoundException {
        Car expectedFirstCar = new Car("12.08.2018", "Toyota Chaser", 10000);
        Car expectedSecondCar = new Car("10.01.2022", "Toyota Mark", 15000);

        List<Car> cars = new ArrayList<>();
        cars.add(expectedFirstCar);
        cars.add(expectedSecondCar);

        Document document = new Document();
        document.setCars(cars);

        binParser.writeValue(new File(PATH_NAME), document);
        Document binDocument = binParser.readValue(new File(PATH_NAME));

        Car actualFirstCar = binDocument.getCars().get(0);
        Car actualSecondCar = binDocument.getCars().get(1);

        Assert.assertEquals(2, binDocument.getCars().size());

        Assert.assertEquals(expectedFirstCar.date(), actualFirstCar.date());
        Assert.assertEquals(expectedFirstCar.brandName(), actualFirstCar.brandName());
        Assert.assertEquals(expectedFirstCar.price(), actualFirstCar.price());

        Assert.assertEquals(expectedSecondCar.date(), actualSecondCar.date());
        Assert.assertEquals(expectedSecondCar.brandName(), actualSecondCar.brandName());
        Assert.assertEquals(expectedSecondCar.price(), actualSecondCar.price());
    }

    @Test(expected = DateTimeParseException.class)
    public void whenReadingAnBinFile_givenObjectInvalidDateField_thenShouldThrowException() throws FileNotFoundException {
        Car expectedFirstCar = new Car("123.08.2018", "Toyota Chaser", 10000);
        Car expectedSecondCar = new Car("12.123.2022", "Toyota Mark", 15000);

        List<Car> cars = new ArrayList<>();
        cars.add(expectedFirstCar);
        cars.add(expectedSecondCar);

        Document document = new Document();
        document.setCars(cars);

        binParser.writeValue(new File(PATH_NAME), document);
        binParser.readValue(new File(PATH_NAME));
    }

    @Test(expected = IllegalStateException.class)
    public void whenReadingAnBinFile_givenObjectInvalidPriceField_thenShouldThrowException() throws FileNotFoundException {
        Car expectedFirstCar = new Car("12.08.2018", "Toyota Chaser", -10000);
        Car expectedSecondCar = new Car("12.12.2022", "Toyota Mark", -15000);

        List<Car> cars = new ArrayList<>();
        cars.add(expectedFirstCar);
        cars.add(expectedSecondCar);

        Document document = new Document();
        document.setCars(cars);

        binParser.writeValue(new File(PATH_NAME), document);
        binParser.readValue(new File(PATH_NAME));
    }

    @Test(expected = IllegalStateException.class)
    public void whenReadingAnBinFile_givenObjectInvalidNameBrandField_thenShouldThrowException() throws FileNotFoundException {
        Car expectedFirstCar = new Car("12.08.2018", "", 10000);
        Car expectedSecondCar = new Car("12.12.2022", "", 15000);

        List<Car> cars = new ArrayList<>();
        cars.add(expectedFirstCar);
        cars.add(expectedSecondCar);

        Document document = new Document();
        document.setCars(cars);

        binParser.writeValue(new File(PATH_NAME), document);
        binParser.readValue(new File(PATH_NAME));
    }

    @Test(expected = FileNotFoundException.class)
    public void whenReadingAnBinFile_givenObject_thenShouldThrowException() throws FileNotFoundException {
        binParser.readValue(new File(PATH_NAME));
    }
}
