package com.pingxun.library.guomeilibrary.bridge;

import android.content.Intent;

public interface BridgeHandler {
	
	void handler(String data, CallBackFunction function, BridgeInterface bridgeInterface);
	void onActivityResult(int requestCode, int resultCode, Intent data);
}
