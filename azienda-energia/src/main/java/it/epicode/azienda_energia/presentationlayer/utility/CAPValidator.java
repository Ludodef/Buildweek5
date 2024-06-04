package it.epicode.azienda_energia.presentationlayer.utility;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;


public class CAPValidator implements ConstraintValidator<CAPValid, String>{

        @Override
        public boolean isValid(String fieldValue, ConstraintValidatorContext context) {
            // Verifica che la stringa sia non nulla e abbia esattamente 5 caratteri
            if (fieldValue == null || fieldValue.length() != 5) {
                return false;
            }

            // Verifica che tutti i caratteri siano cifre
            for (char c : fieldValue.toCharArray()) {
                if (!Character.isDigit(c)) {
                    return false;
                }
            }

            // Restituisci true se tutte le verifiche passano
            return true;
        }

}
