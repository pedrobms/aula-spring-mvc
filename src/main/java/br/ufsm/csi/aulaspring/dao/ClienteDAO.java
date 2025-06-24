package br.ufsm.csi.aulaspring.dao;

import br.ufsm.csi.aulaspring.model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class ClienteDAO {
    public String inserir(Cliente cliente) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO cliente (nome, cpf, telefone, endereco, usuario_id) VALUES (?, ?, ?, ?, ?)"
            );

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEndereco());
            stmt.setInt(5, cliente.getUsuarioId());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir cliente");
        }

        return "Cliente inserido com sucesso";
    }

    public String alterar(Cliente cliente) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
            "UPDATE cliente SET nome = ?, cpf = ?, telefone = ?, endereco = ? WHERE id = ?"
            );

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getCpf());
            stmt.setString(3, cliente.getTelefone());
            stmt.setString(4, cliente.getEndereco());
            stmt.setInt(5, cliente.getId());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar cliente");
        }

        return "Cliente alterado com sucesso";
    }

    public String excluir(int id) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM cliente WHERE id = ?"
            );

            stmt.setInt(1, id);

            stmt.execute();

            if (stmt.getUpdateCount() <= 0) {
                return "Nenhum cliente excluído";
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao excluir cliente");
        }

        return "Cliente excluído com sucesso";
    }

    public ArrayList<Cliente> listar() {
        ArrayList<Cliente> clientes = new ArrayList<>();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
            "SELECT * FROM cliente"
            );

            stmt.execute();

            while (stmt.getResultSet().next()) {
                Cliente cliente = new Cliente();
                cliente.setId(stmt.getResultSet().getInt("id"));
                cliente.setNome(stmt.getResultSet().getString("nome"));
                cliente.setCpf(stmt.getResultSet().getString("cpf"));
                cliente.setTelefone(stmt.getResultSet().getString("telefone"));
                cliente.setEndereco(stmt.getResultSet().getString("endereco"));
                cliente.setUsuarioId(stmt.getResultSet().getInt("usuario_id"));

                clientes.add(cliente);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao listar clientes");
        }

        return clientes;
    }
}
