package com.untad.ferin.spkwpprodukjual.data;

public class Result {
    public Alternative data;
    public double value;

    public Result(){}

    public Result(Alternative data, double value){
        this.data = data;
        this.value = value;
    }

    public Alternative getData() {
        return data;
    }

    public void setData(Alternative data) {
        this.data = data;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
