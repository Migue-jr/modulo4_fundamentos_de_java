package alke.wallet;

import java.util.List;
import java.util.ArrayList;

public class CuentaBancaria {
    private long idCuenta;
    private String numeroCuenta;
    private double saldo;
    private List<Transaccion> transacciones;

    public CuentaBancaria(long idCuenta, String numeroCuenta, double saldoInicial) {
        this.idCuenta = idCuenta;
        this.numeroCuenta = numeroCuenta;
        this.saldo = saldoInicial;
        this.transacciones = new ArrayList<>();
    }

    public boolean depositar(double monto) {
        if (monto <= 0) {
            return false; // Verifica que el monto sea positivo
        }
        saldo += monto;
        transacciones.add(new Transaccion("Depósito", monto));
        return true;
    }

    public boolean retirar(double monto) {
        if (monto <= 0 || monto > saldo) {
            return false; // Verifica que haya suficientes fondos y que el monto sea positivo
        }
        saldo -= monto;
        transacciones.add(new Transaccion("Retiro", monto));
        return true;
    }

    public boolean transferir(CuentaBancaria destino, double monto) {
        if (monto <= 0 || saldo < monto) {
            return false; // Verifica que haya suficientes fondos y que el monto sea positivo antes de transferir
        }
        if (this.retirar(monto)) {
            destino.depositar(monto);
            return true;
        }
        return false;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public List<Transaccion> getTransacciones() {
        return new ArrayList<>(transacciones);
    }
}
