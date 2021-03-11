/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria.DAO;

import inmobiliaria.entity.Usuario;
import org.springframework.data.repository.CrudRepository;


public interface IUsuarioDAO extends CrudRepository<Usuario,Integer>{
    
    public Usuario findByCorreo(String correo);
    
    public Usuario findByCodigo(String code);
    
}
