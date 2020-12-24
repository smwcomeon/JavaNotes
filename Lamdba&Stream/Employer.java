package com.JDKStream.salary;

import java.util.Objects;

public class Employer {

    private String name;
    private Integer age;
    private Double salry;
    private Status status;

    public Employer() {
    }
    public Employer(Integer age){
        this.age=age;
    }

    public Employer(String name, Integer age, Double salry) {
        this.name = name;
        this.age = age;
        this.salry = salry;
    }

    public Employer(String name, Integer age, Double salry, Status status) {
        this.name = name;
        this.age = age;
        this.salry = salry;
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getSalry() {
        return salry;
    }

    public void setSalry(Double salry) {
        this.salry = salry;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employer employer = (Employer) o;
        return Objects.equals(name, employer.name) &&
                Objects.equals(age, employer.age) &&
                Objects.equals(salry, employer.salry);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, salry);
    }

    @Override
    public String toString() {
        return "Employer{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", salry=" + salry +
                ", status=" + status +
                '}';
    }

    public enum Status{
        FREE,
        BUSY,
        VOCATION;
    }
}
