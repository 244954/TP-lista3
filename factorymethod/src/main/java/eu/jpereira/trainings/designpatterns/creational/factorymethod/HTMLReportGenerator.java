package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class HTMLReportGenerator extends ReportGenerator {

	public HTMLReportGenerator() {
		// TODO Auto-generated constructor stub
	}
	
	public Report generateReport(ReportData data) {

		Report generatedReport = null;
		generatedReport = new HTMLReport();
		generatedReport.generateReport(data);
		return generatedReport;
		
	}

}
