package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class XMLReportGenerator extends ReportGenerator {

	public XMLReportGenerator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Report generateReport(ReportData data) {
		Report generatedReport = null;
		generatedReport = new XMLReport();
		generatedReport.generateReport(data);
		return generatedReport;
	}

}
