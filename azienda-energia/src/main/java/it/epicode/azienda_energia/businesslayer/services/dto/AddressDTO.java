package it.epicode.azienda_energia.businesslayer.services.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class AddressDTO extends BaseDTO{
    private String street;
    private String number;
    private String location;
    private int zip;
    private String Municipality;
}
