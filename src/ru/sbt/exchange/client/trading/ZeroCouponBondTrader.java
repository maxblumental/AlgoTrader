package ru.sbt.exchange.client.trading;

import ru.sbt.exchange.client.Broker;
import ru.sbt.exchange.domain.Order;
import ru.sbt.exchange.domain.PeriodInfo;
import ru.sbt.exchange.domain.Portfolio;

/**
 * Created by Maxim on 11/23/2015.
 */
public class ZeroCouponBondTrader extends Trader {
    @Override
    protected double gain(Order order, Broker broker) {
        return 100 * order.getQuantity();
    }
}
