package com.aether.uility;

/**
 * This ENUM contains all the paths of APIs, which will be called from the application
 *
 */
public enum RestPaths {
    LOGIN("/rest/auth/angelbroking/user/v1/loginByPassword"),
    PROFILE("/rest/secure/angelbroking/user/v1/getProfile"),
    FUNDS_MARGIN("/rest/secure/angelbroking/user/v1/getRMS"),
    LOGOUT("/rest/secure/angelbroking/user/v1/logout"),

    CREATE_RULE("/rest/secure/angelbroking/gtt/v1/createRule"),
    MODIFY_RULE("/rest/secure/angelbroking/gtt/v1/modifyRule"),
    CANCEL_RULE("/rest/secure/angelbroking/gtt/v1/cancelRule"),
    GET_RULE("/rest/secure/angelbroking/gtt/v1/ruleDetails"),
    GET_RULE_LIST("/rest/secure/angelbroking/gtt/v1/ruleList"),

    CREATE_ORDER("/rest/secure/angelbroking/order/v1/placeOrder"),
    MODIFY_ORDER("/rest/secure/angelbroking/order/v1/modifyOrder"),
    CANCEL_ORDER("/rest/secure/angelbroking/order/v1/cancelOrder"),
    GET_TRADE_BOOK("/rest/secure/angelbroking/order/v1/getTradeBook"),

    BROKERAGE_CALCULATOR("/rest/secure/angelbroking/brokerage/v1/estimateCharges"),
    GET_ALL_HOLDING("/rest/secure/angelbroking/portfolio/v1/getAllHolding"),
    MARGIN_CALCULATOR("/rest/secure/angelbroking/margin/v1/batch"),

    MARKET_DATA("/rest/secure/angelbroking/market/v1/quote"),

    OPTION_GREEK("/secure/angelbroking/marketData/v1/optionGreek"),
    PCR_DATA("/rest/secure/angelbroking/marketData/v1/putCallRatio"),
    OI_BUILDUP("/rest/secure/angelbroking/marketData/v1/OIBuildup"),
    OI_DATA("/rest/secure/angelbroking/historical/v1/getOIData"),
    CANDLE_DATA("/rest/secure/angelbroking/historical/v1/getCandleData");

    private String path;

    RestPaths(String value) {
        this.path = value;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String value) {
        this.path = value;
    }
}
