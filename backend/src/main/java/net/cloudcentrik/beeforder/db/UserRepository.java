package net.cloudcentrik.beeforder.db;

import net.cloudcentrik.beeforder.model.Order;
import net.cloudcentrik.beeforder.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByEmail(@Param("email") String email);
}
