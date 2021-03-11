/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria.controller;

import inmobiliaria.entity.Casa;
import inmobiliaria.entity.ImagenesCasa;
import inmobiliaria.entity.Usuario;
import inmobiliaria.service.ICasaService;
import inmobiliaria.service.IUsuarioService;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/mis")
@SessionAttributes("casa")
public class PublicacionesController {

    @Autowired
    private ICasaService casaService;

    @Autowired
    private IUsuarioService usuarioService;

    @RequestMapping(value = "/publicaciones")
    public String publicaciones(Usuario usuario, Authentication auth, Model model) {
        usuario = usuarioService.findUsuario(auth.getName());
        model.addAttribute("casas", casaService.listarCasasDeUsuario(usuario));
        return "publicaciones";
    }

    @RequestMapping(value = "/casas/{id}")
    public String casa(@PathVariable(name = "id") Long id, Casa casa, Model model) {
        casa.setIdCasa(id);
        casa = casaService.buscarCasaPorId(casa);

        if (casa == null) {
            return "redirect:/";
        }
        model.addAttribute("casa", casa);
        return "casa";
    }

    @RequestMapping(value = "/casas/ver/{id}")
    public String casaVer(@PathVariable(name = "id") Long id, Casa casa, Model model) {
        casa.setIdCasa(id);
        casa = casaService.buscarCasaPorId(casa);
        if (casa == null) {
            return "redirect:/";
        }
        model.addAttribute("casa", casa);
        return "casaVer";
    }

    @RequestMapping(value = "/eliminar")
    public String eliminar(@RequestParam(name = "id") Long id, Casa casa, RedirectAttributes flash) {
        casa.setIdCasa(id);
        casa = casaService.buscarCasaPorId(casa);
        if (casa == null) {
            return "redirect:/";
        }
        if (casa.getRutaImg() != null) {
            Path pathImg = Paths.get("D://ProgramasDevops//ImgExternal//").resolve(casa.getRutaImg()).toAbsolutePath();
            File archivoImg = pathImg.toFile();

            //Si el archivo existe y se puede leer.
            if (archivoImg.canRead() && archivoImg.exists()) {
                //Todo el siguiente código, es para no eliminar la imagen del servidor si otra casa la usa.
//                int contadorImg = 0, noEliminar = 0;
//                List<Casa> lista = casaService.listarCasas();
//                System.out.println("casa imagen ".concat(casa.getRutaImg()));
//                for (Casa c : lista) {
//                    if (c.getRutaImg() != null) {
//                        if (c.getRutaImg().equals(casa.getRutaImg())) {
//                            contadorImg++;
//                        }
//                    } else {
//                        noEliminar = 1;
//                    }
//                }
//                if (contadorImg > 1 || noEliminar > 0) {
//                    System.out.println("No se elimina");
//                } else {
//                    if (archivoImg.delete()) {
//                        System.out.println("Se ha eliminado la imagen del archivo");
//                    }
//                }
                if (archivoImg.delete()) {
                    System.out.println("Se ha eliminado la imagen del archivo");
                }
            }
        }

        casaService.eliminarCasa(casa.getIdCasa());
        flash.addFlashAttribute("delete", "exitoso");
        return "redirect:/mis/publicaciones";
    }

    @RequestMapping(value = "/editar/{id}")
    public String editar(@PathVariable(name = "id") Long id, Casa casa, Model model) {
        casa.setIdCasa(id);
        casa = casaService.buscarCasaPorId(casa);
        if (casa == null) {
            return "redirect:/";
        }
        model.addAttribute("casa", casa);
        return "editar";
    }

    @RequestMapping(value = "/editarV", method = RequestMethod.POST)
    public String editar(Casa casa, Model model, MultipartFile file, MultipartFile[] files, SessionStatus status) throws IOException {
        System.out.println("ID EDIT: " + casa.getIdCasa());
        System.out.println("Nombre EDIT: " + casa.getTitulo());
        System.out.println(" desc: " + casa.getDescripcion());
        System.out.println(" inmueble: " + casa.getTipoInmueble());
        System.out.println(" venta: " + casa.getArriendoVenta());
        if (casa == null) {
            return "redirect:/";
        }
        String path = "D://ProgramasDevops//ImgExternal";
        //File individual
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                Path fullPath = Paths.get(path + "//" + file.getOriginalFilename());
                Files.write(fullPath, bytes);
                Path pathImg = Paths.get("D://ProgramasDevops//ImgExternal//").resolve(casa.getRutaImg()).toAbsolutePath();
                File archivoImg = pathImg.toFile();
                if (archivoImg.canRead()) {
                    archivoImg.delete();
                }
                casa.setRutaImg(file.getOriginalFilename());
            } catch (IOException e) {
                System.out.println("Error al guardar la imagen principal de la casa: ".concat(e.toString()));
                return "redirect:/";
            }
        }
        //Multiples files
        if (files.length > 0 || files != null) {
//            int contador = 0;
            for (int i = 0; i < files.length; i++) {
                if (files[i].isEmpty()) {
                    continue;
                }
                byte[] bytes = files[i].getBytes();
                Path fullPath = Paths.get(path + "//" + files[i].getOriginalFilename());
                Files.write(fullPath, bytes);
                Path pathImg = Paths.get("D://ProgramasDevops//ImgExternal//").resolve(casa.getImagenesCasa().get(i).getRutaImg()).toAbsolutePath();
                File archivoImg = pathImg.toFile();
                if (archivoImg.canRead()) {
                    archivoImg.delete();
                }
                casa.getImagenesCasa().get(i).setRutaImg(files[i].getOriginalFilename());
//                contador++;
            }

        }

        casaService.guardarCasa(casa);
        status.setComplete();
        return "redirect:/mis/publicaciones";
    }
}
