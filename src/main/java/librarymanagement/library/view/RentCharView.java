package librarymanagement.library.view;

import java.util.ArrayList;
import java.util.List;

public class RentCharView {
    
    private List<String> label=new ArrayList<>();
    private List<String> rentCount=new ArrayList<>();
    private List<String> returnCount=new ArrayList<>();
    public List<String> getLabel() {
        return label;
    }
    public List<String> getRentCount() {
        return rentCount;
    }
    public List<String> getReturnCount() {
        return returnCount;
    }
    public void setLabel(List<String> label) {
        this.label = label;
    }
    public void setRentCount(List<String> rentCount) {
        this.rentCount = rentCount;
    }
    public void setReturnCount(List<String> returnCount) {
        this.returnCount = returnCount;
    }

    
    

    
}
