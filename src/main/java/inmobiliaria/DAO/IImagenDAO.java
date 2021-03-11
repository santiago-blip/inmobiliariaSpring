/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria.DAO;

import inmobiliaria.entity.ImagenesCasa;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author santi
 */
public interface IImagenDAO extends CrudRepository<ImagenesCasa,Integer>{
    
}
