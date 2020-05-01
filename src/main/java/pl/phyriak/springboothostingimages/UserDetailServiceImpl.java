package pl.phyriak.springboothostingimages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.phyriak.springboothostingimages.repo.AppUserRepo;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private AppUserRepo appUserRepo;

    @Autowired
    public UserDetailServiceImpl(AppUserRepo appUserRepo) {
        this.appUserRepo = appUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return appUserRepo.findByUsername(username);
    }
}
