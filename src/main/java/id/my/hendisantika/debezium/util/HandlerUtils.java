package id.my.hendisantika.debezium.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import id.my.hendisantika.debezium.entity.Product;
import lombok.experimental.UtilityClass;
import org.apache.kafka.connect.data.Struct;

import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-cdc-with-debezium
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 5/25/24
 * Time: 05:45
 * To change this template use File | Settings | File Templates.
 */
@UtilityClass
public class HandlerUtils {
    /**
     * Extracts the document ID from the given Struct object.
     *
     * @param key The Struct object containing the document information.
     * @return The extracted document ID, or null if not found.
     */
    public static String getDocumentId(Struct key) {
        String id = key.getString("id");
        Matcher matcher = Pattern.compile("\"\\$oid\":\\s*\"(\\w+)\"").matcher(id);
        return matcher.find() ? matcher.group(1) : null;
    }

    /**
     * Extracts the collection name from the source record value.
     *
     * @param sourceRecordValue The Struct object representing the source record.
     * @return The name of the collection.
     */
    public static String getCollection(Struct sourceRecordValue) {
        Struct source = (Struct) sourceRecordValue.get("source");
        return source.getString("collection");
    }

    /**
     * Deserializes the 'after' field of the source record value into a Product object.
     *
     * @param sourceRecordValue The Struct object representing the source record.
     * @return The deserialized Product object.
     * @throws IOException If there is an error during deserialization.
     */
    public static Product getData(Struct sourceRecordValue) throws IOException {
        if (Objects.nonNull(sourceRecordValue.get("after"))) {
            var source = sourceRecordValue.get("after").toString();
            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(source, Product.class);
        }
        return null;
    }
}
