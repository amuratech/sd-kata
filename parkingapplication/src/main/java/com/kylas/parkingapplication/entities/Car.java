package com.kylas.parkingapplication.entities;

public class Car {
    private int id;
    private String carNo;

    public Car(int id, String carNo) {
        this.id = id;
        this.carNo = carNo;
    }

    public Car() {
        super();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarNo() {
        return carNo;
    }

    public void setCarNo(String carNo) {
        this.carNo = carNo;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", carNo='" + carNo + '\'' +
                '}';
    }
}
