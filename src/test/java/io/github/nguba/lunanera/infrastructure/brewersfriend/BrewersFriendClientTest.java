package io.github.nguba.lunanera.infrastructure.brewersfriend;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.concurrent.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

@SpringBootTest(classes = {BrewersFriendClient.class})
@ActiveProfiles("test")
class BrewersFriendClientTest {

    public static final RecipeResponse NICOSTINER_DUNKEL =
            new RecipeResponse(1240852, "Nicostiner Dunkel", 0, 13.4184f, 5.6f);

    private final RestTemplate rest = new RestTemplate();

    @Value("${brewersfriend.api.key}")
    String key;

    @Autowired
    BrewersFriendClient client;

    @Test
    void hasApiKeyHeaderEntry() {
        HttpEntity<Object> entity = client.makeRequestEntity();
        assertThat(entity.getHeaders().containsKey("X-API-Key")).isTrue();
    }

    @Test
    void hasApiKeyHeaderValue() {
        HttpEntity<Object> entity = client.makeRequestEntity();
        assertThat(entity.getHeaders().getFirst("X-API-Key")).isEqualTo(key);
    }

    @Test
    void findAllRecipes() {
        Collection<RecipeResponse> recipes = client.getRecipes();
        assertThat(recipes).asList().contains(NICOSTINER_DUNKEL);
    }

    @Test
    void loadRecipe() throws Exception {
        assertThat(client.getRecipe(1240852)).isEqualTo(NICOSTINER_DUNKEL);
    }

    @Test
    void loadRecipe_Unknown() {
        assertThatExceptionOfType(FileNotFoundException.class).isThrownBy(() -> client.getRecipe(1240850));
    }

    @Test
    @Disabled
    void findAllBrewSessions() {
        Collection<BrewSessionResponse> recipes = client.getBrewSessions();

        assertThat(recipes).asList().contains(new BrewSessionResponse(424680, 1263445));
    }

    @Test
    @Disabled
    void submitReading() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(10);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            float startTemp = 6.2f;
            float temp = startTemp + 0.5f;
            float startGravity = 12.8f;
            float gravity = startGravity - 0.2f;
            FermentationEntry entry = new FermentationEntry(temp, gravity);
            startTemp = temp;
            startGravity = gravity;
            client.submit(entry);
            latch.countDown();
        }, 0, 15, TimeUnit.MINUTES);

        latch.await();
    }
}