/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria.service.impl;

import inmobiliaria.DAO.IImagenDAO;
import inmobiliaria.entity.ImagenesCasa;
import inmobiliaria.service.IImagenesCasaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagenesCasaServiceImpl implements IImagenesCasaService{

    @Autowired
    private IImagenDAO serviceDAO;
    
    @Override
    public void saveImg(ImagenesCasa imagen) {
        serviceDAO.save(imagen);
    }
    
}
