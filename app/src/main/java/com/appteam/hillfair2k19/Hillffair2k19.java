package com.appteam.hillfair2k19;

import android.app.Application;

import com.cloudinary.android.MediaManager;

import java.util.HashMap;
import java.util.Map;

public class Hillffair2k19 extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Map config = new HashMap();
        config.put("cloud_name", "dpxfdn3d8");
        config.put("api_key", "172568498646598");
        config.put("api_secret", "NNa_bFKyVxW0AB30wL8HVoFxeSs");
        MediaManager.init(this, config);
    }
}
