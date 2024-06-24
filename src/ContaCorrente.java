public class ContaCorrente extends Conta implements ITaxas {

    public ContaCorrente (int numero, Cliente dono, double saldo, double limite) {
        super (numero, dono, saldo, limite);
        setlimite();
    }
    public void setlimite (double limite) {
        if (limite <= -100) {
            super.limite = -100;
            return false;
        } else {
            super.limite = limite;
            return true;
        }
    }
    public double CalcularTaxas {
        return getdono.CalcularTaxas();
    }
}
