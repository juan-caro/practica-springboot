/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.service;

import java.util.List;

import com.mycompany.springbootjcr.entity.PerfilEntity;

/**
 *
 * @author johnm
 */
public interface PerfilService {
    List<PerfilEntity> getPerfiles();
    PerfilEntity savePerfil(PerfilEntity p);
    PerfilEntity updatePerfil(PerfilEntity p);
    PerfilEntity getPerfilById(Long id);
    void deletePerfilById(Long id);
    boolean existsByLogin(String login);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    boolean existsByLoginEdit(Long id, String login);
    boolean existsByUsernameEdit(Long id, String username);
    boolean existsByEmailEdit(Long id, String email);
}
