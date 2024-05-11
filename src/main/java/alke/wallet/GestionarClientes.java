package alke.wallet;

import java.util.List;
import java.util.ArrayList;

public class GestionarClientes {
    private List<Cliente> clientes;

    public GestionarClientes() {
        this.clientes = new ArrayList<>();
        inicializarClientes();
    }

    private void inicializarClientes() {
        // Crear clientes y asignarles cuentas inicialmente
        Cliente cliente1 = new Cliente(1L, "Ronoroa Zoro");
        cliente1.agregarCuenta(new CuentaBancaria(1L, "001", 1000.0)); // Asumiendo que el constructor de CuentaBancaria acepta estos parámetros

        Cliente cliente2 = new Cliente(2L, "Nico Robin");
        cliente2.agregarCuenta(new CuentaBancaria(2L, "002", 2200.0)); // Asumiendo que el constructor de CuentaBancaria acepta estos parámetros

        agregarCliente(cliente1);
        agregarCliente(cliente2);
    }

    public void agregarCliente(Cliente cliente) {
        if (cliente == null) {
            throw new IllegalArgumentException("El cliente no puede ser null");
        }
        this.clientes.add(cliente);
    }

    public Cliente buscarClientePorId(long id) {
        for (Cliente cliente : this.clientes) {
            if (cliente.getIdCliente() == id) {
                return cliente;
            }
        }
        return null; // Devuelve null si no encuentra un cliente con el ID especificado
    }

    public List<Cliente> getClientes() {
        return this.clientes;
    }
}

