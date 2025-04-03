import javax.swing.*;

import java.text.DecimalFormat;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.lang.Double.parseDouble;

public class Interface {


    private Fornecedor[] fornecedor = new Fornecedor[3];
    private Produto[] produto = new Produto[3];
    private int indexProduto = 0;
    private int indexFornecedor = 0;

    public void menu() {
        int resposta;
        String aux = "Escolha uma opção: \n";
        aux += "1. Cadastrar produto: \n";
        aux += "2. Pesquisar produto por nome: \n";
        aux += "3. Pesquisar fornecedor por CNPJ: \n";
        aux += "4. Finalizar: \n";

        int opc;

        while (true) {
            opc = parseInt(showInputDialog(aux));
            if (opc == 4) {
                resposta = showConfirmDialog(null, "Tem certeza que deseja finalizar?");
                if (resposta == YES_OPTION) {
                    break;
                }
            }


            if (opc < 1 || opc > 4) {
                showInputDialog(null, "Opção inválida");
            } else {
                switch (opc) {
                    case 1:
                        cadastrarProduto();
                        break;

                    case 2:
                        pesquisarProduto();
                        break;

                    case 3:
                        pesquisar();
                        break;

                }
            }


        }

    }

    private void pesquisar() {
        Fornecedor fornecedor = pesquisarFornecedor();
        if (fornecedor != null) {
            String aux = "Fornecedor: " + fornecedor.getNome() + "\n";
            aux += "CNPJ: " + fornecedor.getCnpj() + "\n";
            showMessageDialog(null, aux);

        }
    }

    public void cadastrarProduto() {
        Fornecedor fornecedor = pesquisarFornecedor();
        String nome;
        double valor;
        int qtdEstoque;
        if (fornecedor == null) {
            fornecedor = cadastrarFornecedor();
        }

        nome = showInputDialog("Produto");
        valor = parseDouble(showInputDialog("Valor unitario"));
        qtdEstoque = parseInt(showInputDialog("Quantidade em Estoque"));
        produto[indexProduto] = new Produto(nome, valor, qtdEstoque, fornecedor);
        indexProduto++;
    }

    public void pesquisarProduto() {
       String nome = showInputDialog("Nome do produto");
       String aux = "";
        DecimalFormat df = new DecimalFormat("0.00");
       for (int i = 0; i < indexProduto; i++) {
           if (produto[i].getNome().equals(nome)) {
               aux += "Nome do produto:" + nome + "\n";
               aux += "Preço unitário R$ " + df.format(produto[i].getValor()) + "\n";
               aux += "Quanitade em estoque" + df.format(produto[i].getQuantidade()) + "\n";
               aux += "Fornecedor:" + produto[i].getFornecedor().getNome() + "\n";
               break;

           }
       }
       showMessageDialog(null, aux);

    }

    private Fornecedor pesquisarFornecedor() {
        long cnpj = parseLong(showInputDialog("CNPJ"));
        for (int i = 0; i < indexFornecedor; i++) {
            if (fornecedor[i].getCnpj() == cnpj) {
                return fornecedor[i];
            }
        }
        showConfirmDialog(null, cnpj + "Não encontrado");
        return null;
    }

    private Fornecedor cadastrarFornecedor() {
        String nome;
        long cnpj;
        Fornecedor f = null;
        if (indexFornecedor < fornecedor.length) {
            nome = showInputDialog("Nome do fornecedor:");
            cnpj = parseLong(showInputDialog("CNPJ:"));
            f = new Fornecedor(nome, cnpj);
            fornecedor[indexFornecedor] = f;
            indexFornecedor++;
        }
        return f;
    }



}
