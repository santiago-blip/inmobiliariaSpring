/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria.service.impl;

import inmobiliaria.entity.Usuario;
import javax.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmailRecuperar {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    public void contentEmail(Usuario usuario) throws MessagingException {
        String content = "<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head><meta charset=\"UTF-8\"></head>"
                + "<body>"
                + "<h1>El siguiente enlace lo llevará a recuperar su contraseña.</h1>"
                + "<a href=http://localhost:8080/recuperar/pass?code=" + usuario.getCodigo() + "&correo="+usuario.getCorreo()+">Verificar</a>"
                + "</body>"
                + "</html>";
        usuarioService.sendEmail(usuario.getCorreo(), "Confirmación inmobiliaria", content);
    }
}
