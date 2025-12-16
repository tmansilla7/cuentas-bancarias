package ar.edu.unlam.pb2;

import java.util.Set;
import java.util.HashSet;
import java.util.TreeSet;

public class Banco implements EntidadBancaria{
	
	private Set<Cliente> clientes;
	private Set<Cuenta> cuentas;

	public Banco() {
		this.clientes = new HashSet<Cliente>();
		this.cuentas = new HashSet<Cuenta>();
	}
	
	@Override
	public void agregarCliente(Cliente cliente) throws ClienteDuplicadoException{
		if(clientes.contains(cliente)) {
			throw new ClienteDuplicadoException();
		}
		else {
			this.clientes.add(cliente);
		}
	}

	@Override
	public void agregarCuenta(Cuenta unaCuenta) throws ClienteInexistenteException {
		Cliente clienteFalso = new Cliente(unaCuenta.getDniCliente(), "Juan");
		
		if(clientes.contains(clienteFalso)) {
			this.cuentas.add(unaCuenta);
		}
		else {
			throw new ClienteInexistenteException();
		}
		
	}

	@Override
	public void depositar(String cbu, double monto) throws CuentaNoExisteException{
		Cuenta cuenta = new Cuenta(cbu, "1", monto);

		if(!cuentas.contains(cuenta)) {
			throw new CuentaNoExisteException();
		}
		else if(monto < 0){
			throw new IllegalArgumentException();
		}
		else {
			cuenta.depositar(monto);			
		}
	}

	@Override
	public void retirar(String cbu, double monto) throws CuentaNoExisteException, SaldoInsuficienteException{
		Cuenta cuentaEncontrada = null;
		
		if(monto <= 0){
			throw new IllegalArgumentException();
		}

		for(Cuenta cuenta: cuentas) {
			if(cuenta.getCbu().equalsIgnoreCase(cbu)) {
				cuentaEncontrada = cuenta;
				break;
			}
		}
		
		if(cuentaEncontrada == null) {			
			throw new CuentaNoExisteException();
		}
		else if(cuentaEncontrada.getSaldo() < monto && (!(cuentaEncontrada instanceof CuentaCorriente))){			
			throw new SaldoInsuficienteException();
		}
		else {
			cuentaEncontrada.retirar(monto);			
		}
		
	}

	@Override
	public void transferir(String cbuOrigen, String cbuDestino, double monto)
			throws CuentaNoExisteException, FondosInsuficientesException {
		
		
		
	}
	
	public void buscarCuenta(String cbu) throws CuentaNoExisteException{
		for(Cuenta c:cuentas) {
			if(!c.getCbu().equalsIgnoreCase(cbu)) {
				throw new CuentaNoExisteException();
			}
		}
	}
	
	public Set<Cliente> getClientesOrdenadosPorDni(){
		Set<Cliente> clientesOrdenados = new TreeSet<>(clientes);
		
		return clientesOrdenados;
	}
	
	public Set<Cuenta> getCuentasOrdenadasPorSaldo(){
		Set<Cuenta> cuentasOrdenadas = new TreeSet<>(new CuentasOrdenadasPorSaldo());
		
		for(Cuenta c:cuentas) {
			cuentasOrdenadas.add(c);
		}
		
		return cuentasOrdenadas;
	}
	
	public Set<Cuenta> getCuentasCorrientesOrdenadasPorSaldo(){
		Set<Cuenta> cuentasOrdenadas = new TreeSet<>(new CuentasCorrientesOrdenadasPorSaldo());
		
		for(Cuenta c:cuentas) {
			if(c instanceof CuentaCorriente) {
				cuentasOrdenadas.add(c);				
			}
		}
		
		return cuentasOrdenadas;
	}

	public Set<Cuenta> getCuentasCorrientesOrdenadasPorSaldoDeudor(){
		Set<Cuenta> cuentasOrdenadas = new TreeSet<>(new CuentasCorrientesOrdenadasPorSaldoDeudor());
		
		for(Cuenta c:cuentas) {
			if(c instanceof CuentaCorriente && c.getSaldo() < 0) {
				cuentasOrdenadas.add(c);				
			}
		}
		
		return cuentasOrdenadas;
	}
	
	public int getCantidadClientes() {
		return clientes.size();
	}
	
	public int getCantidadCuentas() {
		return cuentas.size();
	}
}