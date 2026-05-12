package com.google.android.gms.plus.model.people;

import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzd;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.plus.internal.model.people.PersonEntity;
import com.google.android.gms.plus.internal.model.people.zzk;

public final class PersonBuffer extends AbstractDataBuffer<Person> {
    private final zzd<PersonEntity> asR;

    public PersonBuffer(DataHolder dataHolder) {
        super(dataHolder);
        if (dataHolder.zzarc() == null || !dataHolder.zzarc().getBoolean("com.google.android.gms.plus.IsSafeParcelable", false)) {
            this.asR = null;
        } else {
            this.asR = new zzd(dataHolder, PersonEntity.CREATOR);
        }
    }

    public Person get(int i) {
        return this.asR != null ? (Person) ((SafeParcelable) this.asR.get(i)) : new zzk(this.tu, i);
    }
}
