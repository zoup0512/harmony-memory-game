package com.google.android.gms.plus;

import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.plus.model.people.PersonBuffer;

public interface People$LoadPeopleResult extends Releasable, Result {
    String getNextPageToken();

    PersonBuffer getPersonBuffer();
}
