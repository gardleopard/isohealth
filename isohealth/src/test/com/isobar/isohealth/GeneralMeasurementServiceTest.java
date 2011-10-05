package test.com.isobar.isohealth;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.isobar.isohealth.GraphConstants;
import com.isobar.isohealth.models.GeneralMeasurement;
import com.isobar.isohealth.models.GeneralMeasurementFeed;
import com.isobar.isohealth.models.GeneralMeasurementFeed.Item;
import com.isobar.isohealth.models.NewGeneralMeasurement;
import com.isobar.isohealth.wrappers.GeneralMeasurementWrapper;
import com.isobar.isohealth.wrappers.RunkeeperService;

public class GeneralMeasurementServiceTest extends TestCase {
	
	GeneralMeasurementWrapper generalMeasurementWrapper;
	GeneralMeasurementFeed generalMeasurementFeed;
	
	protected void setUp() {
    	RunkeeperService runkeeperService = new RunkeeperService(GraphConstants.AUTH_CODE);
    	generalMeasurementWrapper = runkeeperService.generalMeasurementWrapper;
    	try {
    		generalMeasurementFeed = generalMeasurementWrapper.getGeneralMeasurementFeed();
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    }
	public void testGeneralMeasurementFeed() {
		try {
//			GeneralMeasurementFeed generalMeasurementFeed = GeneralMeasurementService.getGeneralMeasurementFeed(GraphConstants.AUTH_CODE);
			System.out.println("BackgroundActivityFeed: " + generalMeasurementFeed.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testGeneralMeasurement() {
		try {
//			GeneralMeasurementFeed generalMeasurementFeed = GeneralMeasurementService.getGeneralMeasurementFeed(GraphConstants.AUTH_CODE);
			List<GeneralMeasurement> generalMeasurementList =  new ArrayList<GeneralMeasurement>();
			for (Item item : generalMeasurementFeed.getItems()) {
				GeneralMeasurement generalMeasurement = generalMeasurementWrapper.getGeneralMeasurement(item.getUri());
				System.out.println("GeneralMeasurement: " + generalMeasurement.toString());
				generalMeasurementList.add(generalMeasurement);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testUpdateGeneralMeasurement() {
		try {
//			GeneralMeasurementFeed generalMeasurementFeed = GeneralMeasurementService.getGeneralMeasurementFeed(GraphConstants.AUTH_CODE);
			for (Item item : generalMeasurementFeed.getItems()) {
				GeneralMeasurement generalMeasurement = generalMeasurementWrapper.getGeneralMeasurement(item.getUri());
				generalMeasurement.setSystolic(125.0);
				GeneralMeasurement updatedGeneralMeasurement = generalMeasurementWrapper.updateGeneralMeasurement(generalMeasurement);
				System.out.println("Updated GeneralMeasurement: " + updatedGeneralMeasurement);
				break;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
	
	public void testCreateNewGeneralMeasurement() {
		try {
		  NewGeneralMeasurement generalMeasurement = new NewGeneralMeasurement();
		  
		  generalMeasurement.setDiastolic(70.0);
		  generalMeasurement.setTimestamp("Wed, 5 Jan 2011 07:03:00");
		  
		  generalMeasurementWrapper.createGeneralMeasurement(generalMeasurement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
