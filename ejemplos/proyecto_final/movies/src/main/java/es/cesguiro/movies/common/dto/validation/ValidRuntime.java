package es.cesguiro.movies.common.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = RuntimeValidator.class)
public @interface ValidRuntime {
    String message() default "La duración debe ser superior a 0 minutos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
