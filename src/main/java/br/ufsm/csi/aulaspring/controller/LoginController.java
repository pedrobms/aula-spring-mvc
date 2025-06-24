package br.ufsm.csi.aulaspring.controller;

import br.ufsm.csi.aulaspring.service.LoginService;
import jakarta.servlet.RequestDispatcher;
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
    public String login(Model model, String email, String senha) {
        System.out.println("Email: " + email + " - senha: " + senha);

        if(new LoginService().autenticar(email, senha)) {
            // Se a autenticação for bem-sucedida, redireciona para o dashboard
            return "redirect:/dashboard"; // Redireciona para o método dashboard()
        }else {
            model.addAttribute("msg", "Login ou senha incorreto!");
            return "index"; // Retorna à página de login com mensagem de erro
        }
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        // Retorna a página do dashboard
        return "pages/dashboard"; // Nome do template HTML para o dashboard
    }

}
