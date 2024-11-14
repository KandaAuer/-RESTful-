package com.example.myproject.service;

import com.example.myproject.model.Avatar;
import com.example.myproject.model.Student;
import com.example.myproject.repository.AvatarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AvatarService {

    @Autowired
    private AvatarRepository avatarRepository;

    // Метод для загрузки аватара
    public Avatar uploadAvatar(MultipartFile file, Long studentId) throws IOException {
        // Сохраняем файл на локальный диск
        String filePath = "avatars/" + file.getOriginalFilename();
        Path path = Paths.get(filePath);
        Files.write(path, file.getBytes());

        // Сохраняем файл в БД
        Avatar avatar = new Avatar();
        avatar.setFilePath(filePath);
        avatar.setFileSize(file.getSize());
        avatar.setMediaType(file.getContentType());
        avatar.setData(file.getBytes());

        // Присваиваем студенту
        avatar.setStudent(new Student()); // или получаем студента по ID
        avatar.getStudent().setId(studentId);

        return avatarRepository.save(avatar);
    }

    // Метод для получения аватара из БД
    public Avatar getAvatarFromDatabase(Long avatarId) {
        return avatarRepository.findById(avatarId).orElse(null);
    }

    // Метод для получения аватара из директории
    public byte[] getAvatarFromFileSystem(String fileName) throws IOException {
        Path path = Paths.get("avatars/" + fileName);
        return Files.readAllBytes(path);
    }
}
