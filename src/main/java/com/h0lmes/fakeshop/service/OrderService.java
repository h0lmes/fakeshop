package com.h0lmes.fakeshop.service;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import com.amazonaws.xray.spring.aop.XRayEnabled;
import com.h0lmes.fakeshop.model.Cart;
import com.h0lmes.fakeshop.model.Product;
import com.h0lmes.fakeshop.model.dto.CartDto;
import com.h0lmes.fakeshop.model.dto.ProductDto;
import com.h0lmes.fakeshop.repository.CartRepository;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@XRayEnabled
public class OrderService {

    private final AWSCredentialsProvider awsCredentialsProvider;
    private final CartRepository cartRepository;
    private AmazonSQS sqs;

    @Value("${application.sqs.url}")
    private String sqsUrl;


    public OrderService(CartRepository cartRepository,
                        @Value("${application.aws.access-key}") String awsAccessKey,
                        @Value("${application.aws.secret-key}") String awsSecretKey
    ) {
        this.cartRepository = cartRepository;
        awsCredentialsProvider = new AWSStaticCredentialsProvider(new BasicAWSCredentials(awsAccessKey, awsSecretKey));
        sqs = AmazonSQSClientBuilder.standard().withCredentials(awsCredentialsProvider).withRegion(Regions.EU_CENTRAL_1).build();
    }

    public void checkout(Long cartId) {
        Optional<Cart> byId = cartRepository.findById(cartId);
        byId.ifPresent(cart -> {
            CartDto cartDto = new CartDto();
            cartDto.setId(cart.getId());
            cartDto.setProducts(cart.getProducts()
                .stream()
                .map(OrderService::mapProductToProductDto)
                .collect(Collectors.toList()));
            System.out.println(cartDto);
            sendMessageToSqs(cartDto);
        });
    }

    private static ProductDto mapProductToProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());
        return productDto;
    }

    private void sendMessageToSqs(CartDto cartDto) {
        SendMessageRequest sendMsgRequest = new SendMessageRequest()
            .withQueueUrl(sqsUrl)
            .withMessageBody(cartDto.toString())
            .withDelaySeconds(5);
        sqs.sendMessage(sendMsgRequest);
    }
}
