import java.util.Arrays;
import java.util.Objects;

public abstract class Conta implements ITaxas {

    private int numero;

    private Cliente dono;

    private double saldo;

    private double limite;

    private Operacao[] operacoes;

    private int proximaOperacao;

    private static int totalContas = 0;

    public Conta(int numero, Cliente dono, double saldo, double limite) {
        this.numero = numero;
        this.dono = dono;
        this.saldo = saldo;
        this.limite = limite;

        this.operacoes = new Operacao[1000];
        this.proximaOperacao = 0;

        Conta.totalContas++;
    }

    public void imprimirextratotaxas {
        double x = 0;

        System.out.println ("--- Extrato de Taxas ---");
        System.out.println ("Manutenção da conta:" + CalcularTaxas());

        Operacao[] op = getTotalOperacoes();
        for (int i = 0; i < op.length; i++) {
            if (op[i] != null && op[i].getTipo() == 's') {
                System.out.println ("Total Saque: R$" + op.CalcularTaxas());
                x +=op[i].CalcularTaxas();
            }
        }
        System.out.println ("Taxa total do saque: R$" + x);
    }

    public boolean sacar(double valor) {
        if (valor >= 0 && valor <= this.limite) {
            this.saldo -= valor;

            this.operacoes[proximaOperacao] = new OperacaoSaque(valor);
            this.proximaOperacao++;
            return true;
        }

        return false;
    }

    public void depositar(double valor) {
        this.saldo += valor;

        this.operacoes[proximaOperacao] = new OperacaoDeposito(valor);
        this.proximaOperacao++;
    }

    public boolean transferir(Conta destino, double valor) {
        boolean valorSacado = this.sacar(valor);
        if (valorSacado) {
            destino.depositar(valor);
            return true;
        }
        return false;
    }

    public String toString() {
        return dono.toString() + '\n' +
                "---\n" +
                "numero=" + numero + '\n' +
                "saldo=" + saldo + '\n' +
                "limite=" + limite;
    }
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o instanceof Conta) {
            Conta conta = (Conta) o;
            return numero == conta.numero;
        }
        return false;
    }
    public void imprimirExtrato() {
        System.out.println("======= Extrato Conta " + this.numero + "======");
        for(Operacao atual : this.operacoes) {
            if (atual != null) {
                System.out.println(atual);
            }
        }
        System.out.println("====================");
    }
    public abstract void getLimite();

    public int getNumero() {
        return numero;
    }

    public Cliente getDono() {
        return dono;
    }

    public double getSaldo() {
        return saldo;
    }

    public static int getTotalContas() {
        return Conta.totalContas;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setDono(Cliente dono) {
        this.dono = dono;
    }

    public void setLimite(double limite) {
        if (limite < 0)
            limite = 0;

        this.limite = limite;
    }
}