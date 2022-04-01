package io.github.nguba.lunanera.infrastructure.brewersfriend;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.client.RestTemplate;
import org.testcontainers.shaded.com.google.common.util.concurrent.AtomicDouble;

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
    void findAllRecipes() {
        Collection<RecipeResponse> recipes = client.findAllRecipes();
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

        assertThat(recipes).asList().contains(new BrewSessionResponse(424814, 1263911));
    }

    @Test
    @Disabled
    void submitReading() throws InterruptedException {

        CountDownLatch latch = new CountDownLatch(20);

        AtomicDouble temp = new AtomicDouble(6.2);
        AtomicDouble gravity = new AtomicDouble(13.4f);

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            FermentationEntry entry = new FermentationEntry(temp.floatValue(), gravity.floatValue());
            temp.addAndGet(0.5);
            gravity.set(gravity.doubleValue() - 0.3);
            client.submit(entry);
            latch.countDown();
        }, 0, 15, TimeUnit.MINUTES);

        latch.await();
    }
}