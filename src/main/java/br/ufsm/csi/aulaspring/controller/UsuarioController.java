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
        model.addAttribute("usuarios", new UsuarioService().listar()); // Adiciona a lista de usuários ao modelo
        model.addAttribute("usuario", new Usuario()); // Adiciona um novo usuário vazio para o formulário de criação
        model.addAttribute("acao", "/usuario"); // Define a ação do formulário como 'criar'
        return "pages/usuarios"; // Nome do template HTML para a página de usuários
    }

    @PostMapping
    public String criarUsuario(RedirectAttributes redirectAttributes, Usuario usuario) {
        System.out.println("Criando usuário: " + usuario.getEmail());
        String retorno = new UsuarioService().inserir(usuario);
        redirectAttributes.addFlashAttribute("msg", retorno); // Adiciona mensagem de sucesso para o redirecionamento

        return "redirect:/usuario"; // Redireciona para a lista de usuários após a criação
    }

    // Usamos o @GetMapping para exibir o formulário de edição de usuário
    // O ID do usuário a ser editado é passado como um parâmetro na URL
    // O método busca o usuário pelo ID e adiciona ao modelo para edição
    // O formulário de edição é exibido na mesma página de usuários, mas com os dados do usuário selecionado para edição
    @GetMapping("/editar/{id}")
    public String editarUsuario(@PathVariable("id") int id, Model model) {
        Usuario usuario = new UsuarioService().buscar(id); // Busca o usuário pelo ID e adiciona ao modelo para edição
        model.addAttribute("usuario", usuario); // Adiciona o usuário ao modelo para edição
        model.addAttribute("acao", "/usuario/editar"); // Define a ação do formulário como 'editar'
        return "pages/usuarios"; // Nome do template HTML para a página de edição de usuário
    }

    // Usamos o @PostMapping para processar a atualização do usuário
    // O método recebe o usuário atualizado do formulário e chama o serviço para atualizar os dados
    @PostMapping("/editar")
    public String atualizarUsuario(RedirectAttributes redirectAttributes, Usuario usuario) {
        String retorno = new UsuarioService().alterar(usuario); // Chama o serviço para atualizar o usuário
        redirectAttributes.addFlashAttribute("msg", retorno); // Adiciona mensagem de sucesso para o redirecionamento

        return "redirect:/usuario"; // Redireciona para a lista de usuários após a atualização
    }

    @GetMapping("/excluir/{id}")
    public String excluirUsuario(@PathVariable("id") int id, RedirectAttributes redirectAttributes) {
        String sucesso = new UsuarioService().excluir(id);
        redirectAttributes.addFlashAttribute("msg", sucesso); // Adiciona mensagem de sucesso para o redirecionamento

        return "redirect:/usuario"; // Redireciona para a lista de usuários após a exclusão
    }
}
