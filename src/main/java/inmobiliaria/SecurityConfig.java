/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
    @Autowired
    private UserDetailsService UsuarioLogin;

    @Bean
    public BCryptPasswordEncoder passwordEncode() {
        return new BCryptPasswordEncoder();
    }
//
    @Autowired
    public void configurerGlobal(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(UsuarioLogin)
                .passwordEncoder(passwordEncode());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/","/registro","/verificar","/recuperar","/recuperar/**","/buscadorSelect","/buscarCode","/mis/casas/**","/cambiar","/img/**", "/css/**", "/js/**").permitAll()//Hace que las rutas que pongamos en antMatcher, sean públicas, no necesiten autorización.
//                .antMatchers("/editar/**").hasRole("ADMIN")
//                .antMatchers("/eliminar").hasRole("ADMIN")
//                .antMatchers("/producto").hasRole("ADMIN")
//                .antMatchers("/carrito/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
    }

}
