package com.nightcat.event;


/**
 * Created by finderlo on 15/04/2017.
 * 事件，系统内所有事件都需要在此枚举中
 *
 * @author Aollio
 */
public enum Event {

    ComplaintResultEvent,

    UserUpgradeSuccessEvent,
    UserUpgradeFailEvent,

    OrderPublishedEvent,
    OrderAcceptedEvent,
    OrderCompleteSuccessEvent,
    OrderReplacementCancelEvent,
    OrderOutExpireEvent,
    OrderCommentSuccessEvent,

    UserRegisterEvent,
    UserAutoUpgradeSuccessEvent,

    FundsDepositEvent,
    FundsFetchEvent,
    FundsWithDrawEvent, Manual_NewUpgradeEvent, Manual_NewComplaint;

    Class contextType;

    Event() {
    }

    Event(Class context) {
        this.contextType = context;
    }

    public Class getContextType() {
        return contextType;
    }

}
