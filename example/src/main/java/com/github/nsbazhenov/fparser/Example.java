package com.github.nsbazhenov.fparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 * An example of using the library.
 *
 * @author Bazhenov Nikita
 */
public class Example {
    public static void main(String[] args) throws FileNotFoundException {
        Car firstCar = new Car("12.08.2018","Toyota Chaser", 10000);
        Car secondCar = new Car("10.01.2022","Toyota Mark", 15000);
        Car oldThirdCar = new Car("19.11.2010", "Tesla", 15000);
        Car newThirdCar = new Car ("19.11.2010", "Tesla", 10000);

        List<Car> cars = new ArrayList<>();
        cars.add(firstCar);
        cars.add(secondCar);

        Document document = new Document();
        document.setCars(cars);

        System.out.println(xmlFile(document));
        System.out.println(binFile(document));
        addCar(document, oldThirdCar);
        System.out.println(xmlFile(document));
        editCar(document, oldThirdCar, newThirdCar);
        System.out.println(xmlFile(document));
        deleteCar(document, newThirdCar);
        System.out.println(xmlFile(document));
    }

    /**
     * Method for creating an .xml file from an object and reading an xml file.
     */
    public static Document xmlFile(Document document) throws FileNotFoundException {
        Parser xmlParser = Parser.builder()
                .withXmlDecoder()
                .withXmlEncoder()
                .build();

        xmlParser.writeValue(new File("example/src/main/resources/cars.xml"), document);
        return xmlParser.readValue(new File("example/src/main/resources/cars.xml"));
    }

    /**
     * Method for creating an .bin file from an object and reading an xml file.
     */
    public static Document binFile(Document document) throws FileNotFoundException {
        Parser binParser = Parser.builder()
                .withBinDecoder()
                .withBinEncoder()
                .build();

        binParser.writeValue(new File("example/src/main/resources/cars.bin"), document);
        return binParser.readValue(new File("example/src/main/resources/cars.bin"));
    }

    /**
     * Method for adding a car object to the document.
     */
    public static void addCar(Document document, Car car) {
        document.addCar(car);
    }

    /**
     * Method for deleting  a car object to the document.
     */
    public static void deleteCar(Document document, Car car) {
        document.deleteCar(car);
    }

    /**
     * Method for changing a car object to the document.
     */
    public static void editCar(Document document, Car oldCar, Car newCar) {
        document.editCar(oldCar, newCar);
    }
}
