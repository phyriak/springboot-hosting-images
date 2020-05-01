package pl.phyriak.springboothostingimages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.phyriak.springboothostingimages.model.AppUser;
import pl.phyriak.springboothostingimages.repo.AppUserRepo;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private AppUserRepo appUserRepo;
    private UserDetailServiceImpl userDetail;

    @Autowired
    public WebSecurityConfig(UserDetailServiceImpl userDetail, AppUserRepo appUserRepo) {
        this.userDetail = userDetail;
        this.appUserRepo=appUserRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetail);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               .antMatchers("/test1").hasRole("USER")
               .antMatchers("/test2").hasRole("ADMIN")
               .antMatchers("/gallery").hasAnyRole("ADMIN","USER")
               .antMatchers("/upload").hasRole("ADMIN")
               .and()
               .formLogin()
               .permitAll()
               .and()
               .csrf().disable();
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get(){
        AppUser appUserUser=new AppUser("peter",getPasswordEncoder().encode("peter"),"ROLE_USER");
        AppUser appUserAdmin=new AppUser("admin",getPasswordEncoder().encode("peter"),"ROLE_ADMIN");
        appUserRepo.save(appUserUser);
        appUserRepo.save(appUserAdmin);
    }
}
