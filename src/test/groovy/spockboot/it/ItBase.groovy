package spockboot.it

import groovy.util.logging.Slf4j

import org.springframework.boot.context.embedded.LocalServerPort
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.test.context.ContextConfiguration

import spock.lang.Specification


@ContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class ItBase extends Specification{
	
	@LocalServerPort
	int port

	TestRestTemplate template = new TestRestTemplate()
	
	public URI serviceUri(String path) throws URISyntaxException{
		return new URI("http://localhost:" + port + path)
	}

}
