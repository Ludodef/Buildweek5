package it.epicode.azienda_energia.datalayer.entities.invoices;

import jakarta.persistence.Entity;

@Entity
public class Sent extends InvoiceStatus{
    public Sent(){
        super("sent");
    }
}
