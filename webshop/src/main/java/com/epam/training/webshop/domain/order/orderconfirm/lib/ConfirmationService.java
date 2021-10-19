package com.epam.training.webshop.domain.order.orderconfirm.lib;

import com.epam.training.webshop.domain.order.model.Product;
import java.util.List;

public interface ConfirmationService {

    void sendConfirmationMessageAbout(List<Product> products);
}