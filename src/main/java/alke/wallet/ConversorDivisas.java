package alke.wallet;

public interface ConversorDivisas {
    double convert(double amount, String fromCurrency, String toCurrency) throws ExcepcionesConversorDivisas;
}



