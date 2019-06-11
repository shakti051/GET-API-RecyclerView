package com.example.getsync;

public class Person {
    private String name;
    private String age;
    private String imei;

    public Person(String name, String age,String imei) {
        this.name = name;
        this.age = age;
        this.imei = imei;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }
}
