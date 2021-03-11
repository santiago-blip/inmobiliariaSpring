/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria.service;

import inmobiliaria.entity.Usuario;


public interface IUsuarioService {
    
    public void saveUser(Usuario usuario);
    
    public Usuario findUsuario(String usuario);
    
    public Usuario findUsuarioByCode(String code);
    
}
