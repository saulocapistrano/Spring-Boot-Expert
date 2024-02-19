package ifinit.com.vendas.exception;

public class OrderedNotFoundException extends RuntimeException {
    public OrderedNotFoundException() {
        super("Ordered not found.");
    }
}
