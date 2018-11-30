package eu.jpereira.trainings.designpatterns.creational.builder;

import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;

public class ReportGenerator {

	private ReportAssembler rap;
	
	public ReportGenerator(ReportAssembler rap) {
		this.rap=rap;
	}
	public Report generate(SaleEntry saleEntry)
	{
		rap.setSaleEntry(saleEntry);
		return rap.getReport();
	}

}
