package alke.wallet;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CuentaBancariaTest {
    private CuentaBancaria cuenta;

    @BeforeEach
    void setUp() {
        cuenta = new CuentaBancaria(1L, "123456789", 1000.0);  // Saldo inicial de 1000.0
    }

    @Test
    void testDepositarMontoPositivo() {
        assertTrue(cuenta.depositar(200.0), "El depósito debe ser exitoso");
        assertEquals(1200.0, cuenta.getSaldo(), "El saldo debe incrementarse correctamente");
    }

    @Test
    void testRetirarFondosSuficientes() {
        assertTrue(cuenta.retirar(500.0), "El retiro debe ser exitoso");
        assertEquals(500.0, cuenta.getSaldo(), "El saldo debe disminuir correctamente");
    }

    @Test
    void testRetirarFondosInsuficientes() {
        assertFalse(cuenta.retirar(1500.0), "Debe fallar por fondos insuficientes");
        assertEquals(1000.0, cuenta.getSaldo(), "El saldo no debe cambiar");
    }

    @Test
    void testTransferirValida() {
        CuentaBancaria cuentaDestino = new CuentaBancaria(2L, "987654321", 500.0);
        assertTrue(cuenta.transferir(cuentaDestino, 300.0), "La transferencia debe ser exitosa");
        assertEquals(700.0, cuenta.getSaldo(), "El saldo de la cuenta origen debe disminuir correctamente");
        assertEquals(800.0, cuentaDestino.getSaldo(), "El saldo de la cuenta destino debe incrementarse correctamente");
    }

    @Test
    void testTransferirFondosInsuficientes() {
        CuentaBancaria cuentaDestino = new CuentaBancaria(2L, "987654321", 500.0);
        assertFalse(cuenta.transferir(cuentaDestino, 1500.0), "Debe fallar por fondos insuficientes");
        assertEquals(1000.0, cuenta.getSaldo(), "El saldo de la cuenta origen no debe cambiar");
        assertEquals(500.0, cuentaDestino.getSaldo(), "El saldo de la cuenta destino no debe cambiar");
    }
}
