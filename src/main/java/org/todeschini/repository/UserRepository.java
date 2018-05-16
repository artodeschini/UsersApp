package org.todeschini.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.todeschini.model.Users;

import java.util.List;

/**
 * Created by Artur on 14/05/18.
 */
public interface UserRepository extends PagingAndSortingRepository<Users,Integer> {
    List<Users> findByNameIgnoreCaseContaining(String name);

    List<Users> findByEmailIgnoreCaseContaining(String email);

    List<Users> findByUsernameIgnoreCaseContaining(String username);
}