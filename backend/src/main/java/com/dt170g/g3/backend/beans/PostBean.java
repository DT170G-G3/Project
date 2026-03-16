package com.dt170g.g3.backend.beans;
import com.dt170g.g3.backend.entities.Event;
import com.dt170g.g3.backend.services.EventService;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.Part;


import com.dt170g.g3.backend.entities.Post;
import com.dt170g.g3.backend.services.PostService;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Named("postBean")
@ViewScoped
public class PostBean implements Serializable {
    @Inject
    private PostService postService;

    @Inject
    private EventService eventService;


    private Part imageFile;
    private int selectedId;




    public void createPost(){
        try{

            if (imageFile == null || imageFile.getSubmittedFileName() == null) {
                System.out.println("Ingen bild vald");
                return;
            }


            Post newPost = new Post();
            String filename = imageFile.getSubmittedFileName(); // filename from uploaded file
            System.out.println(filename + "namn");
            String folder = FacesContext.getCurrentInstance()// get full path
                    .getExternalContext()
                    .getRealPath("/resources/images/event/past");



            
            Path target = Paths.get(folder, filename); // target path to folder and filename
            Files.copy(imageFile.getInputStream(), target, StandardCopyOption.REPLACE_EXISTING); // copy the files from input stream to the target
            System.out.println("Folder: " + folder);
            System.out.println("Target: " + target);
            String imagePath = "resources/images/event/past/" + filename; // path to save in database

            newPost.setImagePath(imagePath);
            Event event = eventService.getEventById(selectedId);
            newPost.setEvent(event);
            postService.createPost(newPost);



        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void setImageFile(Part imageFile) {
        this.imageFile = imageFile;
    }

    public Part getImageFile(){
        return imageFile;
    }

    public void setSelectedId(int selectedId) {
        this.selectedId = selectedId;
    }

    public int getSelectedId() {
        return selectedId;
    }
}
