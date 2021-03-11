/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigApp implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/img/uploads/**") //=> Lo sacamos de donde agregamos la imagen en el html, es la ruta que llevará el img src =””, puede ser una ruta inventada
        .addResourceLocations("file:/D:/ProgramasDevops/ImgExternal/");//=> La ruta donde está la carpeta externa, el “file:” es obligatorio y también debe terminar la ruta con el “/”
	}
}
