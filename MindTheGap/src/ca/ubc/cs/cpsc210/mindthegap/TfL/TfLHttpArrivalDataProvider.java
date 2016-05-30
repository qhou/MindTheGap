package ca.ubc.cs.cpsc210.mindthegap.TfL;

import ca.ubc.cs.cpsc210.mindthegap.model.Line;
import ca.ubc.cs.cpsc210.mindthegap.model.Station;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

/**
 * Wrapper for TfL Arrival Data Provider
 */
public class TfLHttpArrivalDataProvider extends AbstractHttpDataProvider {
    private Station stn;

    public TfLHttpArrivalDataProvider(Station stn) {
        super();
        this.stn = stn;
    }
    
    @Override
    /**
     * Produces URL used to query TfL web service for expected arrivals at
     * station specified in call to constructor.
     *   //getting a URL for all lines one can get from stations.getLines()
     * @returns URL to query TfL web service for arrival data
     */
    // example:  https://api.tfl.gov.uk/Line/bakerloo/Arrivals?stopPointId=940GZZLUHAW&app_id=&app_key=
    // example:  https://api.tfl.gov.uk/Line/        /Arrivals?stopPointId=           &app_id=&app_key=
    // example 2: https://api.tfl.gov.uk/Line/central,jubilee/Arrivals?stopPointId=940GZZLUSTD&app_id=&app_key=
    // example 2: https://api.tfl.gov.uk/Line/       ,       /Arrivals?stopPointId=           &app_id=&app_key=
    protected URL getURL() throws MalformedURLException {
        //Query paremetres: stopPointId, ids (=line ids)
        String first = "https://api.tfl.gov.uk/Line/"; // add lines (one or more) after this
        String middle = "/Arrivals?stopPointId="; // add single id after this
        String last = "&app_id=&app_key=";

        String stnId = stn.getID();

        String lineIds = "";
        Set<Line> lines = stn.getLines();
        for (Line nextLine : lines){
            lineIds += nextLine.getId() + ",";
        }
        lineIds = lineIds.substring(0, lineIds.length()-1);

        String request = first + lineIds + middle + stnId + last;

        System.out.println(request);

        return new URL(request);

    }

}
