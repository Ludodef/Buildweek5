package it.epicode.azienda_energia.datalayer.entities.invoices;

import jakarta.persistence.Entity;

@Entity
public class Received extends InvoiceStatus{
    public Received(){
        super("received");
    }
}
