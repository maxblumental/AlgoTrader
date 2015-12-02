package ru.sbt.exchange.client.trading;

import ru.sbt.exchange.client.Broker;
import ru.sbt.exchange.domain.Order;

/**
 * Created by Maxim on 11/23/2015.
 */
public class ZeroCouponBondTrader extends Trader {
    @Override
    protected double gain(Order order, Broker broker) {
        return 100 * order.getQuantity();
    }

    @Override
    protected void handleBuyOrder(Order order, Broker broker) {
        if (order.getPrice() > 100)
            broker.addOrder(order.opposite());
    }
}
