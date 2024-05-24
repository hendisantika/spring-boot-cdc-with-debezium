package id.my.hendisantika.debezium.util;

import lombok.experimental.UtilityClass;
import org.apache.kafka.connect.data.Struct;

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
}
