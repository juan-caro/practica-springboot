/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.service;

import com.mycompany.springbootjcr.entity.CancionEntity;
import com.mycompany.springbootjcr.entity.PerfilEntity;
import java.util.List;
import org.springframework.stereotype.Service;
import com.mycompany.springbootjcr.repository.PerfilRepository;

/**
 *
 * @author johnm
 */
@Service
public class PerfilServiceImplementation implements PerfilService {

    private PerfilRepository perfilRepo;

    public PerfilServiceImplementation(PerfilRepository pr){
        super();
        this.perfilRepo = pr;
    }
    
    /*
    *@brief Obtiene todos los canciones
    *@author Juan Cabello Rodríguez
    */
    @Override
    public List<PerfilEntity> getPerfiles() {
        return this.perfilRepo.findAll();
        
    }
    
    /*
    *@brief Guarda el cancion 
    *@author Juan Cabello Rodríguez
    */
    @Override
    public PerfilEntity savePerfil(PerfilEntity p) {
        return this.perfilRepo.save(p);
    }

    /*
    *@brief Actualiza el cancion 
    *@author Juan Cabello Rodríguez
    */
    @Override
    public PerfilEntity updatePerfil(PerfilEntity p) {
        return this.perfilRepo.save(p);
    }

    /*
    *@brief Devuelve el cancion 
    *@param ID del cancion a devolver
    *@author Juan Cabello Rodríguez
    */
    @Override
    public PerfilEntity getPerfilById(Long id) {
        return this.perfilRepo.findById(id).get();
    }
    
    /*
    *@brief Elimina el perfil 
    *@param ID del perfil a eliminar
    *@author Juan Cabello Rodríguez
    */
    @Override
    public void deletePerfilById(Long id) {
        this.perfilRepo.deleteById(id);
    }
    
    /*
    *@brief Comprueba si existe el login
    *@param login
    *@post true existe o false no existe
    *@author Juan Cabello Rodríguez
    */
    @Override
    public boolean existsByLogin(String login) {
       boolean exists = false;
       String aux = "";
        for(PerfilEntity pe: this.perfilRepo.findAll()){
            aux = "\""+ pe.getLogin() + "\"";
            System.out.println("COMPRUEBO LOGIN " + pe.getLogin() + " - " + login);
            if(aux.equals(login)){
                exists = true;
            }
        }
        return exists;
    }
    /*
    *@brief Comprueba si existe el username
    *@param username
    *@post true existe o false no existe
    *@author Juan Cabello Rodríguez
    */
    @Override
    public boolean existsByUsername(String username) {
        boolean exists = false;
        String aux = "";
        for(PerfilEntity pe: this.perfilRepo.findAll()){
            aux = "\""+ pe.getNombre_usuario() + "\"";
            System.out.println("COMPRUEBO USERNAME " + pe.getNombre_usuario() + " - " + username);
            if(aux.equals(username)){
                exists = true;
            }
        }
        return exists;
    }
    /*
    *@brief Comprueba si existe el email
    *@param email
    *@post true existe o false no existe
    *@author Juan Cabello Rodríguez
    */
    @Override
    public boolean existsByEmail(String email) {
        boolean exists = false;
        String aux = "";
        for(PerfilEntity pe: this.perfilRepo.findAll()){
            aux = "\""+ pe.getEmail() + "\"";
            System.out.println("COMPRUEBO EMAIL " + pe.getEmail() + " - " + email);
            if(aux.equals(email)){
                exists = true;
            }
        }
        return exists;
    }
    /*
    *@brief Comprueba si existe el login sin tenerse en cuenta a si mismo
    *@param login
    *@post true existe o false no existe
    *@author Juan Cabello Rodríguez
    */
    @Override
    public boolean existsByLoginEdit(Long id, String login) {
       boolean exists = false;
       String aux = "";
        for(PerfilEntity pe: this.perfilRepo.findAll()){
            if(pe.getId_perfil() != id){
                aux = "\""+ pe.getLogin() + "\"";
                System.out.println("COMPRUEBO LOGIN " + pe.getLogin() + " - " + login);
                if(aux.equals(login)){
                    exists = true;
                }
            }
        }
        return exists;
    }
    
    /*
    *@brief Comprueba si existe el username sin tenerse en cuenta a si mismo
    *@param username
    *@post true existe o false no existe
    *@author Juan Cabello Rodríguez
    */
    @Override
    public boolean existsByUsernameEdit(Long id, String username) {
        boolean exists = false;
        String aux = "";
        for(PerfilEntity pe: this.perfilRepo.findAll()){
            if(pe.getId_perfil() != id){
                aux = "\""+ pe.getNombre_usuario() + "\"";
                System.out.println("COMPRUEBO USERNAME " + pe.getNombre_usuario() + " - " + username);
                if(aux.equals(username)){
                    exists = true;
                }
            }
        }
        return exists;
    }

    /*
    *@brief Comprueba si existe el email sin tenerse en cuenta a si mismo
    *@param email
    *@post true existe o false no existe
    *@author Juan Cabello Rodríguez
    */
    @Override
    public boolean existsByEmailEdit(Long id, String email) {
        boolean exists = false;
        String aux = "";
        for(PerfilEntity pe: this.perfilRepo.findAll()){
            if(pe.getId_perfil() != id){
                aux = "\""+ pe.getEmail() + "\"";
                System.out.println("COMPRUEBO EMAIL " + pe.getEmail() + " - " + email);
                if(aux.equals(email)){
                    exists = true;
                }
            }
        }
        return exists;
    }
    
}
