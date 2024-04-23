/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.controller;

import com.mycompany.springbootjcr.entity.CancionEntity;
import com.mycompany.springbootjcr.service.PerfilService;
import com.mycompany.springbootjcr.entity.PerfilEntity;
import com.mycompany.springbootjcr.entity.PlaylistEntity;
import com.mycompany.springbootjcr.service.PlaylistService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author johnm
 */
@Controller
public class PerfilController {
    
    private PerfilService perfilServ;
    private PlaylistService playlistServ;
    
    public PerfilController(PerfilService ps, PlaylistService plays){
        super();
        this.perfilServ = ps;
        this.playlistServ = plays;
    }
    
    /*
    *@brief Obtiene los perfiles y los muestra en la página principal
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/perfil")
    public String listPerfiles(Model modelo){
        modelo.addAttribute("perfiles", this.perfilServ.getPerfiles());
        return "perfil";
    }
    
    /*
    *@brief Método GET para el crear un nuevo perfil. Reenvía al usuario a la página de creación
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/perfil/new")
    public String crearPerfil(Model modelo){
        PerfilEntity perfil = new PerfilEntity();
        
        modelo.addAttribute("perfil", perfil);
        
        return "create_perfil";
    }
    
    /*
    *@brief Método POST para la creación de un nuevo perfil
    *@param Perfil creado
    *@post Guarda el perfil y redirige a la página principal
    *@author Juan Cabello Rodríguez
    */
    @PostMapping("/perfil")
    public String savePerfil(@ModelAttribute("perfil") PerfilEntity perfil ){
        this.perfilServ.savePerfil(perfil);
        return "redirect:/perfil";
    }
    
    /*
    *@brief Verifica que el login no exista para crear un nuevo perfil
    *@post Asegura que el login no se duplique
    *@author Juan Cabello Rodríguez
    */
    @PostMapping("/verificarLogin")
    @ResponseBody
    public boolean verificarLogin(@RequestBody String login){
        if(this.perfilServ.existsByLogin(login)){
            System.out.println("EXISTE LOGIN");
            return false;
        }else{
            System.out.println("NO EXISTE LOGIN");
            return true;
        }
    }
    
    /*
    *@brief Verifica que el username no exista para crear un nuevo perfil
    *@post Asegura que el username no se duplique
    *@author Juan Cabello Rodríguez
    */
    @PostMapping("/verificarUsername")
    @ResponseBody
    public boolean verificarUsername(@RequestBody String username){
        if(this.perfilServ.existsByUsername(username)){
            System.out.println("EXISTE USERNAME");
            return false;
        }else{
            System.out.println("NO EXISTE USERNAME");
            return true;
        }
    }
    
    /*
    *@brief Verifica que el email no exista para crear un nuevo perfil
    *@post Asegura que el email no se duplique
    *@author Juan Cabello Rodríguez
    */
    @PostMapping("/verificarEmail")
    @ResponseBody
    public boolean verificarEmail(@RequestBody String email){
        if(this.perfilServ.existsByEmail(email)){
            System.out.println("EXISTE EMAIL");
            return false;
        }else{
            System.out.println("NO EXISTE EMAIL");
            return true;
        }
    }
    
    /*
    *@brief Verifica que el login no exista al editar un perfil
    *@post Asegura que el login no se duplique y no se tiene en cuenta a si mismo
    *@author Juan Cabello Rodríguez
    */
    @PostMapping("/verificarLogin/{id}")
    @ResponseBody
    public boolean verificarLoginEdit(@PathVariable Long id, @RequestBody String login){
        System.out.println("entro en verificarloginedit");
        if(this.perfilServ.existsByLoginEdit(id, login)){
            System.out.println("EXISTE LOGIN");
            return false;
        }else{
            System.out.println("NO EXISTE LOGIN");
            return true;
        }
    }
    
    /*
    *@brief Verifica que el username no exista al editar un perfil
    *@post Asegura que el username no se duplique y no se tiene en cuenta a si mismo
    *@author Juan Cabello Rodríguez
    */
    @PostMapping("/verificarUsername/{id}")
    @ResponseBody
    public boolean verificarUsernameEdit(@PathVariable Long id, @RequestBody String username){
        if(this.perfilServ.existsByUsernameEdit(id, username)){
            
            System.out.println("EXISTE USERNAME");
            return false;
        }else{
            System.out.println("NO EXISTE USERNAME");
            return true;
        }
    }
    
    /*
    *@brief Verifica que el login no exista al editar un perfil
    *@post Asegura que el login no se duplique y no se tiene en cuenta a si mismo
    *@author Juan Cabello Rodríguez
    */
    @PostMapping("/verificarEmail/{id}")
    @ResponseBody
    public boolean verificarEmailEdit(@PathVariable Long id, @RequestBody String email){
        if(this.perfilServ.existsByEmailEdit(id, email)){
            System.out.println("EXISTE EMAIL");
            return false;
        }else{
            System.out.println("NO EXISTE EMAIL");
            return true;
        }
    }
    
    /*
    *@brief Método GET para redirigir al usuario a editar un perfil
    *@param ID del perfil a editar
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/perfil/edit/{id}")
    public String editarPerfil(@PathVariable Long id, Model modelo){
        modelo.addAttribute("perfil", this.perfilServ.getPerfilById(id));
        
        List<PlaylistEntity> playlistsNoAnadidas = new ArrayList<>();
        boolean seEncuentra = false;
        
        PerfilEntity modificada = this.perfilServ.getPerfilById(id);
        
        // Muestra únicamente las playlists que el usuario no tiene añadidas.
        for(PlaylistEntity ce : this.playlistServ.getPlaylists()){
            seEncuentra = false;
            for (Object obj : modificada.getPlaylistsPerfil()) {
                if (obj instanceof PlaylistEntity) {
                    PlaylistEntity cae = (PlaylistEntity) obj;
                    if(cae.getId_playlist() == ce.getId_playlist()){
                        seEncuentra = true;
                    }
                }
            }
        
            if(!seEncuentra){
                playlistsNoAnadidas.add(ce);
            }
        }
        modelo.addAttribute("playlists", playlistsNoAnadidas);
        modelo.addAttribute("cargadas", modificada.getPlaylistsPerfil());
        
        return "edit_perfil";
    }
    
    /*
    *@brief Método POST que guarda y actualiza los datos del perfil
    *@param Perfil editado
    *@param Id del perfil
    *@param Id playlist añadida
    *@param Id playlist borrada
    *@post Datos actualizados
    *@author Juan Cabello Rodríguez
    */
    @PostMapping("/perfil/{id}")
    public String updatePerfil(@PathVariable Long id, @ModelAttribute("perfil") PerfilEntity perfil, Model modelo, @RequestParam(value = "playlistId", required = false) Long playlistId, @RequestParam(value = "borrarId", required = false) Long borrarId){
        PerfilEntity perfilCogido = this.perfilServ.getPerfilById(id);
        
        perfilCogido.setLogin(perfil.getLogin());
        perfilCogido.setContrasenia(perfil.getContrasenia());
        perfilCogido.setEmail(perfil.getEmail());
        perfilCogido.setNombre_usuario(perfil.getNombre_usuario());
        
        // SOLO HARÁ ESTO SI SE HAN AÑADIDO PLAYLISTS PARA AGREGAR O PARA BORRAR
        if(playlistId != null){
            perfilCogido.getPlaylistsPerfil().add(this.playlistServ.getPlaylistById(playlistId));
        }
        
        if(borrarId != null){
            perfilCogido.getPlaylistsPerfil().remove(this.playlistServ.getPlaylistById(borrarId));
        }
        
        this.perfilServ.updatePerfil(perfilCogido);
        
        return "redirect:/perfil";
    }
    
    /*
    *@brief Método para borrar un perfil
    *@param Id del perfil a borrar
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/perfil/{id}")
    public String deletePerfil(@PathVariable Long id){
        this.perfilServ.deletePerfilById(id);

        return "redirect:/perfil";

    }
    
    /*
    *@brief Método GET para los detalles de un perfil
    *@param ID del perfil
    *@author Juan Cabello Rodríguez
    */
    @GetMapping("/perfil/{id}/details")
    public String detailsPlaylist(@PathVariable Long id, Model modelo){
        PerfilEntity mostrada = this.perfilServ.getPerfilById(id);
        modelo.addAttribute("playlists", mostrada.getPlaylistsPerfil());

        return "details_perfil";
    }
}
