/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Services;

import model.entities.CarRental;
import model.entities.Invoice;

/**
 *
 * @author joaov
 */
public class RentalServices {
    private Double pricePerDay;
    private Double pricePerHour;
    
    private TaxService taxService;

    public RentalServices(Double pricePerDay, Double pricePerHour, TaxService taxService) {
        this.pricePerDay = pricePerDay;
        this.pricePerHour = pricePerHour;
        this.taxService = taxService;
    }
    
    public void processInvoice(CarRental carRental){
        long t1 = carRental.getStart().getTime();
        long t2 = carRental.getFinish().getTime();
        //t2-t1 acha hora em milissegundos, divide por 1000 se acha em segundos, divide por 60 acha em minutos, divide por 60 dnv acha em horas
        double hours = (double)(t2-t1)/1000/60/60;
        double basicPayment;
        if(hours <= 12.0){
            basicPayment = Math.ceil(hours) * pricePerHour;
        }else{
            basicPayment = Math.ceil(hours/24) * pricePerDay;
        }
        double tax = taxService.tax(basicPayment);
        
        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}
