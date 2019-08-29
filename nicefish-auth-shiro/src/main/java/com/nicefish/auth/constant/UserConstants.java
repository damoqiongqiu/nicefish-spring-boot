package com.nicefish.auth.constant;

/**
 *
 * @author 大漠穷秋
 */
public interface UserConstants {
     String fish_auth_user = "fish_auth_user";
     String NORMAL = "0";
     String EXCEPTION = "1";
     String USER_BLOCKED = "1";
     String ROLE_BLOCKED = "1";
     String DEPT_NORMAL = "0";
     String DICT_NORMAL = "0";
     String YES = "Y";
     int USERNAME_MIN_LENGTH = 2;
     int USERNAME_MAX_LENGTH = 20;
     String USER_NAME_UNIQUE = "0";
     String USER_NAME_NOT_UNIQUE = "1";
     String USER_PHONE_UNIQUE = "0";
     String USER_PHONE_NOT_UNIQUE = "1";
     String USER_EMAIL_UNIQUE = "0";
     String USER_EMAIL_NOT_UNIQUE = "1";
     String DEPT_NAME_UNIQUE = "0";
     String DEPT_NAME_NOT_UNIQUE = "1";
     String ROLE_NAME_UNIQUE = "0";
     String ROLE_NAME_NOT_UNIQUE = "1";
     String POST_NAME_UNIQUE = "0";
     String POST_NAME_NOT_UNIQUE = "1";
     String ROLE_KEY_UNIQUE = "0";
     String ROLE_KEY_NOT_UNIQUE = "1";
     String POST_CODE_UNIQUE = "0";
     String POST_CODE_NOT_UNIQUE = "1";
     String MENU_NAME_UNIQUE = "0";
     String MENU_NAME_NOT_UNIQUE = "1";
     String DICT_TYPE_UNIQUE = "0";
     String DICT_TYPE_NOT_UNIQUE = "1";
     String CONFIG_KEY_UNIQUE = "0";
     String CONFIG_KEY_NOT_UNIQUE = "1";
     int PASSWORD_MIN_LENGTH = 5;
     int PASSWORD_MAX_LENGTH = 20;
     String MOBILE_PHONE_NUMBER_PATTERN = "^0{0,1}(13[0-9]|15[0-9]|14[0-9]|18[0-9])[0-9]{8}$";
     String EMAIL_PATTERN = "^((([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+(\\.([a-z]|\\d|[!#\\$%&'\\*\\+\\-\\/=\\?\\^_`{\\|}~]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])+)*)|((\\x22)((((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(([\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x7f]|\\x21|[\\x23-\\x5b]|[\\x5d-\\x7e]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(\\\\([\\x01-\\x09\\x0b\\x0c\\x0d-\\x7f]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF]))))*(((\\x20|\\x09)*(\\x0d\\x0a))?(\\x20|\\x09)+)?(\\x22)))@((([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|\\d|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.)+(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])|(([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])([a-z]|\\d|-|\\.|_|~|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])*([a-z]|[\\u00A0-\\uD7FF\\uF900-\\uFDCF\\uFDF0-\\uFFEF])))\\.?";
}
