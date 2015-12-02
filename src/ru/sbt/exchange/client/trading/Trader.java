package ru.sbt.exchange.client.trading;

import ru.sbt.exchange.client.Broker;
import ru.sbt.exchange.domain.Direction;
import ru.sbt.exchange.domain.Order;
import ru.sbt.exchange.domain.instrument.Instrument;
import ru.sbt.exchange.domain.instrument.Instruments;

/**
 * Created by Maxim on 11/23/2015.
 */
public abstract class Trader {

    private static ZeroCouponBondTrader zeroCouponBondTrader;
    private static FixedCouponBondTrader fixedCouponBondTrader;
    private static FloatingCouponBondTrader floatingCouponBondTrader;

    static {
        zeroCouponBondTrader = new ZeroCouponBondTrader();
        fixedCouponBondTrader = new FixedCouponBondTrader();
        floatingCouponBondTrader = new FloatingCouponBondTrader();
    }


    public void trade(Order order, Broker broker) {
        if (order.getDirection() == Direction.BUY)
            handleBuyOrder(order, broker);
        else
            handleSellOrder(order, broker);
    }

    protected void handleSellOrder(Order order, Broker broker) {

        int currentPeriodNumber = broker.getPeriodInfo().getCurrentPeriodNumber() - 1;

        if (order.getInstrument().equals(Instruments.zeroCouponBond())
                || currentPeriodNumber >= 4) {
            return;
        }

        double cost = order.getPrice() * order.getQuantity();

        if (broker.getMyPortfolio().getMoney() - cost < 1000 * currentPeriodNumber) {
            return;
        }

        if (cost < gain(order, broker)) {
            broker.addOrder(order.opposite());
        }
    }

    protected void handleBuyOrder(Order order, Broker broker) {
        if (gain(order, broker) < order.getPrice() * order.getQuantity()) {
            broker.addOrder(order.opposite());
        }
    }

    abstract protected double gain(Order order, Broker broker);

    /*
     * Factory for creating traders.
     */
    public static Trader getTrader(Instrument instrument) {
        Trader trader;

        if (instrument.equals(Instruments.zeroCouponBond())) {
            trader = zeroCouponBondTrader;
        } else if (instrument.equals(Instruments.fixedCouponBond())) {
            trader = fixedCouponBondTrader;
        } else {
            trader = floatingCouponBondTrader;
        }

        return trader;
    }
}
