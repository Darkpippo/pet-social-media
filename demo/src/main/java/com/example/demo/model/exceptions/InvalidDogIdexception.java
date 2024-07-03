package com.example.demo.model.exceptions;

public class InvalidDogIdexception extends RuntimeException{
    public InvalidDogIdexception(){
        super("Invalid dog id.");
    }
}
