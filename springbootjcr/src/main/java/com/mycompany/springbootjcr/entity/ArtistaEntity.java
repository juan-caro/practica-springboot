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
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author johnm
 */
@Entity
@Table( name = "Artista" )
public class ArtistaEntity implements Serializable {
    //ID DEL ARTISTA EL CUAL SERÁ ÚNICO, AUTOINCREMENTABLE Y NO PODRÁ SER NULO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artista", unique = true, nullable = false)
    private Long id_artista;
    // Nombre del artista, no tiene por qué ser único, no podrá ser nulo y tendrá una longitud máxima de 100 caracteres
    @Column(name = "nombre_artista", unique = false, nullable = false, length = 100)
    private String nombre_artista;
    
    // Lista de canciones creadas por el artista
    @OneToMany(mappedBy = "artista_cancion", cascade = CascadeType.DETACH)
    private List<CancionEntity> canciones_del_artista;
    
    // Devuelve las canciones del artista
    @OneToMany(cascade = CascadeType.DETACH)
    public List getCancionesDelArtista(){ return canciones_del_artista; }

    // Devuelve el nombre del artista
    public String getNombre_artista() {
        return nombre_artista;
    }
    
    // Reemplaza el nombre del artista por el pasado como parámetro
    public void setNombre_artista(String nombre_artista) {
        this.nombre_artista = nombre_artista;
    }

    // Devuelve el ID del artista
    public Long getId_artista() {
        return id_artista;
    }

    // Reemplaza el ID del artista por el pasado como parámetro
    public void setId_artista(Long id_artista) {
        this.id_artista = id_artista;
    }
    
    
    
}
