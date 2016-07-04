package com.xyz.myotee.data;

import android.content.Context;

import com.xyz.myotee.R;

import java.util.ArrayList;

/**
 * Created by xyz on 16/6/29.
 */
public class FeatureIconLoadEngine {

    public static final int SELECTED_RES[] = new int[]{
            R.mipmap.tab_1_down, R.mipmap.tab_3_down, R.mipmap.tab_5_down, R.mipmap.tab_6_down,
            R.mipmap.tab_7_down
    };

    public static final int UNSELECTED_RES[] = new int[]{
            R.mipmap.tab_1, R.mipmap.tab_3, R.mipmap.tab_5, R.mipmap.tab_6, R.mipmap.tab_7
    };

    public static final int TYPE_LENGTH = SELECTED_RES.length;

    private Context mContext;
    private ArrayList<ArrayList<Integer>> featureIcons;
    private boolean isInit;
    // 注意face、eyebrow的命名方式不太一样
    private final String TYPES[] = new String[] {
          "hair", "face", "eyebrow", "eye", "mouth"
    };
    private final int TYPES_NUM[] = new int[] {
             75, 20,1, 53, 1
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
            int resId;
            for (int j = 0; j < TYPES_NUM[i]; j++) {
                if (TYPES[i].equals("face")) {  // face的ID跟别人不一样，有点日狗
                    int index = 20000 + j;
                    resId = mContext.getResources().getIdentifier(TYPES[i]+"_"+index, "mipmap", mContext.getPackageName());
                } else if (TYPES[i].equals("eyebrow")) {   // eyebrow的命名是奇葩
                    int index;
                    for (index = 1; index <= 31; index++) {
                        resId = mContext.getResources().getIdentifier(TYPES[i]+"_"+index, "mipmap", mContext.getPackageName());
                        if (resId != 0) {
                            featureIcon.add(resId);
                        }
                    }
                    for (index = 20000; index <= 20014; index++) {
                        resId = mContext.getResources().getIdentifier(TYPES[i]+"_"+index, "mipmap", mContext.getPackageName());
                        if (resId != 0) {
                            featureIcon.add(resId);
                        }
                    }
                    break;
                } else if (TYPES[i].equals("mouth")) {
                    int index;
                    for (index = 1; index <= 102; index++) {
                        resId = mContext.getResources().getIdentifier(TYPES[i]+"_"+index, "mipmap", mContext.getPackageName());
                        if (resId != 0) {
                            featureIcon.add(resId);
                        }
                    }
                    for (index = 20000; index <= 20037; index++) {
                        resId = mContext.getResources().getIdentifier(TYPES[i]+"_"+index, "mipmap", mContext.getPackageName());
                        if (resId != 0) {
                            featureIcon.add(resId);
                        }
                    }
                    break;
                } else {
                    resId = mContext.getResources().getIdentifier(TYPES[i]+"_"+j, "mipmap", mContext.getPackageName());
                }
                if (resId != 0) {
                    featureIcon.add(resId);
                }
            }
            featureIcons.add(featureIcon);

        }
        isInit = true;
    }

    /**
     * @param resType 图片资源类型，要与featureIcons对应
     *        position 图片资源具体位置
     * **/
    public int getResId(int resType, int position) {
        switch (resType) {
            case 0:
                return position;
            case 1:
                return 20000+position;
            case 2:
                if (position >= 0 && position <= 30) {
                    return position + 1;
                } else {
                    return 20000 + position - 31;
                }
            case 3:
                return position;
            case 4:
                if (position >= 0 && position <= 101) {
                    return  position + 1;
                } else {
                    return 20000 + position - 102;
                }
            default:
                return 0;
        }
    }

    public String getUrl(int resType, int position) {
        switch (resType) {
            case 0:
                return "javascript:hairChange(" + position + ")";
            case 1:
                return "javascript:faceChange(" + position + ")";
            case 2:
                return "javascript:eyebrowChange(" + position + ")";
            case 3:
                return "javascript:eyeChange(" + position + ")";
            case 4:
                return "javascript:mouthChange(" + position + ")";
            default:
                return "";

        }
    }

    public ArrayList<ArrayList<Integer>> getFeatureIcons() {
        return featureIcons;
    }

    public boolean isInitiated() {
        return isInit;
    }
}
