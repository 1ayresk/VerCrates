package Exceptions;


public class NotValidKeyException extends RuntimeException {
    public NotValidKeyException(){
        super();
    }
    public NotValidKeyException(String message){
        super(message);
    }
}