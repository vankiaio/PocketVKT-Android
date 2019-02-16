package com.oraclechain.pocketvkt.blockchain;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.model.Response;
import com.oraclechain.pocketvkt.base.BaseUrl;
import com.oraclechain.pocketvkt.base.Constants;
import com.oraclechain.pocketvkt.bean.ResponseBean;
import com.oraclechain.pocketvkt.blockchain.api.VktChainInfo;
import com.oraclechain.pocketvkt.blockchain.bean.GetRequiredKeys;
import com.oraclechain.pocketvkt.blockchain.bean.JsonToBeanResultBean;
import com.oraclechain.pocketvkt.blockchain.bean.JsonToBinRequest;
import com.oraclechain.pocketvkt.blockchain.bean.PushSuccessBean;
import com.oraclechain.pocketvkt.blockchain.bean.RequreKeyResult;
import com.oraclechain.pocketvkt.blockchain.chain.Action;
import com.oraclechain.pocketvkt.blockchain.chain.PackedTransaction;
import com.oraclechain.pocketvkt.blockchain.chain.SignedTransaction;
import com.oraclechain.pocketvkt.blockchain.cypto.ec.VktPrivateKey;
import com.oraclechain.pocketvkt.blockchain.types.TypeChainId;
import com.oraclechain.pocketvkt.blockchain.util.GsonVktTypeAdapterFactory;
import com.oraclechain.pocketvkt.net.HttpUtils;
import com.oraclechain.pocketvkt.net.callbck.JsonCallback;
import com.oraclechain.pocketvkt.utils.JsonUtil;
import com.oraclechain.pocketvkt.utils.PublicAndPrivateKeyUtils;
import com.oraclechain.pocketvkt.utils.ShowDialog;
import com.oraclechain.pocketvkt.utils.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by pocketVkt on 2018/5/11.
 * 币答测试管理
 */

public class AskansDatamanger {
    public  static String OCTCONTRACT =  Constants.OCTCONTRACT;//erctoken
    public  static String OCTASKANSCONTRACT = Constants.OCTASKANSCONTRACT;//erctoken
    public  static String ACTIONAPPROVE =  Constants.ACTIONAPPROVE;//授权可转走（押币）
    public  static String ACTIONASK =  Constants.ACTIONASK;//押币后提问
    public  static String ACTIONANSWER =  Constants.ACTIONANSWER;//押币后回答问题
    public  static String PERMISSONION =  Constants.PERMISSONION;

    Callback mCallback;
    Context mContext;
    VktChainInfo mChainInfoBean = new VktChainInfo();
    JsonToBeanResultBean mJsonToBeanResultBean = new JsonToBeanResultBean();
    String[] permissions;
    SignedTransaction txnBeforeSign;
    Gson mGson = new GsonBuilder()
            .registerTypeAdapterFactory(new GsonVktTypeAdapterFactory())
            .excludeFieldsWithoutExposeAnnotation().create();

    String contract, action, message, userpassword;

    public AskansDatamanger(Context context, String userpassword, Callback callback) {
        mContext = context;
        mCallback = callback;
        this.userpassword = userpassword;
    }

    public void pushAction(String contract, String action, String message, String permissionAccount) {
        this.message = message;
        this.contract = contract;
        this.action = action;
        permissions = new String[]{permissionAccount + "@" + PERMISSONION};
        getChainInfo();
    }

    public void getChainInfo() {
        HttpUtils.getRequets(BaseUrl.HTTP_get_chain_info, this, new HashMap<String, String>(), new JsonCallback<ResponseBean>() {
            @Override
            public void onSuccess(Response<ResponseBean> response) {
                if (response.body().code == 0) {
                    mChainInfoBean = (VktChainInfo) JsonUtil.parseStringToBean(mGson.toJson(response.body().data), VktChainInfo.class);
                    getabi_json_to_bin();
                } else {
                    if (ShowDialog.dialog != null) {
                        ShowDialog.dissmiss();
                    }
                    ToastUtils.showLongToast(response.body().message);
                }
            }
        });
    }

    public void getabi_json_to_bin() {
        JsonToBinRequest jsonToBinRequest = new JsonToBinRequest(contract, action, message.replaceAll("\\r|\\n", ""));
        HttpUtils.postRequest(BaseUrl.HTTP_get_abi_json_to_bin, this, mGson.toJson(jsonToBinRequest), new JsonCallback<ResponseBean>() {
            @Override
            public void onSuccess(Response<ResponseBean> response) {
                if (response.body().code == 0) {
                    mJsonToBeanResultBean = (JsonToBeanResultBean) JsonUtil.parseStringToBean(mGson.toJson(response.body().data), JsonToBeanResultBean.class);
                    txnBeforeSign = createTransaction(contract, action, mJsonToBeanResultBean.getBinargs(), permissions, mChainInfoBean);
                    //扫描钱包列出所有可用账号的公钥
                    List<String> pubKey = PublicAndPrivateKeyUtils.getActivePublicKey();

                    getRequreKey(new GetRequiredKeys(txnBeforeSign, pubKey));
                } else {
                    if (ShowDialog.dialog != null) {
                        ShowDialog.dissmiss();
                    }
                    ToastUtils.showLongToast(response.body().message);
                }
            }
        });
    }

    private SignedTransaction createTransaction(String contract, String actionName, String dataAsHex,
                                                String[] permissions, VktChainInfo chainInfo) {

        Action action = new Action(contract, actionName);
        action.setAuthorization(permissions);
        action.setData(dataAsHex);


        SignedTransaction txn = new SignedTransaction();
        txn.addAction(action);
        txn.putSignatures(new ArrayList<String>());


        if (null != chainInfo) {
            txn.setReferenceBlock(chainInfo.getHeadBlockId());
            txn.setExpiration(chainInfo.getTimeAfterHeadBlockTime(30000));
        }
        return txn;
    }

    public void getRequreKey(GetRequiredKeys getRequiredKeys) {
        HttpUtils.postRequest(BaseUrl.HTTP_get_required_keys, this, mGson.toJson(getRequiredKeys), new JsonCallback<ResponseBean>() {
            @Override
            public void onSuccess(Response<ResponseBean> response) {
                if (response.body().code == 0) {
                    RequreKeyResult requreKeyResult = (RequreKeyResult) JsonUtil.parseStringToBean(mGson.toJson(response.body().data), RequreKeyResult.class);
                    VktPrivateKey vktPrivateKey = new VktPrivateKey(PublicAndPrivateKeyUtils.getPrivateKey(requreKeyResult.getRequired_keys().get(0), userpassword));
                    txnBeforeSign.sign(vktPrivateKey, new TypeChainId(mChainInfoBean.getChain_id()));
                    pushTransactionRetJson(new PackedTransaction(txnBeforeSign));
                } else {
                    if (ShowDialog.dialog != null) {
                        ShowDialog.dissmiss();
                    }
                    ToastUtils.showLongToast(response.body().message);
                }
            }
        });
    }



    public void pushTransactionRetJson(PackedTransaction body) {
        HttpUtils.postRequest(BaseUrl.HTTP_push_transaction, this, mGson.toJson(body), new JsonCallback<ResponseBean>() {
            @Override
            public void onSuccess(final Response<ResponseBean> response) {
                if (ShowDialog.dialog != null) {
                    ShowDialog.dissmiss();
                }
                if (response.body().code == 0) {
                    PushSuccessBean.DataBeanX dataBeanX = (PushSuccessBean.DataBeanX) JsonUtil.parseStringToBean(mGson.toJson(response.body().data), PushSuccessBean.DataBeanX.class);
                    mCallback.isApprove(action);
                } else {
                    ToastUtils.showLongToast(response.body().message);
                }
            }
        });
    }

    public interface Callback {
        void isApprove(String action);
    }
}
