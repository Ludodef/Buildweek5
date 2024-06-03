package it.epicode.azienda_energia.datalayer.entities.invoices;

import jakarta.persistence.*;

@Entity
public class Cancelled extends InvoiceStatus{
    public Cancelled(){
        super("cancelled");
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cancelled_seq")
    @SequenceGenerator(name = "cancelled_seq", sequenceName = "cancelled_seq")
    private Long id;
}
