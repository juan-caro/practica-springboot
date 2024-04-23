/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.service;

import com.mycompany.springbootjcr.entity.PlaylistEntity;
import com.mycompany.springbootjcr.repository.PlaylistRepository;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author johnm
 */
@Service
public class PlaylistServiceImplementation implements PlaylistService{
    private PlaylistRepository playlistRepo;

    public PlaylistServiceImplementation(PlaylistRepository pr){
        super();
        this.playlistRepo = pr;
    }
    
    /*
    *@brief Obtiene todos los playlistes
    *@author Juan Cabello Rodríguez
    */
    @Override
    public List<PlaylistEntity> getPlaylists() {
        return this.playlistRepo.findAll();
        
    }

    /*
    *@brief Guarda el playlist 
    *@author Juan Cabello Rodríguez
    */
    @Override
    public PlaylistEntity savePlaylist(PlaylistEntity p) {
        return this.playlistRepo.save(p);
    }

    /*
    *@brief Actualiza el playlist 
    *@author Juan Cabello Rodríguez
    */
    @Override
    public PlaylistEntity updatePlaylist(PlaylistEntity p) {
        return this.playlistRepo.save(p);
    }

    /*
    *@brief Devuelve el playlist 
    *@param ID del playlist a devolver
    *@author Juan Cabello Rodríguez
    */
    @Override
    public PlaylistEntity getPlaylistById(Long id) {
        return this.playlistRepo.findById(id).get();
    }

    /*
    *@brief Elimina el playlist 
    *@param ID del playlist a eliminar
    *@author Juan Cabello Rodríguez
    */
    @Override
    public void deletePlaylistById(Long id) {
        this.playlistRepo.deleteById(id);
    }
}
