package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Stream;


@SpringBootApplication
@RestController
@CrossOrigin(origins = "http://localhost:4200")
@EnableJpaRepositories
public class DemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(DemoApplication.class, args);
    }
// provides information such as the username, roles, and other details associated with the authenticated user
   // @RequestMapping("/user")
  //  public Principal user(Principal user) {
   //     return user;
 //   }


    // this is just for testing purposes to fill the db with some users and rooms
    @Bean
    CommandLineRunner init(PlanUserRepository planUserRepository, RoomRepository roomRepository) {
        return args -> {
            Stream.of("John", "Julie", "Jennifer", "Helen", "Rachel").forEach(name -> {
                PlanUser planUser = new PlanUser(name, name.toLowerCase() + "@domain.com");
                planUserRepository.save(planUser);
            });
            planUserRepository.findAll().forEach(System.out::println);

            Stream.of("Küche", "Bad", "Wohnzimmer").forEach(name -> {
                 Room room = new Room(name);
                roomRepository.save(room);
            });
            roomRepository.findAll().forEach(System.out::println);
        };
    }



    /*@Configuration
    @EnableWebSecurity
    public class SecurityConfiguration {

        *//*public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
            return httpSecurity
                    .csrf(csrf -> csrf.disable())
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/token/**").permitAll()
                            .anyRequest().authenticated()
                    )
                    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt)
                    .httpBasic(Customizer.withDefaults())
                    .build();
        }*//*


        @Bean
        public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(requests -> requests
                            .requestMatchers("/user").permitAll()
                            .requestMatchers("/users").permitAll()
                            //.requestMatchers("/users").hasRole("Admin")
                            .anyRequest().authenticated()
                    );//.oauth2Login((oauth2Login) -> oauth2Login
                    //.userInfoEndpoint((userInfo) -> userInfo
                     //       .userAuthoritiesMapper(grantedAuthoritiesMapper())
                   // )
            //);
            http.addFilterBefore(new CustomFilter(), UsernamePasswordAuthenticationFilter.class); // Add the custom filter before the UsernamePasswordAuthenticationFilter

            return http.build();
        }
        private GrantedAuthoritiesMapper grantedAuthoritiesMapper() {
            return (authorities) -> {
                System.out.println("Recieved Authorities from IDentProvider");
                Set<GrantedAuthority> mappedAuthorities = new HashSet<>();

                authorities.forEach((authority) -> {
                    GrantedAuthority mappedAuthority;

                    if (authority instanceof OidcUserAuthority) {
                        OidcUserAuthority userAuthority = (OidcUserAuthority) authority;
                        mappedAuthority = new OidcUserAuthority(
                                "OIDC_USER", userAuthority.getIdToken(), userAuthority.getUserInfo());
                    } else if (authority instanceof OAuth2UserAuthority) {
                        OAuth2UserAuthority userAuthority = (OAuth2UserAuthority) authority;
                        mappedAuthority = new OAuth2UserAuthority(
                                "OAUTH2_USER", userAuthority.getAttributes());
                    } else {
                        mappedAuthority = authority;
                    }
                    // Print the received authorities
                    mappedAuthorities.forEach(System.out::println);
                    mappedAuthorities.add(mappedAuthority);
                });

                return mappedAuthorities;
            };
        }
        public class CustomFilter extends OncePerRequestFilter {
            @Override
            protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if (authentication != null) {
                    System.out.println("Authorities:");
                    authentication.getAuthorities().forEach(System.out::println);
                }
                if (request.getRequestURI().equals("/users")  && authentication!=null && !authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("Admin"))) {
                   System.out.println("User could not be authentificated, SecFilterChain was triggered");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                } else {
                    System.out.println("User could be authentificated, SecFilterChain was triggered");
                    filterChain.doFilter(request, response);
                }
            }
        }
    }*/


}
