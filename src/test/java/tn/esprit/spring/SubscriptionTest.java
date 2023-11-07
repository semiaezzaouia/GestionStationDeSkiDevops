package tn.esprit.spring;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.ISubscriptionServices;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SubscriptionTest {

    @InjectMocks
    private ISubscriptionServices subscriptionServices;

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize annotated mocks
    }

    @Test
    public void testAddSubscription() {
        // Create a mock Subscription object
        Subscription mockSubscription = new Subscription();
        mockSubscription.setNumSub(1L);
        mockSubscription.setStartDate(LocalDate.now());
        mockSubscription.setEndDate(LocalDate.now().plusMonths(1));
        mockSubscription.setPrice(100.0F);
        mockSubscription.setTypeSub(TypeSubscription.MONTHLY);

        // Mock the behavior of the repository
        Mockito.when(subscriptionRepository.save(Mockito.any(Subscription.class)))
                .thenReturn(mockSubscription);

        // Call the service method
        Subscription addedSubscription = subscriptionServices.addSubscription(mockSubscription);

        // Verify the result
        assertEquals(1L, addedSubscription.getNumSub());
        assertEquals(mockSubscription.getStartDate(), addedSubscription.getStartDate());
        assertEquals(mockSubscription.getEndDate(), addedSubscription.getEndDate());
        assertEquals(mockSubscription.getPrice(), addedSubscription.getPrice());
        assertEquals(mockSubscription.getTypeSub(), addedSubscription.getTypeSub());
    }
}
