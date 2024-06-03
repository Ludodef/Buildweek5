package it.epicode.azienda_energia.datalayer.entities.invoices;

import jakarta.persistence.*;

@Entity
public class Received extends InvoiceStatus{
    public Received(){
        super("received");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "received_seq")
    @SequenceGenerator(name = "received_seq", sequenceName = "received_seq")
    private Long id;
}
