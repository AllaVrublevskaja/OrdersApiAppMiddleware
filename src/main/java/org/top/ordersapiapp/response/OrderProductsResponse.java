package org.top.ordersapiapp.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderProductsResponse {
    private Integer orderId;
    private String description;
    private String clientName;
    private List<Products> products;
}
