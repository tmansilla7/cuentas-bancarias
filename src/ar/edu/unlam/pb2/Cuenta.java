package ar.edu.unlam.pb2;

import java.util.Objects;

public class Cuenta {
	
	private String cbu;
	private String dniCliente;
	protected double saldo;
	
	public Cuenta(String cbu, String dni, double saldo) {
		this.cbu = cbu;
		this.dniCliente = dni;
		this.saldo = saldo;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(cbu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuenta other = (Cuenta) obj;
		return Objects.equals(cbu, other.cbu);
	}

	public void depositar(double monto) {
		this.saldo += monto;
	}
	
	public void retirar(double monto) throws SaldoInsuficienteException{
		if(this.saldo >= monto) {
			this.saldo -= monto;			
		}
	}

	public String getCbu() {
		return cbu;
	}

	public String getDniCliente() {
		return dniCliente;
	}

	public double getSaldo() {
		return saldo;
	}

	@Override
	public String toString() {
		return "Cuenta [cbu=" + cbu + ", dniCliente=" + dniCliente + ", saldo=" + saldo + "]";
	}
}