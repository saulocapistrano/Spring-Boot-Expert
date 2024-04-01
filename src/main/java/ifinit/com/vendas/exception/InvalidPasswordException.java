package ifinit.com.vendas.exception;

public class InvalidPasswordException extends RuntimeException {
    public InvalidPasswordException(){
        super("Invalid password.");
    }
}
