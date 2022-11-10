package com.github.nsbazhenov.fparser;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class DocumentTest {
    private static Document document;
    private static Car oldSecondCar;
    private static Car newSecondCar;

    @BeforeClass
    public static void runOnceBeforeClass() {
       Car firstCar = new Car("12.08.2018", "Toyota Chaser", 10000);
       oldSecondCar = new Car("10.01.2022", "Toyota Mark", 15000);
       newSecondCar = new Car("10.01.2022", "Toyota Mark", 10000);

       List<Car> carsList = new ArrayList<>();
       carsList.add(firstCar);

       document = new Document();
       document.setCars(carsList);
    }

    @Test
    public void whenAddCar_givenNewList_theExpectedListBeReturned() {
        document.addCar(oldSecondCar);

        Assert.assertEquals(3, document.getCars().size());
    }

    @Test
    public void whenEditCarPrice_givenNewList_theExpectedListBeReturned() {
        document.editCar(oldSecondCar, newSecondCar);

        Assert.assertEquals(newSecondCar.price(), document.getCars().get(1).price());
    }

    @Test
    public void whenDeleteCar_givenNewList_theExpectedListBeReturned() {
        document.deleteCar(oldSecondCar);

        Assert.assertEquals(2, document.getCars().size());
    }
}
