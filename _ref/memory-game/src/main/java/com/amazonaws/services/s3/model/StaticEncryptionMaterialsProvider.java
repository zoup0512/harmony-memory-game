package com.amazonaws.services.s3.model;

import java.util.Map;

public class StaticEncryptionMaterialsProvider implements EncryptionMaterialsProvider {
    private final EncryptionMaterials materials;

    public StaticEncryptionMaterialsProvider(EncryptionMaterials materials) {
        this.materials = materials;
    }

    public EncryptionMaterials getEncryptionMaterials() {
        return this.materials;
    }

    public void refresh() {
    }

    public EncryptionMaterials getEncryptionMaterials(Map<String, String> materialDescIn) {
        boolean noMaterialDesc = false;
        Map<String, String> materialDesc = this.materials.getMaterialsDescription();
        if (materialDescIn != null && materialDescIn.equals(materialDesc)) {
            return this.materials;
        }
        boolean noMaterialDescIn;
        EncryptionMaterialsAccessor accessor = this.materials.getAccessor();
        if (accessor != null) {
            EncryptionMaterials accessorMaterials = accessor.getEncryptionMaterials(materialDescIn);
            if (accessorMaterials != null) {
                return accessorMaterials;
            }
        }
        if (materialDescIn == null || materialDescIn.size() == 0) {
            noMaterialDescIn = true;
        } else {
            noMaterialDescIn = false;
        }
        if (materialDesc == null || materialDesc.size() == 0) {
            noMaterialDesc = true;
        }
        EncryptionMaterials encryptionMaterials = (noMaterialDescIn && noMaterialDesc) ? this.materials : null;
        return encryptionMaterials;
    }
}
