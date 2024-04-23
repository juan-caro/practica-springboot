/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.repository;

import com.mycompany.springbootjcr.entity.PerfilEntity;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author johnm
 */
@Repository
public interface PerfilRepository extends JpaRepository<PerfilEntity, Long>{
    
}
