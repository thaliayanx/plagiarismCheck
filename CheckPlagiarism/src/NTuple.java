import java.util.ArrayList;
import java.util.List;

public class NTuple {
	private List<String> inputs;

	private int size;

	public NTuple(int N) {
		inputs = new ArrayList<String>();
		this.size = N;
	}

	public void addNew(String input) {
		inputs.add(input);
	}

	public List<String> getList() {
		return inputs;
	}

	public void setList(ArrayList<String> list) {
		this.inputs = list;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int N) {
		size = N;
	}
	 public boolean compareTuples(NTuple t1) {
	        List<String> list1 = t1.getList();
	        List<String> list2 = this.getList();

	        if (list1.size() != list2.size())
	            return false;

	        for (int i = 0; i < size; i++) {
	            String tmp1 = list1.get(i).toLowerCase();
	            String tmp2 = list2.get(i).toLowerCase();

	            if (!tmp1.equals(tmp2)) {
	            	return false;
	            }
	        }

	        return true;
	    }

	@Override
	public String toString() {
		StringBuilder res=new StringBuilder();
		for(int i=0;i<inputs.size();i++){
			res.append(inputs.get(i));
			res.append(" ");
		}
		return res.toString();
	}

}
