//GerenciadorDeFuncionarios

package Packages.Util;

import Packages.Entidades.Cliente;
import Packages.Entidades.Funcionario;
import Packages.Entidades.Ingrediente;
import Packages.Entidades.Produto;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GerenciadorDeFuncionarios {

    public static void confirmarDadosFuncionarios (Funcionario funcionario){//PEDE A CONFIRMAÇÃO DOS DADOS -------------
        System.out.println("Seus dados estão corretos?\n" +
                "Usuario: "+funcionario.usuario + "\n" +
                "Senha: " +funcionario.senha+ "\n" +
                "Nome: " +funcionario.nome+ "\n" +
                "CPF: " +funcionario.cpf+ "\n" +
                "Telefone: " +funcionario.telefone+ "\n" +
                "Endereço: " +funcionario.endereco + "\n");
    }

    public static Funcionario cadastrarFuncionario(List<Funcionario> funcionariosExistentes,int i){ //CADASTRO --------------
        Funcionario novoFuncionario = new Funcionario();
        Scanner scanner = new Scanner(System.in);

        int id = funcionariosExistentes.size();

        if (i != (-1)){//é usado quando o adm for editar um funcionario cadastrado
            novoFuncionario.id_funcionario = i;
        }else {
            novoFuncionario.id_funcionario = id +1;
        }

        checkadordeFuncionarios(novoFuncionario, funcionariosExistentes);

        do{
            System.out.print("Digite a senha: ");
            novoFuncionario.senha = scanner.nextLine();

            while(novoFuncionario.senha.length() > 50){
                System.out.println("Senha não pode ser maior que 50 caracteres.");
                System.out.print("Digite a senha: ");
                novoFuncionario.senha = scanner.nextLine();
            }
            if(novoFuncionario.senha.isEmpty()){
                System.out.println("Senha não pode ser vazio ou apenas espaços! Digite novamente.");
            }
        }while(novoFuncionario.senha.isEmpty());

        do{
            System.out.print("Digite o nome do(a) funcionário(a): ");
            novoFuncionario.nome = scanner.nextLine();

            while(novoFuncionario.nome.length() > 100){
                System.out.println("Nome não pode ser maior que 100 caracteres.");
                System.out.print("Digite o nome do(a) funcionário(a): ");
                novoFuncionario.nome = scanner.nextLine();
            }
            if(novoFuncionario.nome.isEmpty()){
                System.out.println("Nome não pode ser vazio ou apenas espaços! Digite novamente.");
            }
        }while(novoFuncionario.nome.isEmpty());

        System.out.print("Digite o CPF do(a) funcionário(a): ");
        novoFuncionario.cpf = scanner.nextLine();

        while(novoFuncionario.cpf.length() != 11){
            System.out.println("CPF tem que ser igual a 11 digitos.");
            System.out.print("Digite o CPF do funcionário(a): ");
            novoFuncionario.cpf = scanner.nextLine();
        }

        System.out.print("Digite telefone do(a) funcionário(a): ");
        novoFuncionario.telefone = scanner.nextLine();

        while(novoFuncionario.telefone.length() < 8 || novoFuncionario.nome.length() > 14){
            System.out.println("O telefone tem que ter ao menos 9 digitos.");
            System.out.print("Digite telefone do funcionário(a): ");
            novoFuncionario.telefone = scanner.nextLine();
        }

        do{
            System.out.print("Digite o endereço do(a) funcionário(a): ");
            novoFuncionario.endereco = scanner.nextLine();

            while(novoFuncionario.endereco.length() > 100){
                System.out.println("Endereço não pode ser maior que 100 caracteres.");
                System.out.print("Digite o endereço do(a) funcionário(a): ");
                novoFuncionario.endereco = scanner.nextLine();
            }
            if(novoFuncionario.endereco.isEmpty()){
                System.out.println("Nome não pode ser vazio ou apenas espaços! Digite novamente.");
            }
        }while(novoFuncionario.endereco.isEmpty());

        return novoFuncionario;
    }

    private static void checkadordeFuncionarios(Funcionario novoFuncionario, List<Funcionario> funcionariosExistentes) {
        Scanner scanner = new Scanner(System.in);
        boolean usuarioExistente;

        do {
            usuarioExistente = false;

            do {
                System.out.print("Digite o usuario do funcionário(a): ");
                novoFuncionario.usuario = scanner.nextLine().trim();

                if (novoFuncionario.usuario.isEmpty()) {
                    System.out.println("Usuário não pode ser vazio ou apenas espaços! Digite novamente.");
                }
            } while (novoFuncionario.usuario.isEmpty());

            for (Funcionario funcionario : funcionariosExistentes) {
                if (funcionario != novoFuncionario && funcionario.usuario.equals(novoFuncionario.usuario)) {
                    System.out.println("Usuário já cadastrado. Tente novamente.");
                    usuarioExistente = true;
                    break;
                }
                else{
                    break;
                }
            }

        } while (usuarioExistente);
    }


    public static int loginFuncionario(List<Funcionario> funcionarios, int i){//LOGIN ----------------------------------
        Scanner scanner = new Scanner(System.in);

        String usuario, senha;
        Funcionario loginFuncionario = null;

        do {
            System.out.print("Digite seu usuario: ");
            usuario = scanner.nextLine();

            for (Funcionario funcionario : funcionarios) {
                if (funcionario.usuario.equals(usuario)) {
                    loginFuncionario = funcionario;
                    break;
                }
            }
            if(loginFuncionario == null){
                System.out.println("Usuario não encontrado! Digite novamente.");
            }
        } while (loginFuncionario == null);

        do {
            System.out.print("Digite sua senha: ");

            senha = scanner.nextLine();

            if (!loginFuncionario.senha.equals(senha)) {
                System.out.println("Senha incorreta! Digite novamente.");
            }
        } while (!loginFuncionario.senha.equals(senha));
        System.out.println("Logado com sucesso!");

        if(loginFuncionario.usuario.equals("admin") && loginFuncionario.senha.equals("admin")){
            //i = 1;
            return i + 1;
        }
        return i;
    }
    public static int pegarFuncionario(List<Funcionario> funcionarios){//PEGAR ID DO FUNCIONÁRIO -----------------------
        Scanner scanner = new Scanner(System.in);
        int idFuncionario = 0;
        do {
            try {
                idFuncionario = scanner.nextInt(); //2 -thiago

                if (idFuncionario == 1) {
                    System.out.println("Administrador não pode ser alterado");
                }
                else if(idFuncionario < 0 || idFuncionario == 0 || idFuncionario > funcionarios.size()){
                    System.out.println("Não existe funcionário com esse id! Digite novamente.");
                }
                //0-adm, 1-thiago
            }catch(IndexOutOfBoundsException erro){
                System.out.println("[ERROR], digite uma opção valida. ");
            }
        }while(idFuncionario == 1 || idFuncionario < 0 || idFuncionario == 0 || idFuncionario > funcionarios.size());
        return idFuncionario;
    }

    public static void removerFuncionario(List<Funcionario> funcionarios, int indexFuncionario) {
        if (indexFuncionario >= 0 && indexFuncionario < funcionarios.size()) {
            funcionarios.remove(indexFuncionario);
            // Atualiza os IDs dos funcionários após a remoção
            for (int i = 0; i < funcionarios.size(); i++) {
                funcionarios.get(i).id_funcionario = i + 1;
            }
            System.out.println("Funcionário removido com sucesso!");
        } else {
            System.out.println("Não existe funcionário com esse ID!");
        }
    }

    public static void editarFuncionario(List<Funcionario> funcionarios, int indexFuncionario) {
        Scanner scanner = new Scanner(System.in);
            Funcionario funcionarioAtual = funcionarios.get(indexFuncionario - 1);

            System.out.println("\nFuncionario atual: " + funcionarioAtual);// mostra as informações do produto escolhido

            checkadordeFuncionarios(funcionarioAtual, funcionarios);

            do{
                System.out.print("Digite a senha: ");
                funcionarioAtual.senha = scanner.nextLine();

                while(funcionarioAtual.senha.length() > 50){
                    System.out.println("Senha não pode ser maior que 50 caracteres.");
                    System.out.print("Digite a senha: ");
                    funcionarioAtual.senha = scanner.nextLine();
                }
                if(funcionarioAtual.senha.isEmpty()){
                    System.out.println("Senha não pode ser vazio ou apenas espaços! Digite novamente.");
                }
            }while(funcionarioAtual.senha.isEmpty());

            do{
                System.out.print("Digite o nome do(a) funcionário(a): ");
                funcionarioAtual.nome = scanner.nextLine();

                while(funcionarioAtual.nome.length() > 100){
                    System.out.println("Nome não pode ser maior que 100 caracteres.");
                    System.out.print("Digite o nome do(a) funcionário(a): ");
                    funcionarioAtual.nome = scanner.nextLine();
                }
                if(funcionarioAtual.nome.isEmpty()){
                    System.out.println("Nome não pode ser vazio ou apenas espaços! Digite novamente.");
                }
            }while(funcionarioAtual.nome.isEmpty());

            System.out.print("Digite o CPF do(a) funcionário(a): ");
            funcionarioAtual.cpf = scanner.nextLine();

            while(funcionarioAtual.cpf.length() != 11){
                System.out.println("CPF tem que ser igual a 11 digitos.");
                System.out.print("Digite o CPF do funcionário(a): ");
                funcionarioAtual.cpf = scanner.nextLine();
            }

            System.out.print("Digite telefone do(a) funcionário(a): ");
            funcionarioAtual.telefone = scanner.nextLine();

            while(funcionarioAtual.telefone.length() < 8 || funcionarioAtual.nome.length() > 14){
                System.out.println("O telefone tem que ter ao menos 9 digitos.");
                System.out.print("Digite telefone do funcionário(a): ");
                funcionarioAtual.telefone = scanner.nextLine();
            }

            do{
                System.out.print("Digite o endereço do(a) funcionário(a): ");
                funcionarioAtual.endereco = scanner.nextLine();

                while(funcionarioAtual.endereco.length() > 100){
                    System.out.println("Endereço não pode ser maior que 100 caracteres.");
                    System.out.print("Digite o endereço do(a) funcionário(a): ");
                    funcionarioAtual.endereco = scanner.nextLine();
                }
                if(funcionarioAtual.endereco.isEmpty()){
                    System.out.println("Nome não pode ser vazio ou apenas espaços! Digite novamente.");
                }
            }while(funcionarioAtual.endereco.isEmpty());

            System.out.println("\nFuncionario atual: " + funcionarioAtual);

            System.out.println("\nFuncionario editado com sucesso!");
    }
}
