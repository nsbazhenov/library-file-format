package com.github.nsbazhenov.fparser;

import org.junit.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class XmlParseTest {
    private static final String PATH_NAME = "src/test/resources/cars.xml";

    private final Parser xmlParser = Parser.builder()
            .withXmlDecoder()
            .withXmlEncoder()
            .build();

    @After
    public void runAfterTestMethod() {
        File file = new File(PATH_NAME);
        file.delete();
    }

    @Test
    public void whenGettingAnObjectToSave_givenXmlFile_theExpectedFileShouldBeReturned() throws FileNotFoundException {
        Car expectedFirstCar = new Car("12.08.2018", "Toyota Chaser", 10000);
        Car expectedSecondCar = new Car("10.01.2022", "Toyota Mark", 15000);

        List<Car> cars = new ArrayList<>();
        cars.add(expectedFirstCar);
        cars.add(expectedSecondCar);

        Document document = new Document();
        document.setCars(cars);

        xmlParser.writeValue(new File(PATH_NAME), document);
        Document xmlDocument = xmlParser.readValue(new File(PATH_NAME));

        Car actualFirstCar = xmlDocument.getCars().get(0);
        Car actualSecondCar = xmlDocument.getCars().get(1);

        Assert.assertEquals(2, xmlDocument.getCars().size());

        Assert.assertEquals(expectedFirstCar.date(), actualFirstCar.date());
        Assert.assertEquals(expectedFirstCar.brandName(), actualFirstCar.brandName());
        Assert.assertEquals(expectedFirstCar.price(), actualFirstCar.price());

        Assert.assertEquals(expectedSecondCar.date(), actualSecondCar.date());
        Assert.assertEquals(expectedSecondCar.brandName(), actualSecondCar.brandName());
        Assert.assertEquals(expectedSecondCar.price(), actualSecondCar.price());
    }

    @Test(expected = FileNotFoundException.class)
    public void whenReadingAnXmlFile_givenObject_thenShouldThrowException() throws FileNotFoundException {
        xmlParser.readValue(new File(PATH_NAME));
    }
}
