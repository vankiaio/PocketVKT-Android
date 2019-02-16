package com.oraclechain.pocketvkt.view.dialog.walletcodedialog;

import android.graphics.Bitmap;

/**
 * Created by pocketVkt on 2017/12/12.
 */

public interface WalletCodeCallBack {
    void goWeixinFriend(Bitmap bitmap);

    void goWeixinCircle(Bitmap bitmap);

    void goQQFriend(Bitmap bitmap);

    void goQzone(Bitmap bitmap);
}
