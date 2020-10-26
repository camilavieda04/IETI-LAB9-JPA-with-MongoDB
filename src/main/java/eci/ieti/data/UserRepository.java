package eci.ieti.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import eci.ieti.data.model.User;

public interface UserRepository extends CrudRepository<User, Long>{

    User findByName(String name);

    User findByEmail(String email);
}
