/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria.service.impl;

import inmobiliaria.DAO.ICasaDAO;
import inmobiliaria.entity.Casa;
import inmobiliaria.entity.Usuario;
import inmobiliaria.service.ICasaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CasaServiceImpl implements ICasaService{
    
    @Autowired
    private ICasaDAO serviceDAO;

    @Override
    public List<Casa> listarCasas() {
        return (List<Casa>)serviceDAO.findAll();
    }

    @Override
    public List<Casa> listarCasasDeUsuario(Usuario usuario) {
        return (List<Casa>)serviceDAO.findByIdUsuario(usuario);
    }

    @Override
    public Casa buscarCasaPorId(Casa casa) {
        return serviceDAO.findById(casa.getIdCasa()).orElse(null);
    }

    @Override
    public void guardarCasa(Casa casa) {
        serviceDAO.save(casa);
    }

    @Override
    public void eliminarCasa(Long id) {
        serviceDAO.deleteById(id);
    }

    @Override
    public List<Casa> listarCasasDobleParametro(String inmuebble, String arriendoVenta) {
        return (List<Casa>) serviceDAO.findByTipoInmuebleContainsAndArriendoVentaContains(inmuebble, arriendoVenta);
    }

    @Override
    public List<Casa> listarCasasUnParametro(String arriendoVenta) {
        return (List<Casa>) serviceDAO.findByArriendoVentaContains(arriendoVenta);
    }

    @Override
    public List<Casa> listarCasasInmueble(String TipoInmueble) {
        return (List<Casa>) serviceDAO.findByTipoInmuebleContains(TipoInmueble);
    }

    @Override
    public Casa listarCasasPorId(Long id) {
        return (Casa) serviceDAO.findByidCasa(id);
    }
    
}
