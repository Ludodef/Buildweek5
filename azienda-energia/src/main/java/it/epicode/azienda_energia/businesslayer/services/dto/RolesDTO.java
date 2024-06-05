package it.epicode.azienda_energia.businesslayer.services.dto;

import lombok.*;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with")
public class RolesDTO extends BaseDTO{
    String roleType;
}
