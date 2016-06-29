package com.xyz.myotee.data;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by xyz on 16/6/29.
 */
public class FeatureIconLoadEngine {

    private Context mContext;
    private ArrayList<ArrayList<Integer>> featureIcons;
    private boolean isInit;
    private final String TYPES[] = new String[] {
          "hair", "face",  "eye"
    };
    private final int TYPES_NUM[] = new int[] {
             75, 20, 53
    };

    private static FeatureIconLoadEngine instance = null;

    private FeatureIconLoadEngine(Context context) {
        this.mContext = context;
        this.featureIcons = new ArrayList<>();
        this.isInit = false;
    }

    public static FeatureIconLoadEngine getInstance(Context mContext) {
        if (instance != null) {
            return instance;
        }
        synchronized (FeatureIconLoadEngine.class) {
            if (instance == null) {
                instance = new FeatureIconLoadEngine(mContext);
            }
            return instance;
        }
    }

    public void initFeatureIcons() {
        for (int i = 0; i < TYPES.length; i++) {
            ArrayList<Integer> featureIcon = new ArrayList<>();
            for (int j = 0; j < TYPES_NUM[i]; j++) {
                int resId = mContext.getResources().getIdentifier(TYPES[i]+"_"+j, "mipmap", mContext.getPackageName());
                if (resId != 0) {
                    featureIcon.add(resId);
                }
            }
            featureIcons.add(featureIcon);

        }
        isInit = true;
    }

    public ArrayList<ArrayList<Integer>> getFeatureIcons() {
        return featureIcons;
    }

    public boolean isInitiated() {
        return isInit;
    }
}
