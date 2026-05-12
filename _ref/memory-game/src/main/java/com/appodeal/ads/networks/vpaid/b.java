package com.appodeal.ads.networks.vpaid;

import com.appodeal.ads.Appodeal;
import org.nexage.sourcekit.util.DefaultMediaPicker;
import org.nexage.sourcekit.vast.model.VASTModel;
import org.nexage.sourcekit.vast.processor.VASTProcessor;

public class b {
    private String a;

    public b(String str) {
        this.a = str;
    }

    public VASTModel a() {
        if (this.a == null) {
            return null;
        }
        try {
            VASTProcessor vASTProcessor = new VASTProcessor(new DefaultMediaPicker(Appodeal.b));
            if (vASTProcessor.process(this.a) != 0) {
                return null;
            }
            VASTModel model = vASTProcessor.getModel();
            if (model.getPickedMediaFileType().equals(WebRequest.CONTENT_TYPE_JAVASCRIPT)) {
                return model;
            }
            model.sendError(403);
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
