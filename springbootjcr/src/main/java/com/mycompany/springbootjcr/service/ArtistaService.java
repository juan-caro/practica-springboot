/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.service;

import com.mycompany.springbootjcr.entity.ArtistaEntity;
import java.util.List;

/**
 *
 * @author johnm
 */
public interface ArtistaService {
    List<ArtistaEntity> getArtistas();
    ArtistaEntity saveArtista(ArtistaEntity p);
    ArtistaEntity updateArtista(ArtistaEntity p);
    ArtistaEntity getArtistaById(Long id);
    void deleteArtistaById(Long id);
}
