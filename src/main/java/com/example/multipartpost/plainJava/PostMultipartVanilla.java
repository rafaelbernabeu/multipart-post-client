package com.example.multipartpost.plainJava;

import lombok.SneakyThrows;
import org.apache.http.HttpEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;

import java.io.File;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class PostMultipartVanilla {

    @SneakyThrows
    public static void main(String[] args) {

        URL url = new URL("http://localhost:8080/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");

        FileBody fileBody = new FileBody(new File("/c/Users/deinf.abrao/projects/multipart-post/HELP.md"));
        FileBody fileBody2 = new FileBody(new File("/c/Users/deinf.abrao/projects/multipart-post/meuNome"));

        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();

        multipartEntityBuilder.addPart("filedata", fileBody);
        multipartEntityBuilder.addPart("filedata", fileBody2);

        HttpEntity build = multipartEntityBuilder.build();

        connection.setRequestProperty("Content-Type", build.getContentType().getValue());

        connection.setRequestProperty("my-custom-header", "my-custom-header-value");
        connection.setRequestProperty("my-custom-header2", "my-custom-header-value2");

        OutputStream out = connection.getOutputStream();

        try {
            build.writeTo(out);
        } finally {
            out.close();
        }

        int status = connection.getResponseCode();

        System.out.println(status);
    }

}
