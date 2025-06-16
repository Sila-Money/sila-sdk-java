import com.silamoney.client.domain.GetVerificationsResponse;
import com.silamoney.client.util.GsonUtils;
import com.silamoney.client.util.Serialization;
import org.junit.Assert;
import org.junit.Test;

public class GsonUtilsTest {

    @Test
    public void testInstantSerialization() {
        Object resp = Serialization.deserialize("{\n" +
                        "\t\"verifications\":[\n" +
                        "\t{\n" +
                        "\t\t\"verification_uuid\": \"12321312312\",\n" +
                        "\t\t\"flow\" : \"test\",\n" +
                        "\t\t\"requested_at\" : \"2023-10-01T12:00:00Z\"\n" +
                        "\t}\n" +
                        "\t]\n" +
                        "}",
                GetVerificationsResponse.class);
        GetVerificationsResponse response = (GetVerificationsResponse) resp;
        Assert.assertEquals("12321312312", response.getVerifications().get(0).getVerificationUuid());
    }
}
