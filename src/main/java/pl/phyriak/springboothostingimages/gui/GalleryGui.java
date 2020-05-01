package pl.phyriak.springboothostingimages.gui;

import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.phyriak.springboothostingimages.model.Image;
import pl.phyriak.springboothostingimages.repo.ImageRepo;

import java.util.List;

@Route("gallery")
public class GalleryGui extends VerticalLayout {
    private ImageRepo imageRepo;

    @Autowired
    public GalleryGui(ImageRepo imageRepo) {
        this.imageRepo = imageRepo;

        List<Image> all =imageRepo.findAll();

        all.stream().forEach(e->{
            com.vaadin.flow.component.html.Image image =
                    new com.vaadin.flow.component.html.Image(e.getImageAdress(),"brak");
            add(image);
        });
    }
}
