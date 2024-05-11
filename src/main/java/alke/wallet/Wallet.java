package alke.wallet;

public class Wallet {
    private Cliente cliente;
    private ConversorDivisas converter;

    public Wallet(Cliente cliente, ConversorDivisas converter) {
        if (cliente == null || cliente.getCuentas().isEmpty()) {
            throw new IllegalArgumentException("El cliente no puede ser null y debe tener al menos una cuenta");
        }
        this.cliente = cliente;
        this.converter = converter;
    }

    public double getBalance() {
        return cliente.getCuentas().get(0).getSaldo();
    }

    public void deposit(double amount) {
        cliente.getCuentas().get(0).depositar(amount);
    }

    public void withdraw(double amount) {
        cliente.getCuentas().get(0).retirar(amount);
    }

    public void transfer(double amount, CuentaBancaria cuentaDestino) {
        cliente.getCuentas().get(0).transferir(cuentaDestino, amount);
    }

    public void convertAndDeposit(double amount, String fromCurrency, String toCurrency) {
        try {
            double convertedAmount = converter.convert(amount, fromCurrency, toCurrency);
            deposit(convertedAmount);
        } catch (ExcepcionesConversorDivisas e) {
            System.err.println("Error durante la conversión: " + e.getMessage());
        }
    }

    public void convertAndWithdraw(double amount, String fromCurrency, String toCurrency) throws ExcepcionesConversorDivisas {
        double convertedAmount = converter.convert(amount, fromCurrency, toCurrency);
        if (this.cliente.getCuentas().get(0).getSaldo() >= convertedAmount) {
            this.cliente.getCuentas().get(0).retirar(convertedAmount);
            System.out.println("Conversión y retiro completados. Monto retirado: " + convertedAmount + " " + toCurrency);
        } else {
            System.out.println("Fondos insuficientes para completar el retiro.");
        }
    }

}

