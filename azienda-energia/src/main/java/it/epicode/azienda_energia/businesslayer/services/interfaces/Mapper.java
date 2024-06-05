package it.epicode.azienda_energia.businesslayer.services.interfaces;


public interface Mapper<D,S > {

    S map(D input);
}
