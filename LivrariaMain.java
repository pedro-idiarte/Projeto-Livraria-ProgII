import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

//import org.w3c.dom.Text;

public class LivrariaMain {
    public static void main(String[] args) {
        ArrayList<Livro> livros = new ArrayList<>();
        ArrayList<Filial> filiais = new ArrayList<>();

        Scanner tec = new Scanner(System.in);
        int opcao;
        int opcaoFim = -1;
        boolean estoqueCarregado = false;

        do {
            System.out.println("\n Livraria");
            System.out.println(" ");
            System.out.println("1) Cadastrar novo livro");
            System.out.println("2) Listar livros");
            System.out.println("3) Buscar livros por nome");
            System.out.println("4) Buscar livros por categoria");
            System.out.println("5) Buscar livros por preço");
            System.out.println("6) Busca por quantidade em estoque");
            System.out.println("7) Valor total no estoque");
            System.out.println("8) Carregar o estoque");
            System.out.println("9) Atualizar o estoque");
            System.out.println("10) Cadastrar nova filial");
            System.out.println("11) Listagem de estoque");
            System.out.println("12) Buscar livro por código");
            System.out.println("0) Encerrar atividades");
            System.out.println(" ");
            System.out.print("Escolha uma opção: ");
            opcao = tec.nextInt();
            System.out.println(" ");

            switch (opcao) {
                case 1:
                    try {
                        cadastrarLivro(livros, filiais, tec);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    listarLivros(livros);
                    break;
                case 3:
                    buscarLivrosPorNome(livros, filiais, tec);
                    break;
                case 4:
                    buscarLivrosPorCategoria(livros, filiais, tec);
                    break;
                case 5:
                    buscarLivrosPorPreco(livros, filiais, tec);
                    break;
                case 6:
                    buscarLivrosPorQuantidadeEmEstoque(livros, filiais, tec);
                    break;
                case 7:
                    calcularValorTotalNoEstoque(livros);
                    break;
                case 8:
                    try {
                        carregaEstoque(livros, filiais, null, estoqueCarregado);
                        estoqueCarregado = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 9:
                    try {
                        atualizaEstoque(livros, filiais);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 10:
                    try {
                        cadastrarFilial(filiais, tec);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 11:
                    System.out.print("Digite o código da filial: ");
                    String codigoFilial = tec.next();
                    listagemDeEstoque(filiais, codigoFilial);
                    break;
                case 12:
                    buscarLivroPorCodigo(livros, filiais, tec);
                    break;
                case 0:
                    encerraPrograma(livros, filiais, opcaoFim, tec);
                    break;
            }

        } while (opcao != 0);

        tec.close();

    }

    public static void cadastrarLivro(ArrayList<Livro> livros, ArrayList<Filial> filiais, Scanner tec)
            throws Exception {
        System.out.println("Cadastro de Livro");
        System.out.println(" ");

        tec.nextLine();

        System.out.print("Código da Filial: ");
        String filialCodigo = tec.nextLine();

        Filial filial = null;

        for (Filial f : filiais) {
            if (f.getFilialCodigo().equals(filialCodigo)) {
                filial = f;
                break;
            }
        }

        if (filial == null) {
            System.out.println(" ");
            System.out.println("Filial não encontrada. Vamos criar uma nova filial.");
            cadastrarFilial(filiais, tec);
            filial = filiais.get(filiais.size() - 1);
        }

        System.out.print("Código do Livro: ");
        int codigo = tec.nextInt();
        tec.nextLine();

        System.out.print("Título: ");
        String titulo = tec.nextLine();

        System.out.print("Editora: ");
        String editora = tec.nextLine();

        System.out.print("Categoria: ");
        String categoria = tec.nextLine();

        System.out.print("Ano: ");
        int ano = tec.nextInt();

        System.out.print("Valor: R$ ");
        double valor = tec.nextDouble();

        System.out.print("Quantidade em Estoque: ");
        int quantidadeEmEstoque = tec.nextInt();

        Livro novoLivro = new Livro(codigo, titulo, ano, categoria, editora, valor, quantidadeEmEstoque, filial);
        livros.add(novoLivro);
        filial.adicionarLivro(novoLivro);

        System.out.println(" ");
        System.out.println("Livro cadastrado com sucesso!");
        System.out.println(" ");
    }

    public static void cadastrarFilial(ArrayList<Filial> filiais, Scanner tec) throws Exception {
        System.out.println(" ");
        System.out.println("Cadastro de Filial");
        System.out.println(" ");

        System.out.print("Código: ");
        tec.nextLine();
        String filialCodigo = tec.nextLine();

        boolean filialExistente;
        filialExistente = false;

        for (Filial f : filiais) {
            if (f.getFilialCodigo().equals(filialCodigo)) {
                filialExistente = true;
                break;
            }
        }
        if (filialExistente == true) {
            System.out.println("Filial " + filialCodigo + " já existe.");
        } else {

            System.out.print("Nome: ");
            String nome = tec.nextLine();

            System.out.print("Endereço: ");
            String endereco = tec.nextLine();

            System.out.print("Contato: ");
            String contato = tec.nextLine();

            Filial novaFilial = new Filial(filialCodigo, nome, endereco, contato);
            filiais.add(novaFilial);

            System.out.println(" ");
            System.out.println("Filial cadastrada com sucesso!");
            System.out.println(" ");
        }

    }

    public static void listarLivros(ArrayList<Livro> livros) {
        System.out.println("Listagem de Livros");
        System.out.println(" ");

        if (livros.isEmpty()) {
            System.out.println(" ");
            System.out.println("Nenhum livro cadastrado.");
            System.out.println(" ");
        } else {
            for (Livro livro : livros) {
                livro.info();
            }
        }
    }

    public static void listagemDeEstoque(ArrayList<Filial> filiais, String filialCodigo) {
        Filial filial = procurarFilialPorCodigo(filiais, filialCodigo);

        if (filial == null) {
            System.out.println("Filial com o código " + filialCodigo + " não foi encontrada.");
            return;
        }

        System.out.println();
        System.out.println("Listagem de Estoque na Filial " + filial.getNome());
        System.out.println();

        for (Livro livro : filial.getEstoque()) {
            System.out.println(">>>>> Cod#" + livro.getCodigo());
            System.out.println("Título/Editora: " + livro.getTitulo() + "/" + livro.getEditora());
            System.out.println("Categoria: " + livro.getCategoria());
            System.out.println("Ano: " + livro.getAno());
            System.out.printf("Valor: R$%.2f\n", livro.getValor());
            System.out.println("Estoque: " + livro.getQuantidadeEmEstoque() + " unidades");
            System.out.printf("Valor total em estoque: R$%.2f\n", livro.getValorTotalEmEstoque());
            System.out.println(" ");
        }

        double valorTotalFilial = calcularValorTotalEstoqueFilial(filial.getEstoque(), filialCodigo);
        System.out.printf("Valor total do estoque da Filial %s: R$%.2f\n", filialCodigo, valorTotalFilial);
    }

    public static void buscarLivrosPorNome(ArrayList<Livro> livros, ArrayList<Filial> filiais, Scanner tec) {
        System.out.println("Buscar Livros por Nome");
        System.out.println(" ");
        tec.nextLine();
        System.out.print("Digite o número da filial: ");
        String filialCodigo = tec.nextLine();
        Filial filial = procurarFilialPorCodigo(filiais, filialCodigo);

        if (filial == null) {
            System.out.println("Filial com o código " + filialCodigo + " não foi encontrada.");
            return;
        }

        System.out.print("Digite o nome do livro: ");
        String nome = tec.nextLine().toLowerCase();
        System.out.println(" ");

        boolean encontrouLivro = false;

        for (Livro livro : filial.getEstoque()) {
            if (livro.getTitulo().toLowerCase().contains(nome)) {
                if (!encontrouLivro) {
                    System.out.println(
                            "Livros encontrados na Filial " + filial.getNome() + " com o nome '" + nome + "':");
                    System.out.println(" ");
                    encontrouLivro = true;
                }

                System.out.println(">>>>> Cod#" + livro.getCodigo());
                System.out.println("Título/Editora: " + livro.getTitulo() + "/" + livro.getEditora());
                System.out.println("Categoria: " + livro.getCategoria());
                System.out.println("Ano: " + livro.getAno());
                System.out.println("Valor: R$ " + livro.getValor());
                System.out.println("Estoque: " + livro.getQuantidadeEmEstoque() + " unidades");
                System.out.println("Valor total em estoque: R$ " + livro.getValorTotalEmEstoque());
                System.out.println(" ");
            }
        }

        if (!encontrouLivro) {
            System.out.println("Nenhum livro encontrado na Filial " + filial.getNome() + " com o nome '" + nome + "'.");
        }
    }

    public static void buscarLivrosPorCategoria(ArrayList<Livro> livros, ArrayList<Filial> filiais, Scanner tec) {
        System.out.println("Buscar Livros por Categoria");
        System.out.println(" ");

        tec.nextLine();
        System.out.print("Digite o número da filial: ");
        String filialCodigo = tec.nextLine();
        Filial filial = procurarFilialPorCodigo(filiais, filialCodigo);

        if (filial == null) {
            System.out.println("Filial com o código " + filialCodigo + " não foi encontrada.");
            return;
        }

        System.out.print("Digite a categoria desejada: ");
        String categoria = tec.nextLine().toLowerCase();
        System.out.println(" ");

        boolean encontrouLivro = false;

        for (Livro livro : filial.getEstoque()) {
            if (livro.getCategoria().toLowerCase().contains(categoria)) {
                if (!encontrouLivro) {
                    System.out.println(
                            "Livros encontrados na Filial " + filial.getNome() + " na categoria '" + categoria + "':");
                    System.out.println(" ");
                    encontrouLivro = true;
                }

                System.out.println(">>>>> Cod#" + livro.getCodigo());
                System.out.println("Título/Editora: " + livro.getTitulo() + "/" + livro.getEditora());
                System.out.println("Categoria: " + livro.getCategoria());
                System.out.println("Ano: " + livro.getAno());
                System.out.println("Valor: R$ " + livro.getValor());
                System.out.println("Estoque: " + livro.getQuantidadeEmEstoque() + " unidades");
                System.out.println("Valor total em estoque: R$ " + livro.getValorTotalEmEstoque());
                System.out.println(" ");
            }
        }

        if (!encontrouLivro) {
            System.out.println(
                    "Nenhum livro encontrado na Filial " + filial.getNome() + " na categoria '" + categoria + "'.");
        }
    }

    public static void buscarLivrosPorPreco(ArrayList<Livro> livros, ArrayList<Filial> filiais, Scanner tec) {
        System.out.println("Buscar Livros por Preço");
        System.out.println(" ");

        tec.nextLine();
        System.out.print("Digite o número da filial: ");
        String filialCodigo = tec.nextLine();
        Filial filial = procurarFilialPorCodigo(filiais, filialCodigo);

        if (filial == null) {
            System.out.println("Filial com o código " + filialCodigo + " não foi encontrada.");
            return;
        }

        System.out.print("Digite o valor máximo desejado: R$ ");
        double valorMaximo = tec.nextDouble();
        System.out.println(" ");

        boolean encontrouLivro = false;

        for (Livro livro : filial.getEstoque()) {
            if (livro.getValor() <= valorMaximo) {
                if (!encontrouLivro) {
                    System.out.println("Livros encontrados na Filial " + filial.getNome() + " com preço até R$ "
                            + valorMaximo + ":");
                    System.out.println(" ");
                    encontrouLivro = true;
                }

                System.out.println(">>>>> Cod#" + livro.getCodigo());
                System.out.println("Título/Editora: " + livro.getTitulo() + "/" + livro.getEditora());
                System.out.println("Categoria: " + livro.getCategoria());
                System.out.println("Ano: " + livro.getAno());
                System.out.println("Valor: R$ " + livro.getValor());
                System.out.println("Estoque: " + livro.getQuantidadeEmEstoque() + " unidades");
                System.out.println("Valor total em estoque: R$ " + livro.getValorTotalEmEstoque());
                System.out.println(" ");
            }
        }

        if (!encontrouLivro) {
            System.out.println(
                    "Nenhum livro encontrado na Filial " + filial.getNome() + " com preço até R$ " + valorMaximo + ".");
        }
    }

    public static void buscarLivrosPorQuantidadeEmEstoque(ArrayList<Livro> livros, ArrayList<Filial> filiais,
            Scanner tec) {
        System.out.println("Buscar Livros por Quantidade em Estoque");
        System.out.println(" ");

        tec.nextLine();
        System.out.print("Digite o número da filial: ");
        String filialCodigo = tec.nextLine();
        Filial filial = procurarFilialPorCodigo(filiais, filialCodigo);

        if (filial == null) {
            System.out.println("Filial com o código " + filialCodigo + " não foi encontrada.");
            return;
        }

        System.out.print("Digite a quantidade desejada: ");
        int quantidadeDesejada = tec.nextInt();
        System.out.println(" ");

        boolean encontrouLivro = false;

        for (Livro livro : filial.getEstoque()) {
            if (livro.getQuantidadeEmEstoque() >= quantidadeDesejada) {
                if (!encontrouLivro) {
                    System.out.println("Livros encontrados na Filial " + filial.getNome()
                            + " com quantidade igual ou maior que " + quantidadeDesejada + ":");
                    System.out.println(" ");
                    encontrouLivro = true;
                }

                System.out.println(">>>>> Cod#" + livro.getCodigo());
                System.out.println("Título/Editora: " + livro.getTitulo() + "/" + livro.getEditora());
                System.out.println("Categoria: " + livro.getCategoria());
                System.out.println("Ano: " + livro.getAno());
                System.out.println("Valor: R$ " + livro.getValor());
                System.out.println("Estoque: " + livro.getQuantidadeEmEstoque() + " unidades");
                System.out.println("Valor total em estoque: R$ " + livro.getValorTotalEmEstoque());
                System.out.println(" ");
            }
        }

        if (!encontrouLivro) {
            System.out.println("Nenhum livro encontrado na Filial " + filial.getNome()
                    + " com quantidade igual ou maior que " + quantidadeDesejada + ".");
        }
    }

    public static void buscarLivroPorCodigo(ArrayList<Livro> livros, ArrayList<Filial> filiais, Scanner tec) {
        System.out.println("Buscar Livro por Código");
        System.out.println(" ");

        tec.nextLine();
        System.out.print("Digite o código do livro: ");
        int codigoLivro = tec.nextInt();
        System.out.println(" ");

        boolean encontrouLivro = false;
        double valorTotalEstoque = 0.0;

        for (Filial filial : filiais) {
            for (Livro livro : filial.getEstoque()) {
                if (livro.getCodigo() == codigoLivro) {
                    if (!encontrouLivro) {
                        System.out.println("Livro encontrado:");
                        System.out.println(" ");
                        System.out.println(">>>>> Cod#" + livro.getCodigo());
                        System.out.println("Título/Editora: " + livro.getTitulo() + "/" + livro.getEditora());
                        System.out.println("Categoria: " + livro.getCategoria());
                        System.out.println("Ano: " + livro.getAno());
                        encontrouLivro = true;
                    }

                    System.out.printf("Valor: R$%.2f >>> Filial %s, estoque: %d unidades",
                            livro.getValor(), filial.getNome(), livro.getQuantidadeEmEstoque());
                    System.out.println(" ");
                    valorTotalEstoque += livro.getValorTotalEmEstoque();
                }
            }
        }

        if (encontrouLivro) {
            System.out.printf("Valor total em estoque: R$%.2f\n", valorTotalEstoque);
        } else {
            System.out.println("Nenhum livro encontrado com o código " + codigoLivro + ".");
        }
    }

    public static void calcularValorTotalNoEstoque(ArrayList<Livro> livros) {
        System.out.println(" ");
        System.out.println("Valor Total no Estoque");

        if (livros.isEmpty()) {
            System.out.println(" ");
            System.out.println("Nenhum livro cadastrado.");
            System.out.println(" ");
        } else {
            double total = 0.0;
            for (Livro livro : livros) {
                total += livro.getValor() * livro.getQuantidadeEmEstoque();
            }

            System.out.println(" ");
            System.out.println("O valor total em estoque é: R$ " + total);
            System.out.println(" ");
        }
    }

    static void carregaEstoque(ArrayList<Livro> livros, ArrayList<Filial> filiais, String linha,
            boolean estoqueCarregado) throws Exception {
        try {
            FileInputStream arquivoEstoque = new FileInputStream("Estoque.txt");
            Scanner TextoEstoque = new Scanner(arquivoEstoque);
            System.out.println("O estoque foi carregado com sucesso!");
            TextoEstoque.close();
        } catch (Exception e) {
            System.out.println("Erro ao carregar o estoque.");
            e.printStackTrace();
            return;
        }

        FileInputStream arquivoEstoque = new FileInputStream("Estoque.txt");
        Scanner TextoEstoque = new Scanner(arquivoEstoque);

        if (estoqueCarregado == false) {
            while (TextoEstoque.hasNextLine()) {
                String filial_linha = TextoEstoque.nextLine();
                String[] SL = filial_linha.split(",");

                if (SL.length >= 4) {
                    String filialCodigo = SL[0];
                    Filial filial = procurarFilialPorCodigo(filiais, filialCodigo);
                    String nome = SL[1];
                    String endereco = SL[2];
                    String contato = SL[3];

                    if (filial == null) {
                        filial = new Filial(filialCodigo, nome, endereco, contato);
                        filiais.add(filial);
                    }

                    while (TextoEstoque.hasNextLine()) {
                        String livro_linha = TextoEstoque.nextLine();
                        if (livro_linha.isEmpty()) {
                            break;
                        }

                        String[] livro = livro_linha.split(",");
                        int codigo = Integer.parseInt(livro[0]);
                        String titulo = livro[1];
                        int ano = Integer.parseInt(livro[2]);
                        String categoria = livro[3];
                        String editora = livro[4];
                        double valor = Double.parseDouble(livro[5].replace("R$", "").replace(",", ""));
                        int quantidadeEmEstoque = Integer.parseInt(livro[6]);

                        Livro livroEstoque = new Livro(codigo, titulo, ano, categoria, editora, valor,
                                quantidadeEmEstoque,
                                filial);
                        livros.add(livroEstoque);
                        filial.adicionarLivro(livroEstoque);
                    }
                }
            }

        }
        TextoEstoque.close();
    }

    static void atualizaEstoque(ArrayList<Livro> livros, ArrayList<Filial> filiais) throws Exception {
        FileWriter arquivoEstoque = new FileWriter("Estoque.txt");

        for (Filial filial : filiais) {
            arquivoEstoque.write(filial.getFilialCodigo() + ","
                    + filial.getNome() + ","
                    + filial.getEndereco() + ","
                    + filial.getContato() + "\n");

            for (Livro livro : filial.getEstoque()) {
                arquivoEstoque.write(livro.getCodigo() + ","
                        + livro.getTitulo() + ","
                        + livro.getAno() + ","
                        + livro.getCategoria() + ","
                        + livro.getEditora() + ","
                        + "R$" + livro.getValor() + ","
                        + livro.getQuantidadeEmEstoque() + "\n");
            }
            arquivoEstoque.write("\n");
        }

        System.out.println("O estoque foi atualizado!");
        arquivoEstoque.close();
        System.out.println(" ");
    }

    static Filial procurarFilialPorCodigo(ArrayList<Filial> filiais, String codigoFilial) {
        for (Filial filial : filiais) {
            if (filial.getFilialCodigo().equals(codigoFilial)) {
                return filial;
            }
        }
        return null;
    }

    public static double calcularValorTotalEstoqueFilial(ArrayList<Livro> livros, String filialCodigo) {
        double total = 0.0;

        for (Livro livro : livros) {
            if (livro.getFilial().getFilialCodigo().equals(filialCodigo)) {
                total += livro.getValorTotalEmEstoque();
            }
        }

        return total;
    }

    public static void encerraPrograma(ArrayList<Livro> livros, ArrayList<Filial> filiais, int opcaoFim, Scanner tec) {
        System.out.println(" ");
        System.out.println("Você gostaria de atualizar o estoque?");
        System.out.println(" ");
        System.out.println("1) Sim");
        System.out.println("2) Não");
        System.out.println(" ");
        System.out.print("Escolha uma opção: ");
        opcaoFim = tec.nextInt();
        System.out.println();

        switch (opcaoFim) {
            case 1:
                try {
                    atualizaEstoque(livros, filiais);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case 2: {
                System.out.println(" ");
                System.out.println("Programa encerrado!");
                System.out.println(" ");
                opcaoFim = 0;
            }
                break;
        }
    }
}
