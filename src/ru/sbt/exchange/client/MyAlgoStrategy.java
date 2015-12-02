package ru.sbt.exchange.client;

import ru.sbt.exchange.client.trading.Trader;
import ru.sbt.exchange.domain.ExchangeEvent;
import ru.sbt.exchange.domain.Order;

/**
 * Created by Maxim on 11/20/2015.
 */
public class MyAlgoStrategy implements AlgoStrategy {

    @Override
    public void onEvent(ExchangeEvent event, Broker broker) {
        Order order = event.getOrder();
        if (event.getExchangeEventType() == ru.sbt.exchange.domain.ExchangeEventType.ORDER_NEW) {
            Trader trader = Trader.getTrader(order.getInstrument());
            trader.trade(order, broker);
        }
    }

}
