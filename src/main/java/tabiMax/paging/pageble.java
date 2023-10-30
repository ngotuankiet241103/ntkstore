package tabiMax.paging;


public interface pageble {
	Integer getPage();
	Integer getMaxPageItem();
	Integer getTotalsPage();
	void setTotalsPage(int totalsPage);
	void setPage(int page);
	Integer getPageStart();
	
}
