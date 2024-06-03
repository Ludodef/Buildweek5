package it.epicode.azienda_energia.datalayer.entities.invoices;

import jakarta.persistence.*;

@Entity
public class Paid extends InvoiceStatus{
    public Paid(){
        super("paid");
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paid_seq")
    @SequenceGenerator(name = "paid_seq", sequenceName = "paid_seq")
    private Long id;
}
