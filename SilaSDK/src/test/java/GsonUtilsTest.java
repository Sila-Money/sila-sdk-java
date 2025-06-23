import com.silamoney.client.domain.GetVerificationsResponse;
import com.silamoney.client.domain.ResumeVerificationResponse;
import com.silamoney.client.util.Serialization;
import org.junit.Assert;
import org.junit.Test;

import java.time.Instant;

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
        Assert.assertEquals(Instant.parse("2023-10-01T12:00:00Z"), response.getVerifications().get(0).getRequestedAt());
    }

    @Test
    public void testResumeVerificationResponseSerde() {
        Object resp = Serialization.deserialize("{\"success\":false,\"message\":\"Verification d7229ddb-6e63-4565-b138-a589e251b406 not found.\",\"status\":\"FAILURE\",\"reference\":\"bb13c6be-f338-4fb2-b154-c72a52a0b999\",\"sila_reference_id\":\"req_9aicvy4mc1p5ndsqrtumwsv89g\",\"response_time_ms\":\"85\"}",
                ResumeVerificationResponse.class);
        ResumeVerificationResponse response = (ResumeVerificationResponse) resp;
        Assert.assertEquals("Verification d7229ddb-6e63-4565-b138-a589e251b406 not found.", response.getMessage());
    }
}
