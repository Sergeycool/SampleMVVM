package net.code.sample.toolchain;

import android.text.TextUtils;

public class GetAbbrUtil {
    public static String setAbbr(String firstName, String lastName) {
        String value = "";
        if (firstName != null)
            if (!firstName.isEmpty())
                value = String.valueOf(firstName.charAt(0));

        if (lastName != null)
            if (!lastName.isEmpty())
                value = value + lastName.charAt(0);

        return value;
    }

    public static String setAbbrIfNeeded(GetNewsRequestsResponse.Data.NewsRequest item) {
        if (TextUtils.isEmpty(item.getAvatarUrl()))
            return setAbbr(item.getFirstName(), item.getLastName());
        else
            return null;
    }

}
