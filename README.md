# multipart-post-client

URL url = new URL("http://localhost:8080/");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
//        connection.setDoOutput(true);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("param1", "val");

        connection.setRequestProperty("Content-Type", MediaType.TEXT_PLAIN_VALUE);
        connection.setRequestProperty("Accept-Encoding", "gzip");
        connection.setRequestProperty("my-custom-header2", "my-custom-header-value2");

//        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
//        out.writeBytes("RaB");
//        out.flush();
//        out.close();

        int responseCode = connection.getResponseCode();
        System.out.println("### HEADERS:");
        connection.getHeaderFields().forEach((k, v) -> System.out.printf("%s: %s\n", k, v));

        InputStream inputStream = connection.getInputStream();
        byte[] bytes = IOUtils.toByteArray(inputStream);

        String conteudo = new String(bytes);
        String boundary = conteudo.substring(2, conteudo.indexOf("\r\n"));

        ByteArrayDataSource ds = new ByteArrayDataSource(bytes, "multipart/mixed;boundary=" + boundary);
        MimeMultipart multipart = new MimeMultipart(ds, new ContentType("multipart", "mixed", new ParameterList(";boundary=" + boundary)));
        System.out.println(multipart.getCount());

        System.out.println(conteudo);
//        System.out.println(multipart);
