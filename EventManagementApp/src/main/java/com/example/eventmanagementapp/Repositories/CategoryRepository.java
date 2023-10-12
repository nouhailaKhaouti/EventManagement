package com.example.eventmanagementapp.Repositories;

import com.example.eventmanagementapp.Domain.Category;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class CategoryRepository {
    private final EntityManagerFactory entityManagerFactory;

    public CategoryRepository() {
        entityManagerFactory = Persistence.createEntityManagerFactory("my-persistence-unit");
    }

    public void save(Category category) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(category);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public Category findById(Long id) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        Category category = entityManager.find(Category.class, id);
        entityManager.close();
        return category;
    }

    public List<Category> getAllCategorys() {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        List<Category> categorys = entityManager.createQuery("SELECT u FROM Category u", Category.class).getResultList();
        entityManager.close();
        return categorys;
    }

    public void update(Long categoryId, Category updatedCategory) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Category category = entityManager.find(Category.class, categoryId);
        if (category != null) {
            category.setName(updatedCategory.getName());
            category.setDescription(updatedCategory.getDescription());
            entityManager.merge(category);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }

    public void delete(Long categoryId) {
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Category category = entityManager.find(Category.class, categoryId);
        if (category != null) {
            entityManager.remove(category);
        }

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}