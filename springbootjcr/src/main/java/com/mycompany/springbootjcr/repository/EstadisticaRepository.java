/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.repository;

import com.mycompany.springbootjcr.entity.EstadisticaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author johnm
 */
@Repository
public interface EstadisticaRepository extends JpaRepository<EstadisticaEntity, Long>{
    
}
