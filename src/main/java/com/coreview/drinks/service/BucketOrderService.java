package com.coreview.drinks.service;

import com.coreview.drinks.data.bucket.BucketOrder;
import com.coreview.drinks.data.drink.Drink;
import com.coreview.drinks.repository.BucketOrderRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Date;
import java.util.Optional;

@Service
public class BucketOrderService {
    final BucketOrderRepository bucketOrderRepository;

    public BucketOrderService(BucketOrderRepository bucketOrderRepository) {
        this.bucketOrderRepository = bucketOrderRepository;
    }

    public BucketOrder save(BucketOrder bucketOrder) {
        return this.bucketOrderRepository.save(bucketOrder);
    }

    public BucketOrder createBucketOrder(Drink drink) {
        BucketOrder bucketOrder = new BucketOrder();
        bucketOrder.setDrink(drink);
        bucketOrder.setPrice(drink.getPrice());
        bucketOrder.setOrderDate(new Date());
        bucketOrder.setQuantity(1);
        return this.save(bucketOrder);
    }

    public void updateBucketOrderQuantity(BucketOrder bucketOrder) {
        Optional<BucketOrder> bucketOrderToUpdate = this.bucketOrderRepository.findById(bucketOrder.getId());
        if (bucketOrderToUpdate.isEmpty()) throw new EntityNotFoundException("Bucket order not found!");
        bucketOrderToUpdate.get().setQuantity(bucketOrder.getQuantity());
        this.bucketOrderRepository.save(bucketOrderToUpdate.get());
    }

}
