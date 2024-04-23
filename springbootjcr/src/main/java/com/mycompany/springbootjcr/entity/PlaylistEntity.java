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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author johnm
 */
@Entity
@Table(name = "Playlist")
public class PlaylistEntity implements Serializable {
  //ID DE LA PLAYLIST EL CUAL SERÁ ÚNICO, AUTOINCREMENTABLE Y NO PODRÁ SER NULO
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_playlist", unique = true, nullable = false)
  private Long id_playlist;
  
  // Nombre de la playlist
  @Column(name = "nombre_playlist", unique = false, nullable = false, length = 100)
  private String nombre_playlist;
  
  // Id del perfil creador, cuando se elimine el perfil se pondrá null
  @OneToOne(cascade = CascadeType.DETACH)
  @JoinColumn(name = "id_perfil", nullable = true)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  private PerfilEntity id_creador;
  
  // Número de canciones que tiene la playlist
  @Column(name = "num_canciones", unique = false, nullable = false)
  private Long num_canciones;
  
  // Perfiles que tienen guardada la playlist
  @ManyToMany(cascade = CascadeType.DETACH, mappedBy = "playlists_perfil")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<PerfilEntity> perfiles_playlist;
  
  // Canciones que tiene guardada la playlist
    @ManyToMany(cascade = CascadeType.DETACH)
        @JoinTable(name = "Contiene",
            joinColumns={@JoinColumn(name="id_playlist")},
            inverseJoinColumns={@JoinColumn(name="id_cancion")})
    private List<CancionEntity> canciones_playlist;
  

    public PlaylistEntity() {
        this.num_canciones = 0L;
        this.canciones_playlist = new ArrayList<CancionEntity>();
    }

    // Devuelve el id de la playlist
    public Long getId_playlist() {
        return id_playlist;
    }

    // Reemplaza el id de la playlist por el pasado como parámetro
    public void setId_playlist(Long id_playlist) {
        this.id_playlist = id_playlist;
    }

    // Devuelve el nombre de la playlist
    public String getNombre_playlist() {
        return nombre_playlist;
    }

    // Reemplaza el nombre de la playlist por el pasado como parámetro
    public void setNombre_playlist(String nombre_playlist) {
        this.nombre_playlist = nombre_playlist;
    }

    // Devuelve el número de canciones
    public Long getNum_canciones() {
        return num_canciones;
    }

    // Reemplaza el número de canciones por el pasado como parámetro
    public void setNum_canciones(Long num_canciones) {
        this.num_canciones = num_canciones;
    }
  
    // Devuelve el creador de la playlist
    public PerfilEntity getCreador(){ return id_creador; }
  
    // Devuelve las canciones de la playlist
  @ManyToMany(cascade = CascadeType.DETACH)
    public List getCancionesPlaylist(){ return canciones_playlist; }
    
    // Devuelve los perfiles en los que se encuentra la playlist
  @ManyToMany(cascade = CascadeType.DETACH)
    public List getPerfilesPlaylist(){ return perfiles_playlist; }

    // Devuelve el id del creador de la playlist
    public PerfilEntity getId_creador() {
        return id_creador;
    }
    
    // Reemplaza el id del creador de la playlist por el pasado como parámetro
    public void setId_creador(PerfilEntity id_creador) {
        this.id_creador = id_creador;
    }

    // Devuleve los perfiles de la playlist
    public List<PerfilEntity> getPerfiles_playlist() {
        return perfiles_playlist;
    }

    // Reemplaza los perfiles de la playlist por los pasados como parámetro
    public void setPerfiles_playlist(List<PerfilEntity> perfiles_playlist) {
        this.perfiles_playlist = perfiles_playlist;
    }

    // Devuelve las canciones de la playlist
    public List<CancionEntity> getCanciones_playlist() {
        return canciones_playlist;
    }

    // Reemplaza las canciones de la playlist por las pasadas como parámetro
    public void setCanciones_playlist(List<CancionEntity> canciones_playlist) {
        this.canciones_playlist = canciones_playlist;
    }
  
} 
