import org.testng.Assert;
import org.testng.annotations.Test;
import pages.GoogleMapsPage;

import static utils.TestRunner.report;

public class MapsTest extends BaseTest {

    private String GOOGLE_MAPS_URL = "http://google.com/maps";
    private String SEARCH_TERM = "Rome";


    @Test
    public void GoogleMapsTest() {
        report.startLevel("Open URL:" + GOOGLE_MAPS_URL);
        openURL(GOOGLE_MAPS_URL);
        GoogleMapsPage mapsPage = new GoogleMapsPage(driver);
        report.endLevel();

        report.startLevel("Search Rome");
        mapsPage.search(SEARCH_TERM);
        report.endLevel();

        report.startLevel("zoomIn (with screenshot) #1");
        mapsPage.zoomInMap();
        report.endLevel();

        report.startLevel("zoomIn (with screenshot) #2");
        mapsPage.zoomInMap();
        report.endLevel();

        report.startLevel("zoomIn (with screenshot) #3");
        mapsPage.zoomInMap();
        report.endLevel();

    }


}
