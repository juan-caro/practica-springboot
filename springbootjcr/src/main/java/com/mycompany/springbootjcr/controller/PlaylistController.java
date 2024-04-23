/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.controller;

import com.mycompany.springbootjcr.entity.CancionEntity;
import com.mycompany.springbootjcr.entity.PerfilEntity;
import com.mycompany.springbootjcr.entity.PlaylistEntity;
import com.mycompany.springbootjcr.service.CancionService;
import com.mycompany.springbootjcr.service.PerfilService;
import com.mycompany.springbootjcr.service.PlaylistService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author johnm
 */
@Controller
public class PlaylistController {
        
    private PlaylistService playlistServ;
    private CancionService cancionServ;
    private PerfilService perfilServ;
    
    public PlaylistController(PlaylistService ps, CancionService cs, PerfilService perfs){
        super();
        this.playlistServ = ps;
        this.cancionServ = cs;
        this.perfilServ = perfs;
    }
    
    /*
    *@brief Obtiene todas las playlists de las BD y las muestra en el HTML
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/playlist")
    public String listPlaylists(Model modelo){
        modelo.addAttribute("playlists", this.playlistServ.getPlaylists());
        return "playlist";
    }
    
    /*
    *@brief Método GET para crear una nueva playlist
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/playlist/new")
    public String crearPlaylist(Model modelo){
        PlaylistEntity playlist = new PlaylistEntity();
        
        modelo.addAttribute("playlist", playlist);
        modelo.addAttribute("perfiles", this.perfilServ.getPerfiles());
        
        return "create_playlist";
    }
    
    /*
    *@brief Método POST para guardar la playlist creada
    *@param Playlist creada
    *@author Juan Cabello Rodríguez
    */
    @PostMapping("/playlist")
    public String savePlaylist(@ModelAttribute("playlist") PlaylistEntity playlist, @RequestParam(value = "perfilId", required = true) Long perfilId){
        PerfilEntity modificado = this.perfilServ.getPerfilById(perfilId);
        playlist.setId_creador(this.perfilServ.getPerfilById(perfilId));
        this.playlistServ.savePlaylist(playlist);
        modificado.getPlaylistsPerfil().add(playlist);
        this.perfilServ.savePerfil(modificado);
        
        return "redirect:/playlist";
    }
    
    /*
    *@brief Método GET para editar una playlist
    *@param ID de la playlist
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/playlist/edit/{id}")
    public String editarPlaylist(@PathVariable Long id, Model modelo){
        modelo.addAttribute("playlist", this.playlistServ.getPlaylistById(id));
        List<CancionEntity> cancionesNoAnadidas = new ArrayList<>();
        boolean seEncuentra = false;
        
        PlaylistEntity modificada = this.playlistServ.getPlaylistById(id);
        
        //Muestra únicamente las canciones que no están añadidas a la playlist
        for(CancionEntity ce : this.cancionServ.getCanciones()){
            seEncuentra = false;
            for (Object obj : modificada.getCancionesPlaylist()) {
                if (obj instanceof CancionEntity) {
                    CancionEntity cae = (CancionEntity) obj;
                    if(cae.getId_cancion() == ce.getId_cancion()){
                        seEncuentra = true;
                    }
                }
            }
            if(!seEncuentra){
                cancionesNoAnadidas.add(ce);
            }
        }
        
        modelo.addAttribute("canciones", cancionesNoAnadidas);
        modelo.addAttribute("cargadas", modificada.getCancionesPlaylist());
        
        return "edit_playlist";
    }
    
    /*
    *@brief Método POST para guardar la playlist editada
    *@param Playlist editada
    *@param Id de la canción añadida
    *@param Id de la canción a eliminar de la playlist
    *@author Juan Cabello Rodríguez
    */
    @PostMapping("/playlist/{id}")
    public String updatePlaylist(@PathVariable Long id, @ModelAttribute("playlist") PlaylistEntity playlist, Model modelo, @RequestParam(value = "cancionId", required = false) Long cancionId, @RequestParam(value = "borrarId", required = false) Long borrarId){
        PlaylistEntity playlistCogido = this.playlistServ.getPlaylistById(id);
        
        playlistCogido.setNombre_playlist(playlist.getNombre_playlist());
        
        // SOLO HARÉ ESTO SI HAY UNA CANCIÓN QUE AÑADIR O ELIMINAR DE LA PLAYLIST
        if(cancionId != null){
            playlistCogido.getCancionesPlaylist().add(this.cancionServ.getCancionById(cancionId));
            this.cancionServ.getCancionById(cancionId).getEstadisticas_cancion().setVeces_incluida_en_playlists(
                this.cancionServ.getCancionById(cancionId).getEstadisticas_cancion().getNum_reproducciones()+1
                );
            this.cancionServ.updateCancion(this.cancionServ.getCancionById(cancionId));
        }
        
        if(borrarId != null){
            playlistCogido.getCancionesPlaylist().remove(this.cancionServ.getCancionById(borrarId));
            if(this.cancionServ.getCancionById(borrarId).getEstadisticas_cancion().getNum_reproducciones()-1 > 0){
                this.cancionServ.getCancionById(borrarId).getEstadisticas_cancion().setVeces_incluida_en_playlists(
                    this.cancionServ.getCancionById(borrarId).getEstadisticas_cancion().getNum_reproducciones()-1
                    );
            }
            this.cancionServ.updateCancion(this.cancionServ.getCancionById(borrarId));
        }
        
        playlistCogido.setNum_canciones((long) playlistCogido.getCanciones_playlist().size());
        this.playlistServ.updatePlaylist(playlistCogido);
        
        return "redirect:/playlist";
    }
    
    
    
    /*
    *@brief Método para eliminar una playlist
    *@param ID de la playlist a eliminar
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/playlist/{id}")
    public String deletePlaylist(@PathVariable Long id){
        
        this.playlistServ.deletePlaylistById(id);

        return "redirect:/playlist";

    }
 
    /*
    *@brief Muestra los detalles de una playlist
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/playlist/{id}/details")
    public String detailsPlaylist(@PathVariable Long id, Model modelo){
        PlaylistEntity mostrada = this.playlistServ.getPlaylistById(id);
        modelo.addAttribute("canciones", mostrada.getCancionesPlaylist());

        return "details_playlist";
    }
}
