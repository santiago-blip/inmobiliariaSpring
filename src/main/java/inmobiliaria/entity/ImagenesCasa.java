/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package inmobiliaria.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tbl_imagen_casa")
public class ImagenesCasa implements Serializable{
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagen")
    private int idImagen;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_casa")
    private Casa idCasa;
    
    @Column(name = "ruta_img")
    private String rutaImg;
    
    public ImagenesCasa(){
    
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public Casa getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(Casa idCasa) {
        this.idCasa = idCasa;
    }

    public String getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }
    
}
