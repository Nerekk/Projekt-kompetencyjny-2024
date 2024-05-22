package com.pk2024.backend.settings;

import com.pk2024.backend.prediction_model.ModelType;

import java.util.HashMap;

public class Settings {

    public static final String RESOURCES_PATH = "src\\main\\resources\\";


    public static final String BASE_MAPPING = "/api/";
    public static final String AUTH_MAPPING = BASE_MAPPING + "auth";
    public static final String USERS_MAPPING = BASE_MAPPING + "users";
    public static final String LOGOUT_MAPPING = AUTH_MAPPING + "/logout";
    public static final String MODEL_MAPPING = BASE_MAPPING + "model";


    /* TABLES */
    public static final String USER_TABLE_NAME = "_user";


    /* MESSAGES */
    public static final String CHANGE_PASSWORD_WRONG_PASSWORD = "Wrong password";
    public static final String CHANGE_PASSWORD_NOT_THE_SAME_PASSWORDS = "Passwords are not the same";
    public static final String USER_NOT_FOUND = "User not found";


    /* PREDICTION MODEL */
    public static final String SMALL_MODEL_FILENAME = "reg_rf_4f.pmml";
    public static final String MEDIUM_MODEL_FILENAME = "reg_rf_7f.pmml";
    public static final String BIG_MODEL_FILENAME = "reg_rf_14f.pmml";

    public static final HashMap<ModelType, String> MODEL_FILENAMES_MAP;
    static {
        MODEL_FILENAMES_MAP = new HashMap<>();
        MODEL_FILENAMES_MAP.put(ModelType.SMALL, SMALL_MODEL_FILENAME);
        MODEL_FILENAMES_MAP.put(ModelType.MEDIUM, MEDIUM_MODEL_FILENAME);
        MODEL_FILENAMES_MAP.put(ModelType.BIG, BIG_MODEL_FILENAME);
    }

}
