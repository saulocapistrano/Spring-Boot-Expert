package ifinit.com.vendas.validation;

import ifinit.com.vendas.validation.constraintvalidation.NotEmptyListValidator;
import javax.validation.Constraint;
import javax.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Constraint(validatedBy = NotEmptyListValidator.class)
public @interface NotEmptyList {
    String message () default "This list no has empty";
    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
