package com.starry.controller;

import com.starry.stroage.StorageFileNotFoundException;
import com.starry.stroage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.stream.Collectors;

/**
 * FileUploadController:
 */
@Controller
public class FileUploadController {
    private final StorageService strongService;
    @Autowired
    public FileUploadController(StorageService strongService) {
        this.strongService = strongService;
    }
    @GetMapping("/")
    public String listUploadFile(Model model) throws IOException{
        model.addAttribute("files",strongService.loadAll().map(
                path -> MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                        "saveFile",path.getFileName().toString()).build().toString()).collect(Collectors.toList()));
        return "uploadForm";
    }
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> saveFile(@PathVariable String filename){
        Resource resource = strongService.loadAsResource(filename);
        return  ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment;filename=\""+resource.getFilename()+"\"").body(resource);

    }
    @PostMapping("/")
    public String handleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        strongService.store(file);
        redirectAttributes.addFlashAttribute("message",
                "you successfuly upload "+ file.getOriginalFilename()+"!");
        return "redirect:/";

    }
    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }

}
