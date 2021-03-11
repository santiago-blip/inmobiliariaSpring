/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria.service;

import inmobiliaria.entity.Casa;
import inmobiliaria.entity.Usuario;
import java.util.List;

/**
 *
 * @author santi
 */
public interface ICasaService {
    
    public List<Casa> listarCasas();
    
    public List<Casa> listarCasasDeUsuario(Usuario usuario);
    
    public List<Casa> listarCasasDobleParametro(String inmuebble,String arriendoVenta);
    
    public List<Casa> listarCasasUnParametro(String arriendoVenta);
    
    public List<Casa> listarCasasInmueble(String TipoInmueble);
    
    public Casa listarCasasPorId(Long id);
    
    public Casa buscarCasaPorId(Casa casa);
    
    public void guardarCasa(Casa casa);
    
    public void eliminarCasa(Long id);
    
}
