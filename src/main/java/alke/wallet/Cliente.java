package alke.wallet;

import java.util.List;
import java.util.ArrayList;

public class Cliente {
    private long idCliente;
    private String nombre;
    private List<CuentaBancaria> cuentas = new ArrayList<>();

    public Cliente(long idCliente, String nombre) {
        this.idCliente = idCliente;
        this.nombre = nombre;
    }

    public void agregarCuenta(CuentaBancaria cuenta) {
        if (cuenta == null) throw new IllegalArgumentException("Cuenta no puede ser null");
        cuentas.add(cuenta);
    }

    public CuentaBancaria getCuentaPrincipal() {
        if (cuentas.isEmpty()) throw new IllegalStateException("No hay cuentas disponibles");
        return cuentas.get(0);  // Asumiendo que la primera cuenta es la principal
    }

    public long getIdCliente() {
        return idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public List<CuentaBancaria> getCuentas() {
        return cuentas;
    }
}

