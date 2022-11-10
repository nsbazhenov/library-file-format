package com.github.nsbazhenov.fparser;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.List;

public class Document {

    @JacksonXmlProperty(localName = "Car")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Car> cars;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public void deleteCar(Car car) {
        cars.remove(car);
    }

    public void editCar(Car oldCar, Car newCar) {
        deleteCar(oldCar);
        addCar(newCar);
    }

    @Override
    public String toString() {
        return "Document{" +
                "cars=" + cars +
                '}';
    }
}