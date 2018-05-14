package org.todeschini.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.todeschini.model.User;

/**
 * Created by Artur on 14/05/18.
 */
public interface UserRepository extends PagingAndSortingRepository<User,Integer> {

}
