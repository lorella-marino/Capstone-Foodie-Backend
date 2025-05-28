package it.epicode.Foodie.pagamenti;
import com.stripe.Stripe;
import com.stripe.model.PaymentIntent;
import com.stripe.param.PaymentIntentCreateParams;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {
    @Value("${stripe.secret.key}")
    private String stripeSecretKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = stripeSecretKey;
    }

    public Map<String, Object> createPaymentIntent(Long amount) throws Exception {
        PaymentIntentCreateParams params = PaymentIntentCreateParams.builder()
                .setAmount(amount) // es. 1000 = €10.00
                .setCurrency("eur")
                .build();

        PaymentIntent intent = PaymentIntent.create(params);
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("clientSecret", intent.getClientSecret());
        return responseData;
    }
}
