package alke.wallet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClienteTest {
    private Cliente cliente;
    private CuentaBancaria cuenta1;
    private CuentaBancaria cuenta2;

    @BeforeEach
    public void setUp() {
        cliente = new Cliente(1L, "John Doe");
        cuenta1 = new CuentaBancaria(101, "10001", 1000.0);
        cuenta2 = new CuentaBancaria(102, "10002", 2000.0);
    }

    @Test
    public void testAgregarCuenta() {
        cliente.agregarCuenta(cuenta1);
        assertEquals(1, cliente.getCuentas().size(), "Debería haber una cuenta agregada");
        assertSame(cuenta1, cliente.getCuentas().get(0), "La cuenta agregada debe ser la misma que la ingresada");
    }

    @Test
    public void testAgregarCuentaNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> cliente.agregarCuenta(null));
        assertEquals("Cuenta no puede ser null", exception.getMessage(), "Debe lanzar IllegalArgumentException con el mensaje correcto");
    }

    @Test
    public void testGetCuentaPrincipalSinCuentas() {
        Exception exception = assertThrows(IllegalStateException.class, cliente::getCuentaPrincipal);
        assertEquals("No hay cuentas disponibles", exception.getMessage(), "Debe lanzar IllegalStateException si no hay cuentas");
    }

    @Test
    public void testGetCuentaPrincipalConCuentas() {
        cliente.agregarCuenta(cuenta1);
        cliente.agregarCuenta(cuenta2);
        CuentaBancaria retrievedAccount = cliente.getCuentaPrincipal();
        assertSame(cuenta1, retrievedAccount, "La cuenta principal debe ser la primera cuenta agregada");
    }

    @Test
    public void testGetIdCliente() {
        assertEquals(1L, cliente.getIdCliente(), "El ID del cliente debe coincidir con el proporcionado en el constructor");
    }

    @Test
    public void testGetNombre() {
        assertEquals("John Doe", cliente.getNombre(), "El nombre del cliente debe coincidir con el proporcionado en el constructor");
    }
}
