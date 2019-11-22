package com.vega.springit.repository;

import com.vega.springit.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

//Spring Data JPA writes the sql queries under the hood
public interface LinkRepository extends JpaRepository<Link,Long> {

//  "Link"I wanna get a link back, "fBT" my query choice from Jpa, "(Str title)" My parameter I want to pass it
    Link findByTitle(String title);

}
