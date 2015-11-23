package ru.sbt.exchange.client;

import ru.sbt.exchange.client.trading.FixedCouponBondTrader;
import ru.sbt.exchange.client.trading.FloatingCouponBondTrader;
import ru.sbt.exchange.client.trading.Trader;
import ru.sbt.exchange.client.trading.ZeroCouponBondTrader;
import ru.sbt.exchange.domain.ExchangeEvent;
import ru.sbt.exchange.domain.Order;
import ru.sbt.exchange.domain.instrument.Instrument;
import ru.sbt.exchange.domain.instrument.Instruments;

/**
 * Created by Maxim on 11/20/2015.
 */
public class MyAlgoStrategy implements AlgoStrategy {

    @Override
    public void onEvent(ExchangeEvent event, Broker broker) {
        Order order = event.getOrder();
        switch (event.getExchangeEventType()) {
            case ORDER_NEW:
                processNewOrder(order, broker);
                break;
        }
    }

    private void processNewOrder(Order order, Broker broker) {
        Trader trader = Trader.getTrader(order.getInstrument());
        trader.trade(order, broker);
    }
}
