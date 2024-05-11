package alke.wallet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class WalletTest {
    private Wallet wallet;
    private Cliente cliente;
    private CuentaBancaria cuenta;
    private ConversorDivisas converter;

    @BeforeEach
    void setUp() {
        cuenta = mock(CuentaBancaria.class);
        cliente = mock(Cliente.class);
        converter = mock(ConversorDivisas.class);

        when(cliente.getCuentas()).thenReturn(java.util.Collections.singletonList(cuenta));
        when(cuenta.getSaldo()).thenReturn(1000.0);  // Ejemplo de saldo inicial

        wallet = new Wallet(cliente, converter);
    }

    @Test
    void constructorShouldThrowWhenClienteIsNull() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Wallet(null, converter));
        assertEquals("El cliente no puede ser null y debe tener al menos una cuenta", exception.getMessage());
    }

    @Test
    void constructorShouldThrowWhenCuentasIsEmpty() {
        when(cliente.getCuentas()).thenReturn(java.util.Collections.emptyList());
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new Wallet(cliente, converter));
        assertEquals("El cliente no puede ser null y debe tener al menos una cuenta", exception.getMessage());
    }

    @Test
    void getBalanceShouldReturnCorrectBalance() {
        assertEquals(1000.0, wallet.getBalance());
    }

    @Test
    void depositShouldIncreaseBalance() {
        wallet.deposit(100.0);
        verify(cuenta).depositar(100.0);
    }

    @Test
    void withdrawShouldDecreaseBalance() {
        wallet.withdraw(100.0);
        verify(cuenta).retirar(100.0);
    }

    @Test
    void transferShouldInvokeTransferOnCuenta() {
        CuentaBancaria destino = mock(CuentaBancaria.class);
        wallet.transfer(100.0, destino);
        verify(cuenta).transferir(destino, 100.0);
    }

    @Test
    void convertAndDepositShouldHandleConversion() throws ExcepcionesConversorDivisas {
        when(converter.convert(100.0, "USD", "EUR")).thenReturn(85.0);
        wallet.convertAndDeposit(100.0, "USD", "EUR");
        verify(cuenta).depositar(85.0);
    }

    @Test
    void convertAndWithdrawShouldHandleConversion() throws ExcepcionesConversorDivisas {
        when(converter.convert(100.0, "USD", "EUR")).thenReturn(85.0);
        wallet.convertAndWithdraw(100.0, "USD", "EUR");
        verify(cuenta).retirar(85.0);
    }

    @Test
    void convertAndWithdrawShouldHandleInsufficientFunds() throws ExcepcionesConversorDivisas {
        when(converter.convert(1200.0, "USD", "EUR")).thenReturn(1020.0);
        wallet.convertAndWithdraw(1200.0, "USD", "EUR");
        verify(cuenta, never()).retirar(1020.0);
    }
}
