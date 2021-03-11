/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria.service.impl;

import inmobiliaria.DAO.IUsuarioDAO;
import inmobiliaria.entity.Usuario;
import inmobiliaria.service.IUsuarioService;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service("usuarioService")
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioDAO serviceDAO;

    //Importante hacer la inyección de dependencia de JavaMailSender:
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void saveUser(Usuario usuario) {
        serviceDAO.save(usuario);
    }

    @Override
    public Usuario findUsuario(String usuario) {
        return serviceDAO.findByCorreo(usuario);
    }

    //Pasamos por parametro: destinatario, asunto y el mensaje
    public void sendEmail(String to, String subject, String content) throws MessagingException {

//        SimpleMailMessage email = new SimpleMailMessage(); ->Cuando no se envía un html usar este para solo texto.
//        Para enviar solo texto, sin html
//        email.setTo(to);
//        email.setSubject(subject);
//        email.setText(content);

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("svallejo878@misena.edu.co");
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);  //Para solo texto se mandaría email.
    }

    @Override
    public Usuario findUsuarioByCode(String code) {
        return serviceDAO.findByCodigo(code);
    }
}
