package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class JSONReportGenerator extends ReportGenerator {

	public JSONReportGenerator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Report generateReport(ReportData data) {
		Report generatedReport = null;
		generatedReport = new JSONReport();
		generatedReport.generateReport(data);
		return generatedReport;
	}

}
