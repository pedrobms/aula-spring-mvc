package br.ufsm.csi.aulaspring.controller;

import br.ufsm.csi.aulaspring.model.Usuario;
import br.ufsm.csi.aulaspring.service.LoginService;
import br.ufsm.csi.aulaspring.service.UsuarioService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpSession;
import org.apache.juli.logging.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
    @GetMapping
    public String index() {
        // Retorna a página de login
        return "index"; // Nome do template HTML para a página de login
    }

    @PostMapping("/login")
    public String login(HttpSession session, Model model, String email, String senha) {
        Usuario usuario = new LoginService().autenticar(email, senha);

        if(usuario != null) {
            // Se a autenticação for bem-sucedida, armazena o usuário na sessão
            session.setAttribute("usuarioLogado", usuario);
            return "redirect:/dashboard"; // Redireciona para o dashboard
        } else {
            // Se a autenticação falhar, retorna à página de login com uma mensagem de erro
            model.addAttribute("msg", "Login ou senha incorreto!");
            return "index"; // Retorna à página de login
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        // Retorna a página do dashboard
        return "pages/dashboard"; // Nome do template HTML para o dashboard
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // Invalida a sessão para fazer logout
        session.invalidate();
        return "redirect:/"; // Redireciona para a página de login
    }

}
