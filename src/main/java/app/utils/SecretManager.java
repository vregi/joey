package app.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

@Component
public class SecretManager {
    private final Config config;

    @Autowired
    public SecretManager(Config config) {
        this.config = config;
    }


    public String getToken(){
        AwsBasicCredentials credentials = AwsBasicCredentials.create(config.get("AWS_ACCESS_KEY"), config.get("AWS_SECRET_KEY"));
        String secretName = config.get("SECRET_NAME");
        Region region = Region.of(config.get("AWS_REGION"));



        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse;

        // Create a Secrets Manager client
        try (SecretsManagerClient client = SecretsManagerClient.builder()
                .region(region)
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build()) {
            getSecretValueResponse = client.getSecretValue(getSecretValueRequest);
        }



        return Json.getValue(getSecretValueResponse.secretString(), "token");
    }
}
