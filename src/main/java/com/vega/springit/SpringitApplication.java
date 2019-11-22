package com.vega.springit;

import com.vega.springit.domain.Comment;
import com.vega.springit.domain.Link;
import com.vega.springit.repository.CommentRepository;
import com.vega.springit.repository.LinkRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpringitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringitApplication.class, args);
    }

    //(Temporary) Manual database fields entry
    @Bean
    CommandLineRunner runner(LinkRepository linkRepository, CommentRepository commentRepository) {
        return args -> {

            //database row 1
            Link link = new Link("Search engine", "https://google.com");
            linkRepository.save(link);

            Comment comment = new Comment("This site is lets you find anything", link);
            commentRepository.save(comment);
            link.addComment(comment);

            //database row 2
            Link link2 = new Link("news & social", "https://msn.com");
            linkRepository.save(link2);

            Comment comment2 = new Comment("This site was once awesome", link2);
            commentRepository.save(comment2);
            link2.addComment(comment2);

            //database row 3
            Link link3 = new Link("Biased Social Media", "https://facebook.com");
            linkRepository.save(link3);

            Comment comment3 = new Comment("..Just another crap.. unless your leftwing", link3);
            commentRepository.save(comment3);
            link3.addComment(comment3);

            // My request:
            Link gettingFacebookLink = linkRepository.findByTitle("Biased Social Media");
            //Calling my actual get Method &//Just printing result to console. Instead I could save it as a string & use it in my html
            System.out.println(gettingFacebookLink.getTitle());

            Link gettingMsnLink = linkRepository.findByTitle("Biased Social Media");
        };
    }
}