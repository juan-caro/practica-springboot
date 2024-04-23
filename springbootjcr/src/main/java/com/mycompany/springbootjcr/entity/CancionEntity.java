/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PostPersist;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 *
 * @author johnm
 */
@Entity
@Table( name = "Cancion" )
public class CancionEntity implements Serializable {
 //ID DE LA CANCIÓN EL CUAL SERÁ ÚNICO, AUTOINCREMENTABLE Y NO PODRÁ SER NULO
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_cancion", unique = true, nullable = false)
  private Long id_cancion;
  
  // Título de la canción, no único, no nullable y con longitud máxima de 100 caracteres
  @Column(name = "titulo", unique = false, nullable = false, length = 100)
  private String titulo;
  
  // Duración en segundos de la canción, no será unico y no nullable
  // DEBE FILTRARSE EN LA VISTA > 0
  @Column(name = "duracion_seg", unique = false, nullable = false)
  private Integer duracion_seg;
  
  // Año de publicación de la canción, no único, no nullable y con longitud máxima de 100 caracteres
  // DEBE FILTRARSE EN LA VISTA
  @Column(name = "anio_publicacion", unique = false, nullable = false, length = 100)
  private Integer anio_publicacion;
  
  // ID del artista que ha creado la canción
  @ManyToOne
  @JoinColumn(name = "id_artista")
  private ArtistaEntity artista_cancion;
  
  // Playlists en las que está añadida la canción
  @ManyToMany(cascade = CascadeType.DETACH, mappedBy = "canciones_playlist")
  @OnDelete(action = OnDeleteAction.CASCADE)
  private List<PlaylistEntity> playlists_cancion;

  // Devuelve las playlists en las que se encuentra la canción
  @ManyToMany(cascade = CascadeType.DETACH)
  public List getPlaylistsCancion(){ return playlists_cancion; }
  
  // ID de las estadisticas de la canción
  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "id_estadistica")
  private EstadisticaEntity estadisticas_cancion;

    public CancionEntity(){
        this.estadisticas_cancion = new EstadisticaEntity();
        
    }
    
    // Reemplaza las playlists de la canción por las pasadas como parámetro
    public void setPlaylists_cancion(List<PlaylistEntity> playlists_cancion) {
        this.playlists_cancion = playlists_cancion;
    }
    
    // Reemplaza las estadísticas de la canción por las pasadas ocmo parámetro
    public void setEstadisticas_cancion(EstadisticaEntity estadisticas_cancion) {
        this.estadisticas_cancion = estadisticas_cancion;
    }
  
    // Devuelve el id de la canción
    public Long getId_cancion() {
        return id_cancion;
    }
    
    // Reemplaza el id de la canción por el pasado como parámetro
    public void setId_cancion(Long id_cancion) {
        this.id_cancion = id_cancion;
    }

    // Devuelve el título de la canción
    public String getTitulo() {
        return titulo;
    }

    // Reemplaza el titulo de la canción por el pasado como parámetro
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Devuelve la duración en segundos de la canción
    public Integer getDuracion_seg() {
        return duracion_seg;
    }
    
    // Reemplaza la duración en segundos por la pasada como parámetro
    public void setDuracion_seg(Integer duracion_seg) {
        this.duracion_seg = duracion_seg;
    }

    // Devuelve el año de publicación
    public Integer getAnio_publicacion() {
        return anio_publicacion;
    }

    // Reemplaza el año de la publicación por el pasado como parámetro
    public void setAnio_publicacion(Integer anio_publicacion) {
        this.anio_publicacion = anio_publicacion;
    }

    // Devuelve el artista de la canción
    public ArtistaEntity getArtista_cancion() {
        return artista_cancion;
    }

    // Reemplaza el artista de la canción por el pasado como parámetro
    public void setArtista_cancion(ArtistaEntity artista_cancion) {
        this.artista_cancion = artista_cancion;
    }

    // Devuelve las playlists en las que se encuentra la canción
    public List<PlaylistEntity> getPlaylists_cancion() {
        return playlists_cancion;
    }

    // Devuelve las estadísticas de la canción
    public EstadisticaEntity getEstadisticas_cancion() {
        return estadisticas_cancion;
    }
  
    
  
}
