package application;

import model.entities.CarRental;
import model.entities.Invoice;
import model.entities.Vehicle;
import model.services.BrazilTaxService;
import model.services.RentalService;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {

        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);
        Vehicle vehicle = new Vehicle();
        Invoice invoice = new Invoice();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do aluguel: ");
        System.out.print("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.print("Retirada (dd/mm/yyyy hh:mm): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), dtf);
        System.out.print("Retorno (dd/mm/yyyy hh:mm): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), dtf);

        CarRental cr = new CarRental(start, finish, new Vehicle(carModel));

        System.out.print("Entre com o preço por hora: ");
        double pricePerHour = sc.nextDouble();
        System.out.print("Entre com o preço por dia: ");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour, pricePerDay, new BrazilTaxService());
        rentalService.processInvoice(cr);

        System.out.println("FATURA: ");
        System.out.print("Pagamento básico: " + String.format("%.2f%n", cr.getInvoice().getBasicPayment()));
        System.out.printf("Imposto: %.2f%n", cr.getInvoice().getTax());
        System.out.printf("Pagamento total: %.2f", cr.getInvoice().getTotalPayment());

    }
}
