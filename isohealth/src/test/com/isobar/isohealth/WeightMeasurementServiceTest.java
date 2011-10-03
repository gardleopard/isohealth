package test.com.isobar.isohealth;

import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import com.isobar.isohealth.GraphConstants;
import com.isobar.isohealth.models.NewWeightMeasurement;
import com.isobar.isohealth.models.WeightMeasurement;
import com.isobar.isohealth.models.WeightMeasurementFeed;
import com.isobar.isohealth.models.WeightMeasurementFeed.Item;
import com.isobar.isohealth.wrappers.RunkeeperService;
import com.isobar.isohealth.wrappers.WeightManagementWrapper;

public class WeightMeasurementServiceTest extends TestCase {
	
	WeightManagementWrapper weightMeasurementWrapper;
	WeightMeasurementFeed weightMeasurementFeed;
	
	@Override
	protected void setUp() {
    	RunkeeperService runkeeperService = new RunkeeperService(GraphConstants.AUTH_CODE);
    	weightMeasurementWrapper = runkeeperService.weightManagementWrapper;
    	try {
    		weightMeasurementFeed = weightMeasurementWrapper.getWeightMeasurementFeed();
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    }
	
	public void testWeightMeasurementFeed() {
		try {
			WeightMeasurementFeed weightMeasurementFeed = weightMeasurementWrapper.getWeightMeasurementFeed();
			System.out.println("WeightMeasurementFeed: " + weightMeasurementFeed.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void testWeightMeasurement() {
		try {
			List<WeightMeasurement> weightMeasurementList =  new ArrayList<WeightMeasurement>();
			for (Item item : weightMeasurementFeed.getItems()) {
				WeightMeasurement weightMeasurement = weightMeasurementWrapper.getWeightMeasurement(item.getUri());
				System.out.println("SleepMeasurement: " + weightMeasurement.toString());
				weightMeasurementList.add(weightMeasurement);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void testUpdateWeightMeasurement() {
		try {
			for (Item item : weightMeasurementFeed.getItems()) {
				WeightMeasurement weightMeasurement = weightMeasurementWrapper.getWeightMeasurement(item.getUri());
				weightMeasurement.setWeight(80.0);
				weightMeasurement.setTimestamp("Wed, 5 Jan 2011 07:03:00");
				WeightMeasurement updatedWeightMeasurement = weightMeasurementWrapper.updateWeightMeasurement(weightMeasurement);
				System.out.println("Updated SleepMeasurement: " + updatedWeightMeasurement);
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}		
	
	public void testCreateNewWeightMeasurement() {
		try {
			NewWeightMeasurement weightMeasurement = new NewWeightMeasurement();
			weightMeasurement.setWeight(80.0);
			weightMeasurement.setTimestamp("Wed, 5 Jan 2011 07:03:00");
		  
			weightMeasurementWrapper.createWeightMeasurement(weightMeasurement);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}