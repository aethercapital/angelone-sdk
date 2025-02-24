package com.aether.contracts;

public interface OrderAPI {

    void createRule();

    void modifyRule();

    void cancelRule();

    void createOrder();

    void modifyOrder();

    void cancelOrder();

    void ruleDetails();

    void ruleList();

    void getOrderBook();

    void getTradeBook();
}
