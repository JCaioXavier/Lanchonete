package Packages.Util;

import Packages.Entidades.Carrinho;
import Packages.Entidades.HistoricoPedido;
import Packages.Entidades.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static Packages.Util.DadosEstaticos.*;
import static Packages.Util.DadosEstaticos.gerarCarrinho;

public class GerenciadorDeHistoricoPedido {
   /*public static List<HistoricoPedido> adicionarHistoricoPedido (List<HistoricoPedido> listaDePedidos, int idCliente, double total) {//-------------------------------
        HistoricoPedido addHistorico = new HistoricoPedido();

        listaDePedidos.add(addHistorico);


        addHistorico.id_historicoPedido = listaDePedidos.size();

        try {
             for (int i = 1; i <= gerarCarrinho().size(); i++) {  // Corrigido o índice de 1 para 0
                  addHistorico.produtos = gerarCarrinho().get(i).nomeC;  // Usar índice correto
                  System.out.println(addHistorico.produtos);
             }
             System.out.println(listaDePedidos);

             addHistorico.total = total;
             addHistorico.endereco = gerarClientes().get(idCliente).endereco;
        } catch (NoSuchElementException erro) {
             System.out.println("ERROR");
        }

        return listaDePedidos;
   }*/
   public static List<HistoricoPedido> adicionarHistoricoPedido(List<HistoricoPedido> listaHistorico, List<Carrinho> listaCarrinho, int idCliente, double total) {
       // Cria um novo pedido
       HistoricoPedido addHistorico = new HistoricoPedido();

       // Inicializa a lista de produtos
       addHistorico.produtos = new ArrayList<>(); // Certifique-se de importar java.util.ArrayList

       // Adiciona o pedido à lista de históricos
       listaHistorico.add(addHistorico);

       // Define o ID do histórico com base no tamanho da lista
       addHistorico.id_historicoPedido = listaHistorico.size();

       System.out.println("Número de itens no carrinho: " + listaCarrinho.size());

       // Itera sobre a lista de produtos no carrinho
       for (int i = 0; i < listaCarrinho.size(); i++) {  // Inicia do índice 0
           // Adiciona cada nome de produto à lista de produtos do histórico
           addHistorico.produtos.add(listaCarrinho.get(i).nomeC); // Adiciona o produto à lista
           System.out.println("Produto adicionado: " + listaCarrinho.get(i).nomeC);
       }

       // Define o total e o endereço
       addHistorico.total = total;
       addHistorico.endereco = gerarClientes().get(idCliente).endereco;

       // Exibe a lista de históricos
       System.out.println(listaHistorico);

       return listaHistorico;
   }
}
