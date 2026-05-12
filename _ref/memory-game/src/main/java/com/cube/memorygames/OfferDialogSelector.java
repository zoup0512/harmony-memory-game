package com.cube.memorygames;

import android.app.Activity;
import android.support.annotation.NonNull;
import com.cube.memorygames.OfferDialog.PurchaseDialogType;
import com.cube.memorygames.SharingDialog.IabStatus;
import com.cube.memorygames.billing.IabHelper;

public class OfferDialogSelector {
    private static final String PREF_TYPE = "prefDialogType";
    private static final int TYPE_NEW = 2;
    private static final int TYPE_NONE = 0;
    private static final int TYPE_OLD = 1;
    private static Integer type;

    public static void showDialog(Activity activity, IabHelper iabHelper, IabStatus iabStatus, @NonNull PurchaseDialogType purchaseDialogType) {
        if (purchaseDialogType.equals(PurchaseDialogType.SECRET_GAME)) {
            new OfferDialog(activity).show();
        } else {
            new OfferDialogNew(activity, iabHelper, iabStatus, purchaseDialogType).show();
        }
    }
}
