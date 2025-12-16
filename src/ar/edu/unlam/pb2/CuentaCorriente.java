package ar.edu.unlam.pb2;

public class CuentaCorriente extends Cuenta{

	public CuentaCorriente(String cbu, String dni, double saldo) {
		super(cbu, dni, saldo);
	}
	
	@Override 
	public void retirar(double monto) throws SaldoInsuficienteException{
		double porcentajeDescubierto = 0.05d;
		
		if(this.saldo < monto) {
			this.saldo = (this.saldo - monto) - (porcentajeDescubierto * 100);
		}
		
	}
}