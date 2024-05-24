package id.my.hendisantika.debezium.repository;

import id.my.hendisantika.debezium.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-cdc-with-debezium
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/25/24
 * Time: 05:44
 * To change this template use File | Settings | File Templates.
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    void removeProductByMongoId(String mongoId);

    Product findByMongoId(String mongoId);
}
