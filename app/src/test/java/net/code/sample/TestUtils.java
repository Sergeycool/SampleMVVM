package net.code.sample;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.apache.commons.io.IOUtils;
import org.junit.rules.TestName;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import okhttp3.ResponseBody;
import retrofit2.Response;

@SuppressWarnings({"WeakerAccess", "unused"})
public class TestUtils {
    /**
     * @param resourceName name of the resource in src/test/resources/
     * @return a {@link File} object or null if the resource could not be found
     */
    @Nullable
    public static File getTestResourceFile(@NonNull String resourceName) {
        File result = null;

        ClassLoader classLoader = TestUtils.class.getClassLoader();
        @SuppressWarnings("ConstantConditions")
        URL resourceURL = classLoader.getResource(resourceName);
        if (resourceURL != null) {
            result = new File(resourceURL.getPath());
        }

        return result;
    }

    public static void saveResponseBodyOnDisk(@NonNull ResponseBody responseBody,
                                              @NonNull File file) {
        try (InputStream inputStream = responseBody.byteStream();
             FileOutputStream fileOutputStream = new FileOutputStream(file)
        ) {
            IOUtils.copyLarge(inputStream, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints an HTTP response info for test methods only.
     *
     * @param response an HTTP response
     */
    public static void printResponseInfo(@NonNull TestName testName, @NonNull Response response) {
        String methodName = testName.getMethodName();
        if (methodName != null) {
            int responseCode = response.code();
            String responseBodyString = getResponseBodyString(response.body());
            String responseErrorBodyString = getResponseBodyString(response.errorBody());

            System.out.println("------------------------------------------------------------------------");
            System.out.println("Test method name       | " + methodName);
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Response code          | " + responseCode);
            System.out.println("Response body          | " + responseBodyString);
            System.out.println("Response error body    | " + responseErrorBodyString);
            System.out.println("------------------------------------------------------------------------");
            System.out.println();
        }
    }

    @Nullable
    private static String getResponseBodyString(@Nullable Object responseBody) {
        String responseBodyString = null;
        if (responseBody != null) {
            responseBodyString = responseBody.toString();
        }
        if (responseBody instanceof ResponseBody) {
            try {
                responseBodyString = ((ResponseBody) responseBody).string();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return responseBodyString;
    }
}
