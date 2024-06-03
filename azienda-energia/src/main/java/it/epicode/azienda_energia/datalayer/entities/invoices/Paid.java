package it.epicode.azienda_energia.datalayer.entities.invoices;

import jakarta.persistence.Entity;

@Entity
public class Paid extends InvoiceStatus{
    public Paid(){
        super("paid");
    }
}
