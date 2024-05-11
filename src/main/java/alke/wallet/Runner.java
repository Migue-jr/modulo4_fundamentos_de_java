package alke.wallet;

import java.util.ArrayList;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            GestionarClientes gestionarClientes = new GestionarClientes();

            // Inicializa algunos clientes de ejemplo
            Cliente cliente1 = new Cliente(1L, "Ronoroa Zoro");
            Cliente cliente2 = new Cliente(2L, "Nico Robin");
            gestionarClientes.agregarCliente(cliente1);
            gestionarClientes.agregarCliente(cliente2);

            ConversorDivisas converter = new EjecutarConversorDivisas();

            System.out.println("Ingrese el ID del cliente para iniciar sesión:");
            long clienteId = scanner.nextLong();
            Cliente cliente = gestionarClientes.buscarClientePorId(clienteId);

            if (cliente == null) {
                System.out.println("Cliente no encontrado. Asegúrese de que el ID es correcto.");
                return;  // Finaliza la ejecución si no se encuentra el cliente
            }

            Wallet wallet = new Wallet(cliente, converter);
            System.out.println("Bienvenido a Alke Wallet!");

            boolean quit = false;
            while (!quit) {
                System.out.println("\nElige una opción:");
                System.out.println("1. Mostrar Saldo");
                System.out.println("2. Depositar Fondos");
                System.out.println("3. Retirar Fondos");
                System.out.println("4. Convertir y Depositar Fondos");
                System.out.println("5. Convertir y Retirar Fondos");
                System.out.println("6. Realizar Transferencia");
                System.out.println("0. Salir");

                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Saldo Actual: " + wallet.getBalance() + " CLP");
                        break;
                    case 2:
                        System.out.print("Ingrese el monto a depositar: ");
                        double depositAmount = scanner.nextDouble();
                        wallet.deposit(depositAmount);
                        break;
                    case 3:
                        System.out.print("Ingrese el monto a retirar: ");
                        double withdrawalAmount = scanner.nextDouble();
                        wallet.withdraw(withdrawalAmount);
                        break;
                    case 4:
                        System.out.print("Ingrese el monto a convertir y depositar: ");
                        double convertDepositAmount = scanner.nextDouble();
                        System.out.print("Desde la divisa: ");
                        String fromCurrency = scanner.next();
                        System.out.print("A la divisa: ");
                        String toCurrency = scanner.next();
                        wallet.convertAndDeposit(convertDepositAmount, fromCurrency, toCurrency);
                        break;
                    case 5:
                        System.out.print("Ingrese el monto a convertir y retirar: ");
                        double convertWithdrawAmount = scanner.nextDouble();
                        System.out.print("Desde la divisa: ");
                        String fromCurrencyWithdraw = scanner.next();
                        System.out.print("A la divisa: ");
                        String toCurrencyWithdraw = scanner.next();
                        try {
                            wallet.convertAndWithdraw(convertWithdrawAmount, fromCurrencyWithdraw, toCurrencyWithdraw);
                        } catch (ExcepcionesConversorDivisas e) {
                            System.out.println("Error en la conversión: " + e.getMessage());
                        }
                        break;

                    case 6:
                        System.out.print("Ingrese el ID del cliente destino para la transferencia: ");
                        long idClienteDeposito = scanner.nextLong();
                        Cliente clienteDeposito = gestionarClientes.buscarClientePorId(idClienteDeposito);
                        if (clienteDeposito != null) {
                            System.out.print("Ingrese el monto a transferir: ");
                            double montoDeposito = scanner.nextDouble();
                            wallet.transfer(montoDeposito, clienteDeposito.getCuentaPrincipal());
                        } else {
                            System.out.println("Cliente no encontrado.");
                        }
                        break;
                    case 0:
                        quit = true;
                        break;
                    default:
                        System.out.println("Opción inválida. Por favor elige de nuevo.");
                }
            }
            System.out.println("Gracias por usar Alke Wallet!");
        }
    }
}

