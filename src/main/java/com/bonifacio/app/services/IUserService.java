package com.bonifacio.app.services;

import com.bonifacio.app.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface IUserService extends CrudRepository<User, UUID> {
}
