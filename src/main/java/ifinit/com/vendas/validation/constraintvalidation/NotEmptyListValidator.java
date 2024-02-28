package ifinit.com.vendas.validation.constraintvalidation;

import ifinit.com.vendas.validation.NotEmptyList;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import java.util.List;

public class NotEmptyListValidator implements ConstraintValidator<NotEmptyList, List>{
    @Override
    public void initialize(NotEmptyList constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(List list, ConstraintValidatorContext context) {
        return list != null && !list.isEmpty();
    }
}
