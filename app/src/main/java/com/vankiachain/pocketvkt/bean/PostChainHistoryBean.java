package com.vankiachain.pocketvkt.bean;

import java.util.List;

/**
 * Created by pocketVkt on 2018/2/1.
 * 获取json序列化 发送的json实体类
 */
public class PostChainHistoryBean {


    /**
     * symbols : [{"symbolName":"VKT","contractName":"vktio.token"},{"symbolName":"AAA","contractName":"helloworldgo"}]
     * from : vankiachain4
     * to : vankiachain4
     * page : 0
     * pageSize : 10
     */

    private String from;
    private String to;
    private int page;
    private int pageSize;
    private List<SymbolsBean> symbols;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<SymbolsBean> getSymbols() {
        return symbols;
    }

    public void setSymbols(List<SymbolsBean> symbols) {
        this.symbols = symbols;
    }

    public static class SymbolsBean {
        /**
         * symbolName : VKT
         * contractName : vktio.token
         */

        private String symbolName;
        private String contractName;

        public String getSymbolName() {
            return symbolName;
        }

        public void setSymbolName(String symbolName) {
            this.symbolName = symbolName;
        }

        public String getContractName() {
            return contractName;
        }

        public void setContractName(String contractName) {
            this.contractName = contractName;
        }
    }
}
