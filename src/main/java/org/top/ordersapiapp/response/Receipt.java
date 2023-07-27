package org.top.ordersapiapp.response;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    private Integer id;
    private String name;
    private String article;
    private double price;
    private Integer count;
    private double cost;
}
