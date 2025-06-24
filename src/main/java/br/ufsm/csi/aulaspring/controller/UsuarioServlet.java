package br.ufsm.csi.aulaspring.controller;

import br.ufsm.csi.aulaspring.model.Usuario;
import br.ufsm.csi.aulaspring.service.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("usuario")
public class UsuarioServlet extends HttpServlet {

    private static UsuarioService service = new UsuarioService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String email = req.getParameter("email");
        String senha = req.getParameter("senha");

        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setSenha(senha);
        usuario.setAtivo(true);

        String retorno = new UsuarioService().inserir(usuario);

        req.setAttribute("retorno", retorno);
        req.setAttribute("usuarios", new UsuarioService().listar());

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/usuarios.jsp");

        rd.forward(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String opcao = req.getParameter("opcao");
        System.out.println(" ---- > opcao: "+opcao);

        String info = req.getParameter("info");
        System.out.println(" ---- > info: "+info);

        if(opcao != null){

            if(opcao.equals("editar")) {

            }else if(opcao.equals("excluir")) {

              String valor =  service.excluir(Integer.parseInt(info));
              req.setAttribute("msg", valor);

                ArrayList<Usuario> usuarios = new UsuarioService().listar();
                req.setAttribute("usuarios", usuarios);
                RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/usuarios.jsp");
                rd.forward(req, resp);
            }
        }else{
            ArrayList<Usuario> usuarios = new UsuarioService().listar();
            req.setAttribute("usuarios", usuarios);

            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/usuarios.jsp");

            rd.forward(req, resp);
        }





    }
}
