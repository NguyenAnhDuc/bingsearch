package bing;
import java.util.ArrayList;
import java.util.List;

import net.billylieurance.azuresearch.AzureSearchResultSet;
import net.billylieurance.azuresearch.AzureSearchWebQuery;
import net.billylieurance.azuresearch.AzureSearchWebResult;





/**
 * @author Phuong LE-HONG
 * <p>
 * Jun 17, 2014, 9:48:49 AM
 * <p>
 * Pull web search results from Bing as a list of {@link Document}. 
 */
public class BingWebSearchProvider  {
	private String appId;
	
	/**
	 * Creates a puller using the default appId (of phuonglh).
	 */
	public BingWebSearchProvider() {
		this.appId = "sPqkjRn2CLELygmcDJ7kIAC0YOm0nihpzVJ3RLVu3XI=";
	}
	
	/**
	 * Creates a puller given an appId defined by MS Azure.
	 * @param appId
	 * @param useKeywordExpansion
	 */
	/*public BingWebSearchProvider() {
		super(useKeywordExpansion);
		this.appId = appId;
	}*/
	
	/* (non-Javadoc)
	 * @see com.fpt.qa.lucene.search.ISearchResultProvider#getDocuments(java.lang.String)
	 */
	public List<String> getDocuments(String question, Integer...numOfResults) {
		List<String> docs = new ArrayList<String>();
		AzureSearchWebQuery query = new AzureSearchWebQuery();
		query.setAppid(appId);
		query.setQuery(question);
		query.doQuery();
		query.setMarket("Vietnam");
		AzureSearchResultSet<AzureSearchWebResult> results = query.getQueryResult();
		Integer numOfResult = numOfResults.length > 0 ? numOfResults[0] : Integer.MAX_VALUE;
		int count = 0;
		for (AzureSearchWebResult r : results) {
			//String doc = r.getDescription();
			String doc = r.getDisplayUrl();
//			System.out.println(doc);
			docs.add(doc);
			count ++;
			if (count>=numOfResult) break;
		}
		return docs;
	}

	public static void main(String[] args){
		BingWebSearchProvider bingWebSearchProvider = new BingWebSearchProvider();
		List<String> docs = bingWebSearchProvider.getDocuments("chủ tịch fpt");
		for (String doc : docs) {
			System.out.println("Doc: " + doc);
		}
		System.out.println("DONE!");
	}
	
}
