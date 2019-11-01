package com.example.multipartpost.clientSpring;

import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.FileInputStream;

public class PostMultipartSpring {

    @SneakyThrows
    public static void main(String[] args) {

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost uploadFile = new HttpPost("http://localhost:8080/");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addTextBody("field1", "yes", ContentType.TEXT_PLAIN);
        builder.addTextBody("field2", "no", ContentType.TEXT_PLAIN);
        builder.addTextBody("field3", "myCustonContent", ContentType.TEXT_PLAIN);

        // This attaches the file to the POST:
        File f = new File("/c/Users/deinf.abrao/projects/multipart-post/HELP.md");
        File f2 = new File("/c/Users/deinf.abrao/projects/multipart-post/meuNome");

        builder.addBinaryBody("filedata", new FileInputStream(f), ContentType.APPLICATION_OCTET_STREAM, f.getName());
        builder.addBinaryBody("filedata", new FileInputStream(f2), ContentType.APPLICATION_OCTET_STREAM, f2.getName());

        HttpEntity multipart = builder.build();
        uploadFile.setEntity(multipart);
        CloseableHttpResponse response = httpClient.execute(uploadFile);
        HttpEntity responseEntity = response.getEntity();

    }

}
