package es.cesguiro.movies.common.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RuntimeValidator implements ConstraintValidator<ValidRuntime, Integer> {

    private String message;
    @Override
    public void initialize(ValidRuntime constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        //this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Integer runtime, ConstraintValidatorContext constraintValidatorContext) {
        /*if(runtime <= 0) {
            throw new DtoValidationException(message);
        }*/
        return runtime > 0;
    }
}

