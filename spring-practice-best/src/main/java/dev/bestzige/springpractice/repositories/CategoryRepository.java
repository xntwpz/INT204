package dev.bestzige.springpractice.repositories;

import dev.bestzige.springpractice.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
