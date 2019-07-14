package com.fanhehe.message.util;


import java.util.Map;
import java.util.ArrayList;
import java.io.IOException;

import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.fluent.Request;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.stereotype.Service;
import com.fanhehe.message.constant.ServiceEnum;

@Service("HttpUtil")
public abstract class HttpUtil<T> implements IHttpUtil<T> {

    private static final String GET = "GET";
    private static final String POST = "POST";

    private String scheme = "http://";
    private int socketTimeout = 2000;
    private int connectTimeout = 2000;

    private HttpVersion httpVersion = HttpVersion.HTTP_1_1;

    abstract public ServiceEnum cmlb();

    private String getEndpoint() {
        return cmlb().getEndpoint();
    }

    @Override
    public IResult<T> call(String path, Map<String, String> params, Map<String, String> headers, String method) {

        Request request;
        String uri = String.join("", scheme, getEndpoint(), path);

        switch (method) {
            case GET:
                StringBuilder sb = new StringBuilder();
                for (Map.Entry<String, String> item: params.entrySet()) {
                    sb = sb
                            .append(item.getKey())
                            .append('=')
                            .append(item.getValue())
                            .append('&');
                }
                sb.deleteCharAt(sb.length() - 1);
                uri = String.join("?", uri, sb.toString());
                request = Request.Get(uri);
                break;
            case POST:
                ArrayList<NameValuePair> entity = new ArrayList<>(params.size());

                for (Map.Entry<String, String> item: params.entrySet()) {
                    entity.add(new NameValuePair() {
                        @Override
                        public String getName() {
                            return item.getKey();
                        }

                        @Override
                        public String getValue() {
                            return item.getValue();
                        }
                    });
                }

                request = Request.Post(uri).bodyForm(entity);
                break;
            default:
                return null;
        }

        for (Map.Entry<String, String> item: headers.entrySet()) {
            request.addHeader(item.getKey(), item.getValue());
        }

        request
                .version(httpVersion)
                .socketTimeout(socketTimeout)
                .connectTimeout(connectTimeout);

        String content;

        try {
            content = request
                    .execute()
                    .returnContent().asString();
        } catch (IOException e) {
            e.printStackTrace();
            return InvokeResult.failure("");
        }

        JacksonJsonParser jacksonJsonParser = new JacksonJsonParser();

        Map<String, Object> result = jacksonJsonParser.parseMap(content);

        if (result != null && result.get("code") != null && (int)result.get("code") == 0) {
            return InvokeResult.success((T)result.get("data"));
        }

        return InvokeResult.failure("");
    }
}