package com.api.tests;

import controllers.RfqResponse;
import helpers.ApiHelper;
import org.apache.commons.lang3.tuple.Pair;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ApiTests {

    @BeforeClass
    public void setup() {
        ApiHelper.setup();
    }

    @DataProvider(name = "freeTextInputs")
    public Object[][] freeTextInputs() {
        return new Object[][] {
                {"upload-free-text-first-body.json", new String[]{"cutting board"}},
                {"upload-free-text-second-body.json", new String[]{"4 qt", "square"}}
        };
    }

    @Test(dataProvider = "freeTextInputs")
    public void testFreeText(String jsonFile, String[] expectedKeywords) {
        Pair<Integer, RfqResponse> result = ApiHelper.createPostText(jsonFile);
        Assert.assertEquals(result.getLeft().intValue(), HttpStatus.SC_OK);
        assertContainsKeywords(result.getRight(), expectedKeywords);
    }

    @DataProvider(name = "urlInputs")
    public Object[][] urlInputs() {
        return new Object[][] {
                {"upload-url-html-first-body.json", new String[]{"cutting board"}},
                {"upload-url-html-second-body.json", new String[]{"4 qt", "square"}}
        };
    }

    @Test(dataProvider = "urlInputs")
    public void testUrlHtml(String jsonFile, String[] expectedKeywords) {
        Pair<Integer, RfqResponse> result = ApiHelper.createPostUrl(jsonFile);
        Assert.assertEquals(result.getLeft().intValue(), HttpStatus.SC_OK);
        assertContainsKeywords(result.getRight(), expectedKeywords);
    }

    private void assertContainsKeywords(RfqResponse response, String[] keywords) {
        boolean found = false;
        for (RfqResponse.MatchedItem item : response.getResult().getMatchedItems()) {
            List<RfqResponse.MatchedInternalProduct> candidates = item.getMatchedInternalProducts();
            for (RfqResponse.MatchedInternalProduct candidate : candidates) {
                String name = candidate.getName().toLowerCase();
                int confidence = candidate.getPercentage(); // extract confidence

                for (String keyword : keywords) {
                    if (name.contains(keyword.toLowerCase()) && confidence >= 80) { // set threshold
                        found = true;
                        break;
                    }
                }
                if (found) break;
            }
            if (found) break;
        }
        Assert.assertTrue(found, "No matchedInternalProduct contains expected keywords with sufficient confidence");
    }
}
