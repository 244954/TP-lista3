package eu.jpereira.trainings.designpatterns.creational.factorymethod;

public class PDFReportGenerator extends ReportGenerator {

	public PDFReportGenerator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Report generateReport(ReportData data) {
		Report generatedReport = null;
		generatedReport = new PDFReport();
		generatedReport.generateReport(data);
		return generatedReport;
	}

}
