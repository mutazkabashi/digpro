package com.digpro.codetest.frontend.jsf.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.PrimeFaces;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.line.LineChartDataSet;
import org.primefaces.model.charts.line.LineChartModel;
import org.primefaces.model.charts.line.LineChartOptions;
import org.primefaces.model.charts.optionconfig.title.Title;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import com.digpro.codetest.backend.domain.Coordinate;
import com.digpro.codetest.backend.exception.CodeTestException;
import com.digpro.codetest.backend.service.CoordinateService;

/**
 * 
 * @author Mutaz Hussein Kabashi
 * @version 1.0
 *
 */
@Named
@SessionScoped
public class ChartJsView implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(ChartJsView.class);

	private LineChartModel lineModel;

	private CoordinateService coordinateService;

	// Spring Environment object which holds all configurations values from the
	// Config file
	private Environment env;

	private Integer refreshTimeInterval;

	private Boolean automaticChartUpdate;

	private String fromActionsMessage;

	private String errorMessage;

	@Inject
	public ChartJsView(CoordinateService coordinateService, Environment env) {
		super();
		this.coordinateService = coordinateService;
		this.env = env;
	}

	@PostConstruct
	public void init() {
		LOG.info("inilization of Chat screen");
		createLineModel();
		// getNewCoordinates();
		refreshTimeInterval = Integer.valueOf(env.getProperty("frontend.chartJsView.refreshInterval"));
		automaticChartUpdate = Boolean.valueOf(env.getProperty("frontend.chartJsView.chartUpdate"));
		LOG.info("inilization of Chat screen finshed");
	}

	public List<Coordinate> getNewCoordinates() throws CodeTestException {
		LOG.info("calling back end service to get new coordiantes");
		List<Coordinate> coordinates = coordinateService.getNewCoordiantes();
		LOG.debug("Retrives coordinates data");
		for (Coordinate coordinate : coordinates) {
			LOG.debug("current coordiantes " + coordinate.toString());
		}
		return coordinates;
	}

	public void createLineModel() {
		lineModel = new LineChartModel();
		errorMessage = "";

		// Options
		LineChartOptions options = new LineChartOptions();
		Title title = new Title();
		title.setDisplay(true);
		title.setText("Line Chart");
		options.setTitle(title);

		lineModel.setOptions(options);
		try {
			LOG.info("getting new Coordinates from backend service");
			ChartData data = convertCoordinatesToChartData(getNewCoordinates());
			LOG.info("new Coordinates converted to ChartData");
			lineModel.setData(data);
		} catch (Exception e) {
			LOG.error("Error Occuered while getting new Coordiantes from back end service", e);
			errorMessage = env.getProperty("dpe01");
		}

		fromActionsMessage = "";
	}

	// Listener Methods
	public void save() {
		LOG.trace("AutomaticChartUpdate has been changed to  " + automaticChartUpdate
				+ "and refreshTimeInterval has been changed to " + automaticChartUpdate);
		fromActionsMessage = env.getProperty("frontend.chartJsView.messages.updateSettingVaraibles");

	}

	public void showAboutTheApp() {
		String aboutApp = env.getProperty("frontend.chartJsView.messages.aboutTheApp");
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "About The App", aboutApp);
		PrimeFaces.current().dialog().showMessageDynamic(message);
	}

	// Util methods
	private ChartData convertCoordinatesToChartData(List<Coordinate> coordinates) {
		ChartData data = new ChartData();
		LineChartDataSet dataSet = new LineChartDataSet();
		List<Number> values = new ArrayList<>();
		List<String> labels = new ArrayList<>();
		for (Coordinate coordinate : coordinates) {
			values.add(coordinate.getY());
			String label = coordinate.getName() + "/" + coordinate.getX();
			labels.add(label);
		}

		dataSet.setData(values);
		dataSet.setFill(false);
		dataSet.setLabel("Coordinates Dataset");
		dataSet.setBorderColor("rgb(75, 192, 192)");
		dataSet.setLineTension(0.1);
		data.addChartDataSet(dataSet);
		data.setLabels(labels);
		return data;

	}

	public LineChartModel getLineModel() {
		return lineModel;
	}

	public void setLineModel(LineChartModel lineModel) {
		this.lineModel = lineModel;
	}

	public Integer getRefreshTimeInterval() {
		return refreshTimeInterval;
	}

	public void setRefreshTimeInterval(Integer refreshTimeInterval) {
		this.refreshTimeInterval = refreshTimeInterval;
	}

	public Boolean getAutomaticChartUpdate() {
		return automaticChartUpdate;
	}

	public void setAutomaticChartUpdate(Boolean automaticChartUpdate) {
		this.automaticChartUpdate = automaticChartUpdate;
	}

	public String getFromActionsMessage() {
		return fromActionsMessage;
	}

	public void setFromActionsMessage(String fromActionsMessage) {
		this.fromActionsMessage = fromActionsMessage;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
