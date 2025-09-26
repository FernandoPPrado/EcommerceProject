package jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
// Torna o filtro um bean gerenciado pelo Spring
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;
    private final UserDetailsService userDetailsService;

    // Injeção de dependências via construtor (boa prática)
    public AuthTokenFilter(JwtUtils jwtUtils, UserDetailsService userDetailsService) {
        this.jwtUtils = jwtUtils;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        // 1. Extrai o token JWT do header Authorization
        String token = jwtUtils.getJwtFromHeader(request);

        // 2. Se o token existir e for válido
        if (token != null && jwtUtils.validateJwtToken(token)) {
            // 3. Extrai o username do token
            String username = jwtUtils.getUserNameFromJwtToken(token);

            // 4. Carrega os detalhes do usuário (roles, permissões, etc.)
            var userDetails = userDetailsService.loadUserByUsername(username);

            // 5. Cria um objeto de autenticação para o Spring Security
            var auth = new UsernamePasswordAuthenticationToken(
                    userDetails,               // principal (usuário autenticado)
                    null,                      // credenciais (não precisamos aqui)
                    userDetails.getAuthorities() // roles/permissões
            );

            // 6. Adiciona detalhes extras da requisição (como IP, sessão, etc.)
            auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // 7. Registra a autenticação no contexto do Spring Security
            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        // 8. Continua a cadeia de filtros
        filterChain.doFilter(request, response);
    }
}