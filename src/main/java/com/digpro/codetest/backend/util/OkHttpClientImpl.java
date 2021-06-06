package com.digpro.codetest.backend.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.digpro.codetest.backend.repository.CoordinateRepositryImpl;

import java.io.IOException;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * {@link HttpClient} Implementation using OkHttpClient Library
 * 
 * @author Mutaz Hussein Kabashi
 * @version 1.0
 * @see <a href="https://square.github.io/okhttp"/>OkHttp</a>
 *
 */
@Component
public class OkHttpClientImpl implements HttpClient {
	
    private static final Logger LOG = LoggerFactory.getLogger(OkHttpClientImpl.class);
	OkHttpClient client = new OkHttpClient();

	@Override
	public String doGetRequest(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
        LOG.info("getting data from  servlet");
		try (Response response = client.newCall(request).execute()) {
			return response.body().string();
		}
	}


}
