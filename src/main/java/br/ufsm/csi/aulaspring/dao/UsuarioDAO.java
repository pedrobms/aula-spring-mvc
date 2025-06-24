package br.ufsm.csi.aulaspring.dao;

import br.ufsm.csi.aulaspring.model.Usuario;

import java.sql.*;
import java.util.ArrayList;

public class UsuarioDAO {

    public String alterar(Usuario usuario) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
            "UPDATE usuario SET email = ?, senha = ?, ativo = ? WHERE id = ?"
            );

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setBoolean(3, usuario.isAtivo());
            stmt.setInt(4, usuario.getId());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao alterar usuario");
        }

        return "Usuario alterado com sucesso";
    }

    public boolean excluir(int id) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM usuario WHERE id = ?"
            );

            stmt.setInt(1, id);

            stmt.execute();

            if (stmt.getUpdateCount() <= 0) {
                return false;
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao excluir usuario");
        }

        return true;
    }

    public String inserir(Usuario usuario) {
        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO usuario (email, senha, ativo) VALUES (?, ?, true)"
            );

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());

            stmt.execute();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao inserir usuario");
        }

        return "Inserido com sucesso";
    }

    public ArrayList<Usuario> listar() {

        ArrayList<Usuario> usuarios = new ArrayList<>();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            Statement stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery("select * from usuario");
            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setEmail(rs.getString("email"));
                u.setSenha(rs.getString("senha"));
                u.setAtivo(rs.getBoolean("ativo"));

               usuarios.add(u);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao conectar");
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            System.out.println("Drive não carregou");
            ex.printStackTrace();
        }

        return usuarios;
    }

    public Usuario buscar(int id) {
        Usuario usuario = new Usuario();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
            "SELECT * FROM usuario WHERE id = ?"
            );

            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setAtivo(rs.getBoolean("ativo"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar usuario");
        }

        return usuario;
    }

    public Usuario buscar(String email) {
        Usuario usuario = new Usuario();

        try {
            Connection conn = ConectarBancoDados.conectarBancoPostgres();
            PreparedStatement stmt = conn.prepareStatement(
            "SELECT * FROM usuario WHERE email = ?"
            );

            stmt.setString(1, email);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario.setId(rs.getInt("id"));
                usuario.setEmail(rs.getString("email"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setAtivo(rs.getBoolean("ativo"));
            }

        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println("Erro ao buscar usuario");
        }

        return usuario;
    }

}
