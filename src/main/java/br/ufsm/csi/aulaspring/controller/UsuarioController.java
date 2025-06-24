package br.ufsm.csi.aulaspring.controller;

import br.ufsm.csi.aulaspring.model.Usuario;
import br.ufsm.csi.aulaspring.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    @GetMapping
    public String listarUsuarios(Model model) {
        // Aqui você pode adicionar a lógica para buscar os usuários do banco de dados
        // e retornar o nome do template HTML que exibe a lista de usuários.
        model.addAttribute("usuarios", new UsuarioService().listar()); // Adiciona a lista de usuários ao modelo
        model.addAttribute("usuario", new Usuario()); // Adiciona um novo usuário vazio para o formulário de criação
        return "pages/usuarios"; // Nome do template HTML para a página de usuários
    }

    @PostMapping
    public String criarUsuario(RedirectAttributes redirectAttributes, Usuario usuario) {
        // Aqui você pode adicionar a lógica para criar um novo usuário no banco de dados
        // e redirecionar ou retornar uma mensagem de sucesso.
        System.out.println("Criando usuário: " + usuario.getEmail());
        String retorno = new UsuarioService().inserir(usuario);
        redirectAttributes.addFlashAttribute("msg", retorno); // Adiciona mensagem de sucesso para o redirecionamento

        return "redirect:/usuario"; // Redireciona para a lista de usuários após a criação
    }
}
