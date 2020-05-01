package pl.phyriak.springboothostingimages.repo;

import org.apache.commons.codec.language.bm.Lang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.phyriak.springboothostingimages.model.Image;

@Repository
public interface ImageRepo  extends JpaRepository<Image, Lang> {
}
