package eu.jpereira.trainings.designpatterns.creational.builder;

import java.util.Iterator;

import eu.jpereira.trainings.designpatterns.creational.builder.json.JSONReportBody;
import eu.jpereira.trainings.designpatterns.creational.builder.model.Report;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SaleEntry;
import eu.jpereira.trainings.designpatterns.creational.builder.model.SoldItem;

public class JSONReportAssembler implements ReportAssembler {

	protected SaleEntry saleEntry;
	
	public JSONReportAssembler() {
		// TODO Auto-generated constructor stub
	}
	
	public void setSaleEntry(SaleEntry saleEntry)
	{
		this.saleEntry=saleEntry;
	}
	
	public Report getReport()
	{
		Report report = new Report();
		
		JSONReportBody reportBody = new JSONReportBody();
		//Add customer info
		reportBody.addContent("sale:{customer:{");
		reportBody.addContent("name:\"");
		reportBody.addContent(saleEntry.getCustomer().getName());
		reportBody.addContent("\",phone:\"");
		reportBody.addContent(saleEntry.getCustomer().getPhone());
		reportBody.addContent("\"}");
		//add array of items
		reportBody.addContent(",items:[");
		Iterator<SoldItem> it = saleEntry.getSoldItems().iterator();
		while ( it.hasNext() ) {
			SoldItem item = it.next();
			reportBody.addContent("{name:\"");
			reportBody.addContent(item.getName());
			reportBody.addContent("\",quantity:");
			reportBody.addContent(String.valueOf(item.getQuantity()));
			reportBody.addContent(",price:");
			reportBody.addContent(String.valueOf(item.getUnitPrice()));
			reportBody.addContent("}");
			if ( it.hasNext() ) {
				reportBody.addContent(",");
			}
			
		}
		reportBody.addContent("]}");
		
		
		report.setReportBody(reportBody);
		
		return report;
	}

}
