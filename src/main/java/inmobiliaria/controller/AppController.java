/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria.controller;

import inmobiliaria.entity.Casa;
import inmobiliaria.entity.ImagenesCasa;
import inmobiliaria.entity.Rol;
import inmobiliaria.entity.Usuario;
import inmobiliaria.service.ICasaService;
import inmobiliaria.service.IImagenesCasaService;
import inmobiliaria.service.IUsuarioService;
import inmobiliaria.service.impl.ContentEmail;
import inmobiliaria.service.impl.EmailRecuperar;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.mail.MessagingException;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AppController {

    @Autowired
    private BCryptPasswordEncoder passwordEncode;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private ICasaService casaService;

    @Autowired
    private ContentEmail serviceEmail;

    @Autowired
    private EmailRecuperar serviceEmail2;

    @Autowired
    private IImagenesCasaService imagenService;

    @RequestMapping(value = "/")
    public String index(Model model) {
        model.addAttribute("casas", casaService.listarCasas());
        return "index";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam(name = "usernameR") String usuarioR, @RequestParam(name = "passwordR") String password, Usuario usuario, RedirectAttributes flash) throws MessagingException {
        if (usuarioService.findUsuario(usuarioR) == null) {
            Rol rol = new Rol();
            rol.setIdRol(1);
            usuario.setCorreo(usuarioR);
            usuario.setContrasena(passwordEncode.encode(password));
            usuario.setIdRol(rol);
            usuario.setEstado(false);
            usuario.setCodigo(RandomString.make(20));
            usuarioService.saveUser(usuario);
            serviceEmail.contentEmail(usuario);
            flash.addFlashAttribute("registroExitoso", "Usuario creado exitosamente");
        } else {
            flash.addFlashAttribute("userExist", "Ya existe el usuario");
        }
        return "redirect:/";
    }

    @GetMapping("/verificar")
    public String verificar(@RequestParam(name = "code") String code, Usuario usuario, Model modal) {
        usuario = usuarioService.findUsuarioByCode(code);
        if (usuario == null || usuario.getCodigo().length() <= 1) {
            modal.addAttribute("oldV", "Usuario ya validado.");
        } else {
            usuario.setCodigo("0");
            usuario.setEstado(true);
            usuarioService.saveUser(usuario);
            modal.addAttribute("newV", "Se ha validado con éxito.");
        }
        return "verificar";
    }

    @PostMapping("/recuperar")
    public String recuperarContra(@RequestParam(name = "correoR") String correo, Usuario usuario, RedirectAttributes flash) throws MessagingException {

        usuario = usuarioService.findUsuario(correo);
        if (usuario != null) {
            usuario.setCodigo(RandomString.make(20));
            usuarioService.saveUser(usuario);
            serviceEmail2.contentEmail(usuario);
            flash.addFlashAttribute("recuperar", "Se ha enviado el correo");
        } else {
            flash.addFlashAttribute("recuperarNo", "No se ha enviado el correo");
        }
        return "redirect:/";
    }

    @GetMapping("/recuperar/pass")
    public String contraRecuperar(@RequestParam(name = "correo") String correo, @RequestParam(name = "code") String code, Usuario usuario, Model model) {

        usuario = usuarioService.findUsuario(correo);
        if (usuario != null) {
            if (usuario.getCodigo().equals(code)) {
//                usuario.setCodigo("0");
//                usuarioService.saveUser(usuario);
                model.addAttribute("CodeValido", "Codigo válido");
                model.addAttribute("correo", correo);
            } else {
                model.addAttribute("CodeNoValido", "Codigo inválido");
            }
        } else {
            return "redirect:/";
        }

        return "recuperar";
    }

    @PostMapping("/cambiar")
    public String cambiar(@RequestParam(name = "correo") String correo, @RequestParam(name = "pass") String pass, Usuario usuario, RedirectAttributes flash) {
        usuario = usuarioService.findUsuario(correo);
        usuario.setContrasena(passwordEncode.encode(pass));
        usuario.setCodigo("0");
        usuarioService.saveUser(usuario);
        flash.addFlashAttribute("cambiada", "con éxito");
        return "redirect:/";
    }

    @GetMapping("/publicar")
    public String publicar(Casa casa) {

        return "publicar";
    }

    @PostMapping("/publicar")
    public String addPublicar(Casa casa, @RequestParam(name = "file") MultipartFile file, @RequestParam(name = "files") MultipartFile[] files, Usuario usuario, Authentication auth, RedirectAttributes flash) throws IOException {
        String path = "D://ProgramasDevops//ImgExternal";
        usuario = usuarioService.findUsuario(auth.getName());
        //File individual
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                System.out.println("PATH: " + path);
                Path fullPath = Paths.get(path + "//" + file.getOriginalFilename());
                System.out.println("FULL PATH: " + fullPath);
                Files.write(fullPath, bytes);
                casa.setRutaImg(file.getOriginalFilename());
            } catch (IOException e) {
                System.out.println("Error al guardar la imagen principal de la casa: ".concat(e.toString()));
                return "redirect:/";
            }
        }
        //Multiples files
        ImagenesCasa imagen;
        for (MultipartFile f : files) {
            if (f.isEmpty() || f == null) {
                continue;
            }
            byte[] bytes = f.getBytes();
            Path fullPath = Paths.get(path + "//" + f.getOriginalFilename());
            Files.write(fullPath, bytes);
            imagen = new ImagenesCasa();
            imagen.setRutaImg(f.getOriginalFilename());
            imagen.setIdCasa(casa);
            casa.addImagenesCasa(imagen);
        }

        casa.setIdUsuario(usuario);
        casaService.guardarCasa(casa);
        flash.addFlashAttribute("creada", "Con éxito");
//        System.out.println("Titulo: ".concat(casa.getTitulo()));
//        System.out.println("Descripción: ".concat(casa.getDescripcion()).concat(" y su longitud: ").concat(String.valueOf(casa.getDescripcion().length())));
//        System.out.println("Tipo: ".concat(casa.getTipoInmueble()));
//        System.out.println("ArriendoOVenta: ".concat(casa.getArriendoVenta()));
//        System.out.println("Precio: ".concat(String.valueOf(casa.getPrecio())));
//        System.out.println("imgPrincipal: ".concat(casa.getRutaImg()));
//        for (ImagenesCasa c : casa.getImagenesCasa()) {
//            System.out.println("Imagenes: ".concat(c.getRutaImg()));
//        }
        return "redirect:/publicar";
    }

    @RequestMapping(value = "/buscadorSelect")
    public String buscador(@RequestParam(name = "tipoInmueble") String inmueble, @RequestParam(name = "tipoNegocio") String tipoNegocio, Model model) {

        System.out.println("INmueble: " + inmueble);
        System.out.println("Tipo: " + tipoNegocio);

        if (inmueble.isEmpty() && !tipoNegocio.isEmpty()) {
            System.out.println("Entro al primero");
            model.addAttribute("casas", casaService.listarCasasUnParametro(tipoNegocio));
        } else if (tipoNegocio.isEmpty() && !inmueble.isEmpty()) {
            System.out.println("Entro al segundo");
            model.addAttribute("casas", casaService.listarCasasInmueble(inmueble));
        } else if (inmueble.isEmpty() || tipoNegocio.isEmpty()) {
            System.out.println("Entro al tercero");
            model.addAttribute("casas", casaService.listarCasas());
        } else {
            System.out.println("Entro al else");
            model.addAttribute("casas", casaService.listarCasasDobleParametro(inmueble, tipoNegocio));
        }

        return "index";
    }

    @GetMapping("/buscarCode")
    public String buscarCode(@RequestParam(name = "id") Long id,Model model) {
        
        model.addAttribute("casas", casaService.listarCasasPorId(id));
        System.out.println("Esto trae: "+id);
        System.out.println("Este es su tipo: "+ id.getClass());
        return "index";
    }
    
//    @GetMapping("/logoutM")
//    public String logout(@RequestParam(name = "logout") String logout){
//        if(logout!= null){
//            return "redirect:/";
//        }
//        return "redirect:/";
//    }
    
}
