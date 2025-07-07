import java.util.ArrayList;

public class Player {
    private String nome;
    private double saldo;
    private Node posicaoAtual;
    private ArrayList<Imovel> propriedades;

    public Player(String nome, double dinheiroInicial) {
        this.nome = nome;
        this.saldo = dinheiroInicial;
        this.propriedades = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public Node getPosicaoAtual() {
        return posicaoAtual;
    }

    public void setPosicaoAtual(Node posicaoAtual) {
        this.posicaoAtual = posicaoAtual;
    }

    public ArrayList<Imovel> getPropriedades() {
        return propriedades;
    }

    public void adicionarPropriedade(Imovel imovel) {
        propriedades.add(imovel);
    }
}