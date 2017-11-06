package com.wizardsofcode.deckmanagerserver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@PropertySource(value = "/test.properties")
public class DeckmanagerserverApplicationTests {

	@Test
	public void contextLoads() {
	}

}
