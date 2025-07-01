package br.ufsm.csi.aulaspring.service;

import br.ufsm.csi.aulaspring.model.Usuario;

public class LoginService {

    public Usuario autenticar(String email, String senha) {
        Usuario usuario = new UsuarioService().buscar(email);

        if (usuario != null && usuario.getSenha().equals(senha) && usuario.isAtivo()) {
            return usuario; // Retorna o usuário se a autenticação for bem-sucedida
        } else {
            return null; // Retorna null se a autenticação falhar
        }
    }

}
