/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.service;

import com.mycompany.springbootjcr.entity.CancionEntity;
import com.mycompany.springbootjcr.entity.EstadisticaEntity;
import java.util.List;
/**
 *
 * @author johnm
 */
public interface CancionService {
    List<CancionEntity> getCanciones();
    CancionEntity saveCancion(CancionEntity p);
    CancionEntity updateCancion(CancionEntity p);
    CancionEntity getCancionById(Long id);
    void deleteCancionById(Long id);
    EstadisticaEntity getEstadisticaCancionById(Long id);
    
}
