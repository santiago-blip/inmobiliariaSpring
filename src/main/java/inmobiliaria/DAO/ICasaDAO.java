/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria.DAO;

import inmobiliaria.entity.Casa;
import inmobiliaria.entity.Usuario;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author santi
 */
public interface ICasaDAO extends CrudRepository<Casa,Long>{
    
    public List<Casa> findByIdUsuario(Usuario usuario);

    public List<Casa> findByTipoInmuebleContainsAndArriendoVentaContains(String inmueble,String arriendoVenta);
    
    public List<Casa> findByArriendoVentaContains(String arriendoVenta);
    
    public List<Casa> findByTipoInmuebleContains(String inmueble);
    
    public Casa findByidCasa(Long id);
    
}
