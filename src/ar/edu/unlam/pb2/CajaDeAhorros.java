package ar.edu.unlam.pb2;

public class CajaDeAhorros extends Cuenta{
	
	public final int CANTIDAD_MAXIMA_INTENTOS;
	public int cantidadDeIntentos;

	public CajaDeAhorros(String cbu, String dni, double saldo) {
		super(cbu, dni, saldo);
		this.CANTIDAD_MAXIMA_INTENTOS = 5;
		this.cantidadDeIntentos = 0;
	}
	
	@Override 
	public void retirar(double monto) throws SaldoInsuficienteException{
		int costoPorExtraccion = 100;
		
		if(this.saldo >= monto) {
			if(this.CANTIDAD_MAXIMA_INTENTOS > this.cantidadDeIntentos) {
				this.saldo -= monto;				
			}
			else {
				this.saldo = this.saldo - monto - costoPorExtraccion;
			}
			this.cantidadDeIntentos++;
		}
		else {
			throw new SaldoInsuficienteException();
		}
	}
	
	public int getCantidadDeExtraccionesRealizadas() {
		return this.cantidadDeIntentos;
	}
	
}