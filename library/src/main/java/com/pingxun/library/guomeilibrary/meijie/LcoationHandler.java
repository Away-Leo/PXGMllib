package com.pingxun.library.guomeilibrary.meijie;


import com.pingxun.library.guomeilibrary.bridge.BridgeInterface;
import com.pingxun.library.guomeilibrary.bridge.CallBackFunction;
import com.pingxun.library.guomeilibrary.bridge.DefaultHandler;

/**
 * Created by hbl on 2017/5/11.
 */

public class LcoationHandler extends DefaultHandler {
    @Override
    public void handler(String data, CallBackFunction function, BridgeInterface bridgeInterface) {

        function.onCallBack("location");
    }
}
