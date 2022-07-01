import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.flipkart.zjsonpatch.DiffFlags;
import com.flipkart.zjsonpatch.JsonDiff;
import com.google.gson.Gson;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

public class ChangeLogOpenApi {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

        JsonNode file1 = objectMapper.readTree(
                new File("/home/lucaslima/Downloads/accounts-1.0.3.yml"));

        JsonNode file2 = objectMapper.readTree(
                new File("/home/lucaslima/Downloads/accounts-2.0.0.yml"));

        JsonNode patch = JsonDiff.asJson(
                file1,
                file2,
                EnumSet.of(DiffFlags.ADD_ORIGINAL_VALUE_ON_REPLACE));

        String diffs = patch.toString();
        System.out.println(diffs);
    }
}
