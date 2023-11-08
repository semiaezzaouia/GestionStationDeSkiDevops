package tn.esprit.spring;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.entities.Subscription;
import tn.esprit.spring.entities.TypeSubscription;
import tn.esprit.spring.repositories.ISkierRepository;
import tn.esprit.spring.repositories.ISubscriptionRepository;
import tn.esprit.spring.services.SubscriptionServicesImpl;

import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SubscriptionTest {

    @Mock
    private ISubscriptionRepository subscriptionRepository;

    @Mock
    private ISkierRepository skierRepository;

    private SubscriptionServicesImpl subscriptionServices;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        subscriptionServices = new SubscriptionServicesImpl(subscriptionRepository, skierRepository);
    }

    @Test
    public void testAddSubscription() {
        Subscription subscription = new Subscription();
        subscription.setTypeSub(TypeSubscription.ANNUAL);
        subscription.setStartDate(LocalDate.now());

        when(subscriptionRepository.save(any(Subscription.class))).thenReturn(subscription);

        Subscription savedSubscription = subscriptionServices.addSubscription(subscription);

        assertEquals(subscription, savedSubscription);
        verify(subscriptionRepository, times(1)).save(subscription);
    }

    @Test
    public void testGetSubscriptionByType() {
        TypeSubscription type = TypeSubscription.MONTHLY;
        List<Subscription> subscriptions = new ArrayList<>();
        subscriptions.add(new Subscription());
        subscriptions.add(new Subscription());

        when(subscriptionRepository.findByTypeSubOrderByStartDateAsc(type)).thenReturn(new HashSet<>(subscriptions));

        Set<Subscription> result = subscriptionServices.getSubscriptionByType(type);

        assertEquals(subscriptions.size(), result.size());
        verify(subscriptionRepository, times(1)).findByTypeSubOrderByStartDateAsc(type);
    }
}
