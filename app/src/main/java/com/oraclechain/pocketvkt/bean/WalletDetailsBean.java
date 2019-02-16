package com.oraclechain.pocketvkt.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocketVkt on 2018/1/25.
 */

public class WalletDetailsBean {

    /**
     * code : 0
     * message : ok
     * data : [{"vktAccountName":"1589","isMainAccount":1}]
     */

    private String code;
    private String message;
    private List<DataBean> data;

    public String getCode() {
        return code == null ? "" : code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message == null ? "" : message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataBean> getData() {
        if (data == null) {
            return new ArrayList<>();
        }
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * vktAccountName : 1589
         * isMainAccount : 1
         */

        private String vktAccountName;
        private int isMainAccount;

        public String getVktAccountName() {
            return vktAccountName == null ? "" : vktAccountName;
        }

        public void setVktAccountName(String vktAccountName) {
            this.vktAccountName = vktAccountName;
        }

        public int getIsMainAccount() {
            return isMainAccount;
        }

        public void setIsMainAccount(int isMainAccount) {
            this.isMainAccount = isMainAccount;
        }
    }
}
