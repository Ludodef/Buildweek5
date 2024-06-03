package it.epicode.azienda_energia.datalayer.entities.invoices;

import jakarta.persistence.*;

@Entity
public class Sent extends InvoiceStatus{
    public Sent(){
        super("sent");
    }
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sent_seq")
    @SequenceGenerator(name = "sent_seq", sequenceName = "sent_seq")
    private Long id;
}
