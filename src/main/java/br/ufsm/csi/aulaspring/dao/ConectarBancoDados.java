package br.ufsm.csi.aulaspring.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectarBancoDados {

    public static Connection conectarBancoPostgres()
            throws ClassNotFoundException, SQLException {

        Class.forName("org.postgresql.Driver");
        System.out.println("Driver carregado");
        String url = "jdbc:postgresql://localhost:5433/poow1";
        String user = "postgres";
        String senha = "postgres";
        Connection conn =
                DriverManager.getConnection(url, user, senha);
        return conn;
    }

    public Connection conectarBancoPostgresMySql(){
        return null;
    }
}
