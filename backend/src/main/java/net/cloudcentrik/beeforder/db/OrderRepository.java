package net.cloudcentrik.beeforder.db;

import net.cloudcentrik.beeforder.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "orders", path = "orders")
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> findByName(@Param("name") String name);
    List<Order> findByMobile(@Param("mobile") String mobile);

}

