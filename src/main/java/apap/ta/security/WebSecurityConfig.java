package apap.ta.security;

import org.apache.catalina.authenticator.SpnegoAuthenticator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http
                .authorizeRequests()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/api/v2/**").permitAll()
                .antMatchers("/pegawai/list-pegawai").hasAnyAuthority("ADMIN", "FACTORY_MANAGER")
                .antMatchers("/pegawai/add").hasAnyAuthority("ADMIN")
                .antMatchers("/item/propose/**").hasAnyAuthority("FACTORY_MANAGER")
                .antMatchers("/item/propose").hasAnyAuthority("FACTORY_MANAGER")
                .antMatchers("/list-request-update-item").hasAnyAuthority("STAFF_OPERASIONAL", "STAFF_GUDANG")
                .antMatchers("/delivery/add/**").hasAnyAuthority("STAFF_OPERASIONAL")
                .antMatchers("/list-delivery").hasAnyAuthority("STAFF_OPERASIONAL", "STAFF_KURIR")
                .antMatchers("/item/update/**").hasAnyAuthority("STAFF_GUDANG")
                .antMatchers("/api/v1/daftar-alamat-cabang/**").hasAnyAuthority("STAFF_KURIR")
                .antMatchers("/request/update/**").hasAnyAuthority("STAFF_GUDANG")
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").permitAll()
                .and()
                .csrf().disable();
    }
    @Bean
    public BCryptPasswordEncoder encoder(){
     return new BCryptPasswordEncoder();
    }

     @Autowired
     public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
         auth.inMemoryAuthentication()
                 .passwordEncoder(encoder())
                 .withUser("kijangSatu").password(encoder().encode("nasiGoreng"))
                 .roles("ADMIN");
         auth.inMemoryAuthentication()
                 .passwordEncoder(encoder())
                 .withUser("kijangDua").password(encoder().encode("ayamGoreng"))
                 .roles("STAFF_GUDANG");
     }

   

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception{
        auth.userDetailsService(userDetailsService).passwordEncoder(encoder());
    }



}
