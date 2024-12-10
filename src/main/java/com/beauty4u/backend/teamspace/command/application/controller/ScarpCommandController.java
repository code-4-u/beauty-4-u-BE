package com.beauty4u.backend.teamspace.command.application.controller;

import com.beauty4u.backend.teamspace.command.application.service.ScrapService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/teamspace")
@RequiredArgsConstructor
@Tag(name = "Scrap", description = "스크랩 관련 API")
public class ScarpCommandController {

    private final ScrapService scrapService;

    @PostMapping("/{teamspaceId}/folders/{folderId}/files")
    public ResponseEntity<String> uploadScrap(@PathVariable Long teamspaceId,
                                            @PathVariable Long folderId,
                                            @RequestParam("file") MultipartFile file) {

        String imageUrl = scrapService.uploadScrapImage(teamspaceId, folderId, file);
        return ResponseEntity.status(HttpStatus.CREATED).body(imageUrl);

    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = scrapService.uploadImage(file);
            return "File uploaded successfully! imageUrl: " + imageUrl;
        } catch (Exception e) {
            e.printStackTrace();
            return "File upload failed!";
        }
    }

}
