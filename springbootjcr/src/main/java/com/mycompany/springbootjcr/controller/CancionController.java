/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.controller;

import com.mycompany.springbootjcr.entity.CancionEntity;
import com.mycompany.springbootjcr.entity.EstadisticaEntity;
import com.mycompany.springbootjcr.entity.PlaylistEntity;
import com.mycompany.springbootjcr.service.ArtistaService;
import com.mycompany.springbootjcr.service.CancionService;
import com.mycompany.springbootjcr.service.PlaylistService;
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
public class CancionController {
     
    private CancionService cancionServ;
    private ArtistaService artistaServ;
    private PlaylistService playServ;
    
    public CancionController(CancionService ps, ArtistaService as, PlaylistService plays){
        super();
        this.cancionServ = ps;
        this.artistaServ = as;
        this.playServ = plays;
    }
    
    /*
    *@brief Página principal de canciones, obtiene todas las canciones de la BD
    *@param Modelo
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/cancion")
    public String listCanciones(Model modelo){
        modelo.addAttribute("canciones", this.cancionServ.getCanciones());
        return "cancion";
    }

    /*
    *@brief Método GET para reenviar al usuario al HTML para crear una nueva canción
    *@param Modelo
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/cancion/new")
    public String crearCancion(Model modelo){
        CancionEntity cancion = new CancionEntity();
        
        modelo.addAttribute("cancion", cancion);
        modelo.addAttribute("artistas", this.artistaServ.getArtistas());
        return "create_cancion";
    }
    
    /*
    *@brief Método POST para guardar la canción creada
    *@param Canción creada
    *@param Id del artista que ha hecho esa canción
    *@post Canción creada
    *@author Juan Cabello Rodríguez
    */
    @PostMapping("/cancion")
    public String saveCancion(@ModelAttribute("cancion") CancionEntity cancion, @RequestParam("artistaId") Long artistaId){
        cancion.setArtista_cancion(this.artistaServ.getArtistaById(artistaId));
       
        this.cancionServ.saveCancion(cancion);
        return "redirect:/cancion";
    }
    
    /*
    *@brief Método GET para editar una canción
    *@param Id de la canción    
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/cancion/edit/{id}")
    public String editarCancion(@PathVariable Long id, Model modelo){
        modelo.addAttribute("cancion", this.cancionServ.getCancionById(id));
        modelo.addAttribute("artistas", this.artistaServ.getArtistas());
        
        return "edit_cancion";
    }
    
    /*
    *@brief Método update para una canción
    *@param Canción editada
    *@param Id de la canción
    *@param Id del artista
    *@pre La canción debe existir
    *@post Canción editada correctamente
    *@author Juan Cabello Rodríguez
    */
    @PostMapping("/cancion/{id}")
    public String updateCancion(@PathVariable Long id, @ModelAttribute("cancion") CancionEntity cancion, Model modelo, @RequestParam("artistaId") Long artistaId){
        CancionEntity cancionCogido = this.cancionServ.getCancionById(id);
        cancion.setArtista_cancion(this.artistaServ.getArtistaById(artistaId));
        cancionCogido.setTitulo(cancion.getTitulo());
        cancionCogido.setArtista_cancion(cancion.getArtista_cancion());
        cancionCogido.setDuracion_seg(cancion.getDuracion_seg());
        cancionCogido.setAnio_publicacion(cancion.getAnio_publicacion());
        
        this.cancionServ.updateCancion(cancionCogido);
        
        return "redirect:/cancion";
    }
    
    /*
    *@brief Método para borrar una canción
    *@param Id de la canción a borrar
    *@post Canción eliminada de la DB y del programa
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/cancion/{id}")
    public String deleteCancion(@PathVariable Long id){
        
        this.cancionServ.deleteCancionById(id);

        return "redirect:/cancion";

    }
    
    /*
    *@brief Método GET para mostrar las estadísticas de una canción
    *@param ID de la canción
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/cancion/estadistica/{id}")
    public String obtenerEstadisticasCancion(Model modelo, @PathVariable Long id){
        modelo.addAttribute("estadistica", this.cancionServ.getEstadisticaCancionById(id));
        
        
        return "estadistica";
    }
}
