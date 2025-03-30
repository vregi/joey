package utils;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

public class AWSManager {
    public static String getToken(){
        AwsBasicCredentials credentials = AwsBasicCredentials.create(System.getenv("AWS_ACCESS_KEY"), System.getenv("AWS_SECRET_KEY"));
        String secretName = System.getenv("SECRET_NAME");
        Region region = Region.of(System.getenv("AWS_REGION"));



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
