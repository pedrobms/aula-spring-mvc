package br.ufsm.csi.aulaspring.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AutorizadorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        // Verifica se a URL acessada é a de login
        String uri = request.getRequestURI();
        if (uri.equals("/") || uri.equals("/login")) {
            // Se for a página de login, permite o acesso
            return true; // Permite a requisição continuar
        }

        // Verifica se o usuário está logado
        if (request.getSession().getAttribute("usuarioLogado") == null) {
            // Se não estiver logado, redireciona para a página de login
            response.sendRedirect("/"); // Redireciona para a página de login
            return false; // Interrompe a requisição
        }
        // Se o usuário estiver logado, permite o acesso à URL solicitada
        return true; // Permite a requisição continuar
    }
}
