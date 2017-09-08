package com.pingxun.library.guomeilibrary.bridge;

import android.app.Activity;
import android.content.Intent;

/**
 * Created by hbl on 2017/5/11.
 */

public interface BridgeInterface {

    void startActivityForResult(BridgeHandler handler, Intent intent, int requestCode);

    Activity getActivity();

}
