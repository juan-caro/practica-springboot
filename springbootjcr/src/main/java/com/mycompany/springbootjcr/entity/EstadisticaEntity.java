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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.io.Serializable;

/**
 *
 * @author johnm
 */
@Entity
@Table( name = "Estadistica")
public class EstadisticaEntity implements Serializable{
    //ID DE LA ESTADÍSTICA DE LA CANCIÓN EL CUAL SERÁ ÚNICO, AUTOINCREMENTABLE Y NO PODRÁ SER NULO
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estadistica", unique = true, nullable = false)
    private Long id_estadistica;
    
    // Número de reproducciones de la canción
    @Column(name = "num_reproducciones", unique = false, nullable = false)
    private Long num_reproducciones;
    
    // Veces incluida en playlists
    @Column(name = "veces_incluida_en_playlists", unique = false, nullable = false)
    private Long veces_incluida_en_playlists;
    
    // Id de la canción
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cancion")
    private CancionEntity cancion_estadistica;

    public EstadisticaEntity() {
        this.num_reproducciones = 0L;
        this.veces_incluida_en_playlists = 0L;
    
    }

    
    // Devuelve el id de la estadística
    public Long getId_estadistica() {
        return id_estadistica;
    }

    // Reemplaza el id de la estadística por el pasado como parámetro
    public void setId_estadistica(Long id_estadistica) {
        this.id_estadistica = id_estadistica;
    }

    // Devuelve el número de reproducciones
    public Long getNum_reproducciones() {
        return num_reproducciones;
    }

    // Reemplaza el número de reproducciones por el pasado como parámetro
    public void setNum_reproducciones(Long num_reproducciones) {
        this.num_reproducciones = num_reproducciones;
    }

    // Devuelve el número de veces incluida en playlists
    public Long getVeces_incluida_en_playlists() {
        return veces_incluida_en_playlists;
    }

    // Reemplaza el número de veces incluida en playlists por el pasado como parámetro
    public void setVeces_incluida_en_playlists(Long veces_incluida_en_playlists) {
        this.veces_incluida_en_playlists = veces_incluida_en_playlists;
    }

    // Devuelve la canción de la estadística
    public CancionEntity getCancion_estadistica() {
        return cancion_estadistica;
    }

    // Reemplaza la canción de la estadística
    public void setCancion_estadistica(CancionEntity cancion_estadistica) {
        this.cancion_estadistica = cancion_estadistica;
    }
    
    
}
