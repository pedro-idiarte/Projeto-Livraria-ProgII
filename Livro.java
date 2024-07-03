public class Livro {
    
    String titulo,editora, categoria;
    int ano, quantidadeEmEstoque, codigo;
    double valor;
    Filial filial;

    public Livro(int codigo, String titulo, int ano, String categoria, String editora,
    double valor, int quantidadeEmEstoque, Filial filial) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.ano = ano;
        this.categoria = categoria;
        this.editora = editora;
        this.valor = valor;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
        this.filial = filial;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setcategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public void setQuantidadeEmEstoque(int quantidadeEmEstoque) {
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }
    public double getValorTotalEmEstoque() {
        return valor * quantidadeEmEstoque;
    }

    public Filial getFilial() {
        return this.filial;
    }
    public Filial setFilial() {
        return filial;
    }
    public void info(){
        System.out.println(">>>>> Cod#"+ getCodigo());
        System.out.println("Título/Editora: "+ getTitulo() +"/"+ getEditora());
        System.out.println("Categoria: "+ getCategoria());
        System.out.println("Ano: "+ getAno());
        System.out.printf("Valor: R$%.2f \n", getValor());
        System.out.println("Estoque: "+ getQuantidadeEmEstoque() + " unidades");
        System.out.printf("Valor total em estoque: R$%.2f \n", getValorTotalEmEstoque());
        System.out.println();
    }
}
    
