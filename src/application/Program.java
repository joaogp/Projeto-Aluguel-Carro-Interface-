/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.Locale;
import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.util.Date;
import model.Services.BrazilTaxService;
import model.Services.RentalServices;
import model.entities.CarRental;
import model.entities.Vehicle;

/**
 *
 * @author joaov
 */
public class Program {
    public static void main(String[] args) throws ParseException {
        
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:ss");
        System.out.println("Entre com as informações para alocação: ");
        System.out.print("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.println("Retirada(dd/MM/yyyy HH:ss): ");
        Date start = sdf.parse(sc.nextLine());
        System.out.println("Devolução (dd/MM/yyyy HH:ss): ");
        Date finish = sdf.parse(sc.nextLine());
        
        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));
        
        System.out.print("Digite o preço por Hora: R$");
        double pricePerHour = sc.nextDouble();
        System.out.print("Digite o preço por dia: R$");
        double pricePerDay = sc.nextDouble();
        
        RentalServices rentalServices = new RentalServices(pricePerDay, pricePerHour, new BrazilTaxService());
        
        rentalServices.processInvoice(cr);
        
        System.out.println("Fatura: ");
        System.out.println("Modelo do Carro: " + carModel);
        System.out.println("Pagamento sem taxa: " + String.format("%.2f", cr.getInvoice().getBasicPayment()));
        System.out.println("Taxa: " + String.format("%.2f", cr.getInvoice().getTax()));
        System.out.println("Pagamento total: " + String.format("%.2f", cr.getInvoice().getTotalPayment()));
        
        sc.close();
    }
    
}
