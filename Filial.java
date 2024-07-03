import java.util.ArrayList;

public class Filial {
    String filialCodigo; 
    String nome, endereco, contato;
    ArrayList<Livro> estoque; 

    public Filial(String filialCodigo, String nome, String endereco, String contato) {
        this.filialCodigo = filialCodigo;
        this.nome = nome;
        this.endereco = endereco;
        this.contato = contato;
        this.estoque = new ArrayList<>();
    }

    public String getFilialCodigo() {
        return filialCodigo;
    }

    public void setFiliaoCodigo(String filialCodigo) {
        this.filialCodigo = filialCodigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public ArrayList<Livro> getEstoque() {
        return estoque;
    }

    public void setEstoque(ArrayList<Livro> estoque) {
        this.estoque = estoque;
    }

    public void info() {
        System.out.println("Filial " + nome);
        System.out.println("Código: " + filialCodigo);
        System.out.println("Endereço: " + endereco);
        System.out.println("Contato: " + contato);
        System.out.println("Estoque: ");
        for (Livro livro : estoque) {
            System.out.println("  - Cod#" + livro.getCodigo() + ": " + livro.getTitulo());
        }
        System.out.println();
    }

    public void adicionarLivro(Livro livro) {
        estoque.add(livro);
    }
}
