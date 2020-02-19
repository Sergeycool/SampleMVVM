package net.code.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class TestConstants {
    // changing data
    public static final String USER_ID = "741";
    public static final String HASH = "773beeb95e571f348f4226869268d90d";
    public static final String SMS_CODE = "5655";

    public static final String PHONE_COUNTRY_CODE = "380";
    public static final String NATIONAL_PHONE_NUMBER = "990825626";
    public static final String ISO2_COUNTRY_CODE = "UA";
    public static final String FIRST_NAME = "FirstName";
    public static final String LAST_NAME = "LastName";
    public static final String EMAIL = "Exame1928@cuvox.de";
    public static final String PASSWORD = "Qwertyui78";
    public static final String AVATAR_URL = "https://urlofseo.com/images/leemarshallavatar200.png";
    public static final String AVATAR_URL_2 = "http://www.newdesignfile.com/postpic/2014/12/black-man-in-suit-silhouette_358883.jpg";
    public static final List<String> PHONE_CONTACTS_DATA = Arrays.asList("380990825666",
            "380981295020", "some1@email.com", "some2@email.com", "111111111111", "222222222222");

    public static final String ALBUM_TITLE = "album title";
    public static final String ALBUM_ID = "21";
    public static final String PICTURE_COMMENT = "Nice picture!";
    public static final String PICTURE_ID = "123456";
    public static final String PAGE_NUMBER = "1";
    public static final String INVITE_REQUEST_ID = "245";
    public static final boolean ACCEPTED_REQUEST = true;
    public static final String APP_VERSION = "1.35";
    public static final String ANDROID_VERSION = "Android 29";
    public static final String DEEP_LINK_CODE = "iqyq";
    public static final String SORTING_CRITERIA_DATE = "date";
    public static final String PICTURE_URL = "https://sambplebucket.s3.eu-west-2.amazonaws.com/album_pics/5251_1577192677201_67bc4808-e055-4d5b-a87c-8bb6af8b67df.jpg";


    public static final String FRIEND_EMAIL = "Sters1976@superrito.com";
    public static final String FRIEND_PASSWORD = "qwerty123";
    public static final String FRIEND_PHONE = "380990825666";
    public static final String FRIEND_ID = "791";
    public static final String FRIEND_HASH = "e865ff52caf1ff6844f423155c5a528a";
    public static final List<User> USER = new ArrayList<>(Collections.singleton(
            new User(FRIEND_ID, FRIEND_EMAIL, FIRST_NAME, LAST_NAME, FRIEND_PHONE)));
    public static final String TEST_IMAGE_SMALL = "test_image_small.jpg";
    public static final String TEST_DIR_NAME = "temporary";

}
