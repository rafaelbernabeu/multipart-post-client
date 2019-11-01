package com.example.multipartpost.plainJava;

import lombok.SneakyThrows;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
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

        MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.STRICT);

        multipartEntity.addPart("filedata", fileBody);
        multipartEntity.addPart("filedata", fileBody2);

        connection.setRequestProperty("Content-Type", multipartEntity.getContentType().getValue());
        OutputStream out = connection.getOutputStream();

        try {
            multipartEntity.writeTo(out);
        } finally {
            out.close();
        }

        int status = connection.getResponseCode();

        System.out.println(status);
    }

}
