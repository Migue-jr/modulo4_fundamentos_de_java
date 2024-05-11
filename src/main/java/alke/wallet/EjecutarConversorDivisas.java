package alke.wallet;

public class EjecutarConversorDivisas implements ConversorDivisas {
    public double convert(double amount, String fromCurrency, String toCurrency) throws ExcepcionesConversorDivisas {
        switch (fromCurrency + "->" + toCurrency) {
            case "USD->EUR":
                return amount * 0.93;
            case "EUR->USD":
                return amount * 1.08;
            case "USD->JPY":
                return amount * 155.61;
            case "JPY->USD":
                return amount * 0.0064;
            case "CLP->USD":
                return amount * 0.0011;
            case "USD->CLP":
                return amount * 929.39;
            case "CLP->EUR":
                return amount * 0.0010;
            case "EUR->CLP":
                return amount * 1002.10;
            case "CLP->JPY":
                return amount * 0.17;
            case "JPY->CLP":
                return amount * 5.97;

            default:
                throw new ExcepcionesConversorDivisas("Conversión no soportada");
        }
    }
}