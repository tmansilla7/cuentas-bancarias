package ar.edu.unlam.pb2;

public interface EntidadBancaria {
  void depositar(String cbu, double monto) throws CuentaNoExisteException;
  void retirar(String cbu, double monto) throws CuentaNoExisteException, SaldoInsuficienteException;
  void transferir(String cbuOrigen, String cbuDestino, double monto) throws CuentaNoExisteException, FondosInsuficientesException;
  void agregarCuenta(Cuenta unaCuenta) throws ClienteInexistenteException;
  void agregarCliente(Cliente unCliente) throws ClienteDuplicadoException;
}