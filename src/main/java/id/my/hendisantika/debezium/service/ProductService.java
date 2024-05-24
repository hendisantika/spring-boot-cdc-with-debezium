package id.my.hendisantika.debezium.service;

import id.my.hendisantika.debezium.entity.Product;
import id.my.hendisantika.debezium.repository.ProductRepository;
import io.debezium.data.Envelope;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Service
@AllArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    @Transactional
    public void handleEvent(String operation, String documentId, String collection, Product product) {
        // Check if the operation is either CREATE or READ
        if (Envelope.Operation.CREATE.code().equals(operation) || Envelope.Operation.READ.code().equals(operation)) {
            // Set the MongoDB document ID to the product
            product.setMongoId(documentId);
            product.setSourceCollection(collection);
            // Save the updated product information to the database
            productRepository.save(product);

            // If the operation is DELETE
        } else if (Envelope.Operation.UPDATE.code().equals(operation)) {
            var productToUpdate = productRepository.findByMongoId(documentId);
            product.setId(productToUpdate.getId());
            product.setMongoId(documentId);
            product.setSourceCollection(collection);
            productRepository.save(product);
        }
        // If the operation is DELETE
        else if (Envelope.Operation.DELETE.code().equals(operation)) {
            // Remove the product from the database using the MongoDB document ID
            productRepository.removeProductByMongoId(documentId);
        }
    }
}
