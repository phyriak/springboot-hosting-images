package pl.phyriak.springboothostingimages.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.phyriak.springboothostingimages.model.AppUser;

@Repository
public interface AppUserRepo extends JpaRepository  <AppUser,Long>{

    AppUser findByUsername(String username);
}
