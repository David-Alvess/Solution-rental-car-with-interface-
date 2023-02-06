package model.services;

public class BrazilTaxService implements TaxService{
    public double tax(double amount){
        if (amount<=100){
            amount = amount * 0.2;
        }
        else {
            amount = amount * 0.15;
        }
        return amount;
    }
}
