package it.epicode.azienda_energia.presentationlayer.utility;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CAPValidator.class)
public @interface CAPValid {

    String message() default "Il valore deve essere un CAP valido";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
