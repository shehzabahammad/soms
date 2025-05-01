package com.soms.user_service.util;

import com.soms.user_service.entity.CommonEntityField;

import java.util.Date;

public class RepositoryUtil {

    public static void addCommonFields(CommonEntityField commonEntityField){
        commonEntityField.setCreated(new Date());
        commonEntityField.setModified(new Date());
        commonEntityField.setDeleted(false);
    }
}
