/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.service;

import com.mycompany.springbootjcr.entity.ArtistaEntity;
import com.mycompany.springbootjcr.repository.ArtistaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnm
 */
@Service
public class ArtistaServiceImplementation implements ArtistaService {
    private ArtistaRepository cancionRepo;

    public ArtistaServiceImplementation(ArtistaRepository pr){
        super();
        this.cancionRepo = pr;
    }
    /*
    *@brief Obtiene todos los artistas
    *@author Juan Cabello Rodríguez
    */
    @Override
    public List<ArtistaEntity> getArtistas() {
        return this.cancionRepo.findAll();
        
    }

    /*
    *@brief Guarda el artista 
    *@author Juan Cabello Rodríguez
    */
    @Override
    public ArtistaEntity saveArtista(ArtistaEntity p) {
        return this.cancionRepo.save(p);
    }
    
    /*
    *@brief Actualiza el artista 
    *@author Juan Cabello Rodríguez
    */
    @Override
    public ArtistaEntity updateArtista(ArtistaEntity p) {
        return this.cancionRepo.save(p);
    }

    /*
    *@brief Devuelve el artista 
    *@param ID del artista a devolver
    *@author Juan Cabello Rodríguez
    */
    @Override
    public ArtistaEntity getArtistaById(Long id) {
        return this.cancionRepo.findById(id).get();
    }

    /*
    *@brief Elimina el artista 
    *@param ID del artista a eliminar
    *@author Juan Cabello Rodríguez
    */
    @Override
    public void deleteArtistaById(Long id) {
        this.cancionRepo.deleteById(id);
    }
}
