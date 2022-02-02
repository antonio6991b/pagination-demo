package ru.bolgov.spring.pagination.Exception;

public class TooMuchCarTryToAddException extends RuntimeException{
    public TooMuchCarTryToAddException(String description){
        super(description);
    }
    public TooMuchCarTryToAddException(){
        super();
    }
}
