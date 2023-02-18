package com.sber.filmlibrary.library.repository;

import com.sber.filmlibrary.library.model.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository
        extends GenericRepository<Users> {
}
