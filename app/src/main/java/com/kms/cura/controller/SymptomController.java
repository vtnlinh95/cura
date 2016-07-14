package com.kms.cura.controller;

import com.kms.cura.entity.ConditionEntity;
import com.kms.cura.entity.SymptomEntity;
import com.kms.cura.model.SymptomModel;
import java.util.List;

/**
 * Created by toanbnguyen on 6/28/2016.
 */
public class SymptomController {
    public static void initData() throws Exception {
        SymptomModel.getInstance().initData();
    }

    public static List<SymptomEntity> getAllSymptom() {
        return SymptomModel.getInstance().getAllSymptom();
    }

    public static List<SymptomEntity> getAssociatedSymptom(ConditionEntity entity) throws Exception {
        return SymptomModel.getInstance().getAssociatedSymtom(entity);
    }
}
