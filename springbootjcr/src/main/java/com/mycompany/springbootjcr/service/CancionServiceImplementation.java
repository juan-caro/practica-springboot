/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.service;

import com.mycompany.springbootjcr.entity.CancionEntity;
import com.mycompany.springbootjcr.entity.EstadisticaEntity;
import com.mycompany.springbootjcr.entity.PlaylistEntity;
import com.mycompany.springbootjcr.repository.CancionRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnm
 */
@Service
public class CancionServiceImplementation implements CancionService{

    private CancionRepository cancionRepo;

    public CancionServiceImplementation(CancionRepository pr){
        super();
        this.cancionRepo = pr;
    }
    
    /*
    *@brief Obtiene todos los canciones
    *@author Juan Cabello Rodríguez
    */
    @Override
    public List<CancionEntity> getCanciones() {
        return this.cancionRepo.findAll();
        
    }

    /*
    *@brief Guarda el cancion 
    *@author Juan Cabello Rodríguez
    */
    @Override
    public CancionEntity saveCancion(CancionEntity p) {
        return this.cancionRepo.save(p);
    }
    
    /*
    *@brief Actualiza el cancion 
    *@author Juan Cabello Rodríguez
    */
    @Override
    public CancionEntity updateCancion(CancionEntity p) {
        return this.cancionRepo.save(p);
    }
    
    /*
    *@brief Devuelve el cancion 
    *@param ID del cancion a devolver
    *@author Juan Cabello Rodríguez
    */
    @Override
    public CancionEntity getCancionById(Long id) {
        return this.cancionRepo.findById(id).get();
    }
    
     /*
    *@brief Elimina el cancion 
    *@param ID del cancion a eliminar
    *@author Juan Cabello Rodríguez
    */
    @Override
    public void deleteCancionById(Long id) {
        this.cancionRepo.deleteById(id);
    }

     /*
    *@brief Devuelve la Estadistica de la cancion 
    *@param ID de la estadistica
    *@author Juan Cabello Rodríguez
    */
    @Override
    public EstadisticaEntity getEstadisticaCancionById(Long id) {
        return this.cancionRepo.findById(id).get().getEstadisticas_cancion();
    }

    

}
