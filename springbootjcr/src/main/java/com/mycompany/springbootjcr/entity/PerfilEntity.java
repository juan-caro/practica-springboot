/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author johnm
 */
@Entity
@Table(name = "Perfil")
public class PerfilEntity implements Serializable {
   //ID DEL PERFIL EL CUAL SERÁ ÚNICO, AUTOINCREMENTABLE Y NO PODRÁ SER NULO
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_perfil", unique = true, nullable = false)
  private Long id_perfil;
  
  // LOGIN DEL PERFIL
  @Column(name = "login", unique = false, nullable = false, length = 100)
  private String login;
  
  // EMAIL DEL PERFIL
  @Column(name = "email", unique = false, nullable = false, length = 100)
  private String email;
  
  // USERNAME DEL PERFIL
  @Column(name = "nombre_usuario", unique = false, nullable = false, length = 100)
  private String nombre_usuario;
  
  // PASSWORD DEL PERFIL
  @Column(name = "contrasenia", unique = false, nullable = false, length = 100)
  private String contrasenia;
  
  // RELACIÖN CON LAS PLAYLISTS GUARDADAS POR EL PERFIL
  @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(name = "Guarda",
            joinColumns={@JoinColumn(name="id_perfil")},
            inverseJoinColumns={@JoinColumn(name="id_playlist")})
  private List<PlaylistEntity> playlists_perfil;

  public PerfilEntity() {
  
  }

    // Devuelve el id del perfil
    public Long getId_perfil() {
        return id_perfil;
    }

    // Reemplaza el id del perfil por el pasado como parámetro
    public void setId_perfil(Long id_perfil) {
        this.id_perfil = id_perfil;
    }

    // Devuelve el login del perfil
    public String getLogin() {
        return login;
    }

    // Reemplaza el login del perfil por el pasado como parámetro
    public void setLogin(String login) {
        this.login = login;
    }

    // Devuelve el email del perfil
    public String getEmail() {
        return email;
    }

    // Reemplaza el email del perfil por el pasado como parámetro
    public void setEmail(String email) {
        this.email = email;
    }

    // Devuelve el nombre del usuario del perfil
    public String getNombre_usuario() {
        return nombre_usuario;
    }

    // Reemplaza el username del perfil por el pasado como parámetro
    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    // Devuelve la contraseña del perfil
    public String getContrasenia() {
        return contrasenia;
    }
    
    // Reemplaza la contraseña del perfil por la pasada como parámetro
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
    // Devuelve la lista de playlists que tiene guardadas el usuario
    public List<PlaylistEntity> getPlaylists_perfil() {
        return playlists_perfil;
    }

    // Reemplaza las playlists del perfil por las pasadas como parámetro
    public void setPlaylists_perfil(List<PlaylistEntity> playlists_perfil) {
        this.playlists_perfil = playlists_perfil;
    }
  
  
  
  // Devuelve las playlists guardadas por el perfil
  @ManyToMany(cascade = CascadeType.DETACH)
    public List getPlaylistsPerfil(){ return playlists_perfil; }
}
