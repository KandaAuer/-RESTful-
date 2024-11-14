package com.example.myproject.controller;

import com.example.myproject.model.Avatar;
import com.example.myproject.service.AvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/avatars")
public class AvatarController {

    @Autowired
    private AvatarService avatarService;

    // Эндпоинт для загрузки аватара
    @PostMapping("/upload")
    public Avatar uploadAvatar(@RequestParam("file") MultipartFile file, @RequestParam("studentId") Long studentId) throws IOException {
        return avatarService.uploadAvatar(file, studentId);
    }

    // Эндпоинт для получения аватара из БД
    @GetMapping("/db/{avatarId}")
    public Avatar getAvatarFromDatabase(@PathVariable Long avatarId) {
        return avatarService.getAvatarFromDatabase(avatarId);
    }

    // Эндпоинт для получения аватара из файловой системы
    @GetMapping("/file/{fileName}")
    public byte[] getAvatarFromFileSystem(@PathVariable String fileName) throws IOException {
        return avatarService.getAvatarFromFileSystem(fileName);
    }
}
