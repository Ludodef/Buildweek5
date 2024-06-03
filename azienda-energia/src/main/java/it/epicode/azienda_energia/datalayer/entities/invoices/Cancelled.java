package it.epicode.azienda_energia.datalayer.entities.invoices;

import jakarta.persistence.Entity;

@Entity
public class Cancelled extends InvoiceStatus{
    public Cancelled(){
        super("cancelled");
    }
}
