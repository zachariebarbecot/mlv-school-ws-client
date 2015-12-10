package webservice;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.Random;
import javax.jws.WebService;
import javax.jws.WebMethod;
import wsclient.Converter;
import wsclient.ConverterSoap;

@WebService(serviceName = "Banque")
public class Banque {

    private double solde;
    private final ConverterSoap currencySoap;
    private String currency;

    public Banque() {
        this.currencySoap = new Converter().getConverterSoap();
        List<String> list = currencySoap.getCurrencies().getString();
        int i = new Random().nextInt(list.size() - 1);
        this.currency = list.get(i);
        this.solde = 1000 + (new Random().nextDouble() * (5000 - 1000));
    }

    @WebMethod(operationName = "depot")
    public void depot(String currency, double credit) {
        double amount = currencySoap.getConversionAmount(currency, this.currency, currencySoap.getLastUpdateDate(), BigDecimal.valueOf(credit)).doubleValue();
        solde += amount;
    }

    @WebMethod(operationName = "debit")
    public boolean debit(String currency, double credit) {
        double amount = currencySoap.getConversionAmount(currency, this.currency, currencySoap.getLastUpdateDate(), BigDecimal.valueOf(credit)).doubleValue();
        if (amount >= solde) {
            return false;
        }
        solde -= amount;
        return true;

    }

    @WebMethod(operationName = "solde")
    public String valueSolde() {
        NumberFormat formatter = new DecimalFormat("#0.00");
        return formatter.format(solde) + " " + currency;
    }
}
