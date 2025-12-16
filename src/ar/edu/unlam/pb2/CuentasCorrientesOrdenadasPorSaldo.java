package ar.edu.unlam.pb2;

import java.util.Comparator;

public class CuentasCorrientesOrdenadasPorSaldo implements Comparator<Cuenta>{

	@Override
	public int compare(Cuenta c1, Cuenta c2) {
		System.out.println("Comparan2 "+c1+" con "+c2);
		if(c1.getSaldo() > c2.getSaldo()) {
			return 1;
		}
		else if(c1.getSaldo() < c2.getSaldo()) {
			return -1;
		}
		return 0;
	}
}