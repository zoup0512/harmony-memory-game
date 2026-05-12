package com.cmcm.picks.init;

import com.cmcm.picks.loader.Ad;
import java.util.List;

public interface ICallBack {
    void onLoadError();

    void onLoadSuccess(List<Ad> list);

    void onPreExecute();
}
