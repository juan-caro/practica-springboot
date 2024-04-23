/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.controller;

import com.mycompany.springbootjcr.entity.ArtistaEntity;
import com.mycompany.springbootjcr.service.ArtistaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author johnm
 */
@Controller
public class ArtistaController {
     
    private ArtistaService artistaServ;
    
    public ArtistaController(ArtistaService ps){
        super();
        this.artistaServ = ps;
    }
    /*
    *@brief Obtiene la lista de artistas de la base de datos
    *@param Modelo
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/artista")
    public String listArtistaes(Model modelo){
        modelo.addAttribute("artistas", this.artistaServ.getArtistas());
        return "artista";
    }
    
    /*
    *@brief redirige al html para crear un nuevo artista
    *@param modelo
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/artista/new")
    public String crearArtista(Model modelo){
        ArtistaEntity artista = new ArtistaEntity();
        
        modelo.addAttribute("artista", artista);
        
        return "create_artista";
    }

    /*
    *@brief Método Post para el crear artista, redirige a la página principal de artistas
    *@param Artista a introducir
    *@post Se guarda el artista
    *@author Juan Cabello Rodríguez
    */
    @PostMapping("/artista")
    public String saveArtista(@ModelAttribute("artista") ArtistaEntity artista){
        this.artistaServ.saveArtista(artista);
        return "redirect:/artista";
    }
    
    /*
    *@brief Redirige al HTML para editar un artista por su id
    *@param Id del artista a editar
    *@param Modelo
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/artista/edit/{id}")
    public String editarArtista(@PathVariable Long id, Model modelo){
        modelo.addAttribute("artista", this.artistaServ.getArtistaById(id));
        
        return "edit_artista";
    }
    
    /*
    *@brief Método POST para editar un artista
    *@param Id
    *@param Artista
    *@param Modelo
    *@post Se quedan guardados los cambios
    *@author Juan Cabello Rodríguez
    */
    @PostMapping("/artista/{id}")
    public String updateArtista(@PathVariable Long id, @ModelAttribute("artista") ArtistaEntity artista, Model modelo){
        ArtistaEntity artistaCogido = this.artistaServ.getArtistaById(id);
        
        artistaCogido.setNombre_artista(artista.getNombre_artista());
        
        this.artistaServ.updateArtista(artistaCogido);
        
        return "redirect:/artista";
    }
    
    /*
    *@brief Borra un artista
    *@param Id del artista
    *@post Artista borrado de la BD y del programa
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/artista/{id}")
    public String deleteArtista(@PathVariable Long id){
        this.artistaServ.deleteArtistaById(id);

        return "redirect:/artista";

    }
    
}
