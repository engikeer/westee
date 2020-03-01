package com.mfun.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Book {

    private static Car car;

    public Book(Car car_) {
        car = car_;
    }

    //    @Autowired
//    public void setCar(Car car) {
//        Book.car = car;
//    }

    public Car getCar() {
        return car;
    }



}
