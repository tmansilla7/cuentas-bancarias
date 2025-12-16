package ar.edu.unlam.pb2;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class BancoTest {
	private Cliente cliente1;
    private Cliente cliente2;
    private Cliente cliente3;
    private Cliente cliente4;
    private Cliente cliente5;
    private Cliente cliente6;
    private Cliente cliente7;
    private CuentaSueldo cuentaSueldo2;
    private CuentaSueldo cuentaSueldo3;
    private CuentaSueldo cuentaSueldo4;
    private CuentaSueldo cuentaSueldo5;
    private CuentaSueldo cuentaSueldo6;
    private CajaDeAhorros cajaDeAhorros3;
    private CajaDeAhorros cajaDeAhorros4;
    private CajaDeAhorros cajaDeAhorros6;
    private CuentaCorriente cuentaCorriente2;
    private CuentaCorriente cuentaCorriente5;
    private CuentaCorriente cuentaCorriente6;
    private Cuenta cuentaSueldo;
    private Cuenta cajaDeAhorros;
    private Cuenta cuentaCorriente;
    private Banco banco;

    
    @Before
    public void setUp() throws ClienteDuplicadoException {
        cliente1 = new Cliente("12345678", "Juan Perez");
        cliente2 = new Cliente("87654321", "Maria Lopez");
        cliente3 = new Cliente("22334455", "Carlos Gómez");
        cliente4 = new Cliente("33445566", "Laura Fernández");
        cliente5 = new Cliente("44556677", "Roberto Díaz");
        cliente6 = new Cliente("55667788", "Sofía Ruíz");
        cliente7 = new Cliente("55667788", "Sofía Ruíz");
       
        
        cuentaSueldo = new CuentaSueldo("CBU001", cliente1.getDni(), 2000.0);
        cajaDeAhorros = new CajaDeAhorros("CBU002", cliente1.getDni(), 10000.0);
        cuentaCorriente = new CuentaCorriente("CBU003", cliente2.getDni(), 500.0);
        cuentaSueldo2 = new CuentaSueldo("CBU001", cliente3.getDni(), 3000.0);  // CBU duplicado
        cuentaSueldo2 = new CuentaSueldo("CBU004", cliente3.getDni(), 3000.0);
        cuentaSueldo3 = new CuentaSueldo("CBU005", cliente4.getDni(), 2500.0);
        cuentaSueldo4 = new CuentaSueldo("CBU006", cliente5.getDni(), 0.0);
        cuentaSueldo5 = new CuentaSueldo("CBU007", cliente1.getDni(), 2800.0);
        cuentaSueldo6 = new CuentaSueldo("CBU008", cliente6.getDni(), 3200.0); // Nueva cuenta sueldo para el nuevo cliente

        cajaDeAhorros3 = new CajaDeAhorros("CBU010", cliente4.getDni(), 6000.0);
        cajaDeAhorros4 = new CajaDeAhorros("CBU010", cliente5.getDni(), 7000.0); // CBU duplicado       
        cajaDeAhorros6 = new CajaDeAhorros("CBU013", cliente6.getDni(), 9000.0); // Nueva caja de ahorros para el nuevo cliente

        cuentaCorriente2 = new CuentaCorriente("CBU014", cliente3.getDni(), 1000.0);
        cuentaCorriente5 = new CuentaCorriente("CBU006", cliente2.getDni(), 800.0);
        cuentaCorriente6 = new CuentaCorriente("CBU018", cliente6.getDni(), 2000.0);
        
        banco = new Banco();
        
            banco.agregarCliente(cliente1);
            banco.agregarCliente(cliente2);
            banco.agregarCliente(cliente3);
            banco.agregarCliente(cliente4);
            banco.agregarCliente(cliente5);
            banco.agregarCliente(cliente6);
            
        try {
            banco.agregarCliente(cliente7);
		} catch (ClienteDuplicadoException e) {
			e.getMessage();
		}

        
        try {
            banco.agregarCuenta(cuentaSueldo);
            banco.agregarCuenta(cajaDeAhorros);
            banco.agregarCuenta(cuentaCorriente);
            banco.agregarCuenta(cuentaSueldo2); // CBU duplicado
            banco.agregarCuenta(cuentaSueldo3);
            banco.agregarCuenta(cuentaSueldo4);
            banco.agregarCuenta(cuentaSueldo5);
            banco.agregarCuenta(cuentaSueldo6);

            banco.agregarCuenta(cajaDeAhorros3);
            banco.agregarCuenta(cajaDeAhorros6);

            banco.agregarCuenta(cuentaCorriente2);
            banco.agregarCuenta(cuentaCorriente6);
        } catch (ClienteInexistenteException e) {
        	e.getMessage();
       }
        
        Assert.assertEquals(6 , banco.getCantidadClientes());
        Assert.assertEquals(12 , banco.getCantidadCuentas());
        
    }
    
    @Test
    public void queSePuedaExtraer1000PesosDeUnaCuentaSueldoConSaldoIgualA2000Pesos() throws SaldoInsuficienteException, CuentaNoExisteException {
    	banco.retirar(cuentaSueldo.getCbu(), 1000);
    }
    
    @Test(expected = SaldoInsuficienteException.class)
    public void queNoSePuedaExtraer2500PesosDeUnaCuentaSueldoConSaldoIgualA2000Pesos() throws SaldoInsuficienteException, CuentaNoExisteException {
    	banco.retirar(cuentaSueldo.getCbu(), 2500);
    }
    
    @Test
    public void queAlRealizar6ExtraccionesDe1000EnUnaCajaDeAhorroConSaldoInicialDe10000SuSaldoFinalSea3900() throws SaldoInsuficienteException, CuentaNoExisteException {
    	banco.retirar(cajaDeAhorros.getCbu(), 1000);
    	banco.retirar(cajaDeAhorros.getCbu(), 1000);
    	banco.retirar(cajaDeAhorros.getCbu(), 1000);
    	banco.retirar(cajaDeAhorros.getCbu(), 1000);
    	banco.retirar(cajaDeAhorros.getCbu(), 1000);
    	banco.retirar(cajaDeAhorros.getCbu(), 1000);
    	
    	assertEquals(3900.0, cajaDeAhorros.getSaldo(), 0.01);
    }
    
    @Test(expected = SaldoInsuficienteException.class)
    public void queAlRealizar7ExtraccionesDe1000EnUnaCajaDeAhorroConSaldoInicialDe7000SoloLaUltimaLanceExcepcionSaldoInsuficiente() throws SaldoInsuficienteException, CuentaNoExisteException {
    	banco.retirar(cajaDeAhorros4.getCbu(), 1000);
    	banco.retirar(cajaDeAhorros4.getCbu(), 1000);
    	banco.retirar(cajaDeAhorros4.getCbu(), 1000);
    	banco.retirar(cajaDeAhorros4.getCbu(), 1000);
    	banco.retirar(cajaDeAhorros4.getCbu(), 1000);
    	banco.retirar(cajaDeAhorros4.getCbu(), 1000);
    	banco.retirar(cajaDeAhorros4.getCbu(), 1000);
    }
    
    @Test
    public void queSeCobreRecargoAlRealizarUnaExtraccionMayorAlSaldoEnUnaCuentaCorriente() throws SaldoInsuficienteException, CuentaNoExisteException {
    	banco.retirar(cuentaCorriente.getCbu(), 600);
    	
    	assertEquals(-105, cuentaCorriente.getSaldo(), 0.01);
    }
    
    @Test (expected = ClienteInexistenteException.class)
    public void queAlIntentarDarDeAltaUnaCuentaAUnClienteInexistenteLanceExcepcion() throws SaldoInsuficienteException, ClienteInexistenteException {
    	Cuenta cuentaFalsa = new CuentaCorriente("0", "0", 1);
    	
    	banco.agregarCuenta(cuentaFalsa);
    }
    
    @Test (expected = CuentaNoExisteException.class)
    public void queAlBuscarUnaCuentaPorCBUErroneoLanceExcepcion() throws CuentaNoExisteException {
    	banco.buscarCuenta("a");
    }
    
    @Test
    public void queSeObtengaElListadoDeClientesOrdenadosPorDni() throws SaldoInsuficienteException {
    	System.out.println(banco.getClientesOrdenadosPorDni());
    }
    
    @Test
    public void queSeObtengaUnListadoDeTodasLasCuentasOrdenadoPorSaldo() throws SaldoInsuficienteException {
    	System.out.println(banco.getCuentasOrdenadasPorSaldo());
    }
    
    @Test
    public void queSeObtengaUnListadoDeCuentasCorrientesOrdenadoPorSaldo() throws SaldoInsuficienteException {
    	System.out.println(banco.getCuentasCorrientesOrdenadasPorSaldo());
    }
    
    @Test
    public void queSeObtengaUnListadoDeCuentasCorrientesDeudorasOrdenadoPorSaldoDeudor() throws SaldoInsuficienteException, CuentaNoExisteException {
    	banco.retirar(cuentaCorriente.getCbu(), 600);
    	banco.retirar(cuentaCorriente2.getCbu(), 1200);
    	System.out.println(banco.getCuentasCorrientesOrdenadasPorSaldoDeudor());
    }
}