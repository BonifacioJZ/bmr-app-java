package com.bonifacio.app.services;

import com.bonifacio.app.entities.Category;
import com.bonifacio.app.repositories.ICategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class CategoryService implements ICategoryService{
    private final ICategoryRepository categoryRepository;
    /**
     * @param entity 
     * @param <S>
     * @return
     */
    @Override
    public <S extends Category> S save(S entity) {
        return categoryRepository.save(entity);
    }

    /**
     * @param entities 
     * @param <S>
     * @return
     */
    @Override
    public <S extends Category> Iterable<S> saveAll(Iterable<S> entities) {
        return categoryRepository.saveAll(entities);
    }

    /**
     * @param uuid 
     * @return
     */
    @Override
    public Optional<Category> findById(UUID uuid) {
        return categoryRepository.findById(uuid);
    }

    /**
     * @param uuid 
     * @return
     */
    @Override
    public boolean existsById(UUID uuid) {
        return categoryRepository.existsById(uuid);
    }

    /**
     * @return 
     */
    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    /**
     * @param uuids 
     * @return
     */
    @Override
    public Iterable<Category> findAllById(Iterable<UUID> uuids) {
        return categoryRepository.findAllById(uuids);
    }

    /**
     * @return 
     */
    @Override
    public long count() {
        return categoryRepository.count();
    }

    /**
     * @param uuid 
     */
    @Override
    public void deleteById(UUID uuid) {
        categoryRepository.deleteById(uuid);
    }

    /**
     * @param entity 
     */
    @Override
    public void delete(Category entity) {
        categoryRepository.delete(entity);
    }

    /**
     * @param uuids 
     */
    @Override
    public void deleteAllById(Iterable<? extends UUID> uuids) {
        categoryRepository.deleteAllById(uuids);
    }

    /**
     * @param entities 
     */
    @Override
    public void deleteAll(Iterable<? extends Category> entities) {
        categoryRepository.deleteAll(entities);
    }

    /**
     * 
     */
    @Override
    public void deleteAll() {
        categoryRepository.deleteAll();
    }
}
