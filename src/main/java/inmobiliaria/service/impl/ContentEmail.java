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
public class ContentEmail {
    
    @Autowired
    private UsuarioServiceImpl usuarioService;
    
    public  void contentEmail(Usuario usuario) throws MessagingException{
    
//         mail.setText("<!DOCTYPE html>\n" +
//            "<html lang=\"en\">\n" +
//            "<head>\n" +
//            "    <meta charset=\"UTF-8\">\n" +
//            "<title> htmlmail </ title> \ n" +
//            "</head>\n" +
//            "<body>\n" +
//            "<h2> Este es un mensaje html </ h2> \ n" +
//            "</body>\n" +
//            "</html>");
        String content = "<!DOCTYPE html>"
                + "<html lang=\"en\">"
                + "<head><meta charset=\"UTF-8\"></head>"
                + "<body>"
                + "<h1>Gracias por inscribirse en la página, por favor de click en el siguiente link para verificar su cuenta</h1>"
                + "<a href=http://localhost:8080/verificar?code="+usuario.getCodigo()+">Verificar</a>"
                + "</body>"
                + "</html>";
        usuarioService.sendEmail(usuario.getCorreo(),"Confirmación inmobiliaria",content);
    }
}