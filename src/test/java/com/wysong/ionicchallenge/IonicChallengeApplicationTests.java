package com.wysong.ionicchallenge;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wysong.ionicchallenge.rest.CombinedReturnBody;
import com.wysong.ionicchallenge.rest.InputBody;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IonicChallengeApplicationTests {

	@Autowired
	private TestRestTemplate restTemplate;

    private final ObjectMapper objectMapper = new ObjectMapper();

	private Integer[][] inputs = {
			{5, 0, 25, 5, 145, 250},
			{0, 5, 95, 115, 165, 250},
			{15, 5, 175, 250, 185, 160},
			{5, 5, 145, 250, 245, 140},
			{115, 210, 60, null, 230, 220},
			{55, 5, 175, 150, 170, 145}
	};
	private int threshold = 200;

	@Test
	public void contextLoads() {
	}

//	***********  Happy Path Tests  **************
	@Test
	public void testAdjacentEndpoint() throws IOException {
		InputBody inputBody = new InputBody(inputs, threshold);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("/adjacents", inputBody, String.class);
        assertThat(stringResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Set<Set<Point>> allAdjacents = getAdjacentSets();
        Set<Set<Point>> toCheck = new HashSet<>(3);
        JsonNode jsonNode = objectMapper.readTree(stringResponseEntity.getBody());
        System.out.println(jsonNode);
        if (jsonNode.isArray()) {
            for (JsonNode node : jsonNode) {
                System.out.println(node);
                Set<Point> adjacents = getPoints(node);
                toCheck.add(adjacents);
            }
        }
        for (Set<Point> allAdjacent : allAdjacents) {
            assertThat(toCheck).containsOnlyOnce(allAdjacent);
        }
	}

    @Test
    public void testCenterOfMassEndpoint() throws IOException {
		InputBody inputBody = new InputBody(inputs, threshold);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("/centermass", inputBody, String.class);
        assertThat(stringResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Point p15 = new Point(1, 5);
        Point p24 = new Point(3, 4);
        Point p41 = new Point(4, 1);

        JsonNode jsonNode = objectMapper.readTree(stringResponseEntity.getBody());
        System.out.println(jsonNode);
        if (jsonNode.isArray()) {
            Set<Point> points = getPoints(jsonNode);
            assertThat(points).containsExactlyInAnyOrder(p15, p24, p41);
        }
    }

    @Test
    public void testCenterOfMassWithAdjacentsEndpoint() throws IOException {
		InputBody inputBody = new InputBody(inputs, threshold);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("/centermass/adjacents", inputBody, String.class);
        assertThat(stringResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);

        Point p15 = new Point(1, 5);
        Point p24 = new Point(3, 4);
        Point p41 = new Point(4, 1);

        JsonNode jsonNode = objectMapper.readTree(stringResponseEntity.getBody());
        System.out.println(jsonNode);
        if (jsonNode.isArray()) {
            Set<CombinedReturnBody> combinedReturnBodies = new HashSet<>(3);
            for (JsonNode node : jsonNode) {
                CombinedReturnBody combinedReturnBody = objectMapper.treeToValue(node, CombinedReturnBody.class);
                assertThat(combinedReturnBody.getCenterOfMass()).isIn(p15, p24, p41);
            }
        }
    }

    private Set<Point> getPoints(JsonNode node) throws JsonProcessingException {
        Set<Point> points = new HashSet<>();
        for (JsonNode jsonPoints : node) {
            Point point = objectMapper.treeToValue(jsonPoints, Point.class);
            points.add(point);
        }
        return points;
    }

    private Set<Set<Point>> getAdjacentSets() {
        Set<Set<Point>> allAdjacents = new HashSet<>(3);

        Set<Point> adj1 = new HashSet<>(1);
        adj1.add(new Point(4, 1));
        allAdjacents.add(adj1);

        Set<Point> adj2 = new HashSet<>(2);
        adj2.add(new Point(0, 5));
        adj2.add(new Point(1, 5));
        allAdjacents.add(adj2);

        Set<Point> adj3 = new HashSet<>(5);
        adj3.add(new Point(2, 3));
        adj3.add(new Point(3, 3));
        adj3.add(new Point(3, 4));
        adj3.add(new Point(4, 4));
        adj3.add(new Point(4, 5));
        allAdjacents.add(adj3);

        return allAdjacents;
    }
//	***********  Unhappy Path Tests  **************
    @Test
    public void testNullBodies() {
		InputBody inputBody = new InputBody(null, threshold);

        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("/adjacents", "NOPE!!!", String.class);
        assertThat(stringResponseEntity.getStatusCode()).isEqualTo(HttpStatus.UNSUPPORTED_MEDIA_TYPE);

        stringResponseEntity = restTemplate.postForEntity("/centermass", inputBody, String.class);
        assertThat(stringResponseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(stringResponseEntity.getBody()).contains("Input must not be null");

        stringResponseEntity = restTemplate.postForEntity("/centermass/adjacents", inputBody, String.class);
        assertThat(stringResponseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(stringResponseEntity.getBody()).contains("Input must not be null");

        stringResponseEntity = restTemplate.postForEntity("/adjacents", inputBody, String.class);
        assertThat(stringResponseEntity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(stringResponseEntity.getBody()).contains("Input must not be null");
    }

}
