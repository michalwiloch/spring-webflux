package com.softwarehut.dataservice.demo;

import com.softwarehut.dataservice.demo.model.Fuel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebfluxServerApplicationTests {

	@Autowired
	private WebTestClient webTestClient;

	@Test
	public void shouldGet40Records(){
		webTestClient
				.get().uri("/rest/mongodata/fuel/event")
				.accept(MediaType.TEXT_EVENT_STREAM)
				.exchange()
				.expectStatus().isOk()
				.expectBodyList(Fuel.class)
				.hasSize(40);
	}

}
