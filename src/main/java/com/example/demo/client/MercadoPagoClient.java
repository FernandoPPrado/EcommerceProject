package com.example.demo.client;

import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferencePayerRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.exceptions.MPApiException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.preference.Preference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class MercadoPagoClient {

    public MercadoPagoClient(@Value("${mercado.access.token}") String token) {
        MercadoPagoConfig.setAccessToken(token);
    }

    public String CreateCheckoutLink(String productName, int quantity, BigDecimal price, String userEmail, String username, Integer produtctId) throws MPException, MPApiException {

        PreferenceClient preferenceClient = new PreferenceClient();

        //CRIANDO O ITEM

        PreferenceItemRequest itemRequest = PreferenceItemRequest.builder()
                .title(productName).quantity(quantity).unitPrice(price).build();

        //CRIANDO O PAGADOR

        PreferencePayerRequest payerRequest = PreferencePayerRequest.builder().surname(username).email(userEmail).build();

        //MONTANDO O REQUEST
        PreferenceRequest request = PreferenceRequest.builder().items(List.of(itemRequest)).payer(payerRequest).externalReference(produtctId.toString()).notificationUrl("https://little-bottles-do.loca.lt/payments/webhook") // seu ID interno
                .build();

        //MONTANDO O OBJETO DE VENDA
        Preference preference = preferenceClient.create(request);

        return preference.getInitPoint();

    }


}
