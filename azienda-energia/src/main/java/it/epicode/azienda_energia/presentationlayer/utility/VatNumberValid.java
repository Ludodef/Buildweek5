package it.epicode.azienda_energia.presentationlayer.utility;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = VATValidator.class)
public @interface VatNumberValid {

    String message() default "Il valore deve essere una partita IVA valida";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
