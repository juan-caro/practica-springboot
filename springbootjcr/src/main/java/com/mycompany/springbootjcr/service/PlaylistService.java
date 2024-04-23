/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.springbootjcr.service;

import com.mycompany.springbootjcr.entity.PlaylistEntity;
import java.util.List;

/**
 *
 * @author johnm
 */
public interface PlaylistService {
    List<PlaylistEntity> getPlaylists();
    PlaylistEntity savePlaylist(PlaylistEntity p);
    PlaylistEntity updatePlaylist(PlaylistEntity p);
    PlaylistEntity getPlaylistById(Long id);
    void deletePlaylistById(Long id);
    
}
