package ru.sbt.exchange.client.trading;

import ru.sbt.exchange.client.Broker;
import ru.sbt.exchange.domain.Order;
import ru.sbt.exchange.domain.PeriodInfo;

/**
 * Created by Maxim on 11/23/2015.
 */
public class FixedCouponBondTrader extends Trader {

    @Override
    protected double gain(Order order, Broker broker) {
        PeriodInfo periodInfo = broker.getPeriodInfo();
        int numOfPeriods = periodInfo.getEndPeriodNumber() - periodInfo.getCurrentPeriodNumber() + 1;
        return (10 * numOfPeriods + 100) * order.getQuantity();
    }
}
