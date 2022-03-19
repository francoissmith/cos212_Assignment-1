import java.text.DecimalFormat;

public class Interface {

	private Node origin;

	private String floatFormatter(float value){
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(value);
	}

	//DO NOT CHANGE THE ABOVE FUNCTION
	//Place your code below

	public Interface() {
		origin = new Node(new Origin(), 0, 0);
		origin.nextVal = null;
		origin.prevVal = null;
	}

	public Interface(Node[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				addPoint(arr[i].getFunction(), arr[i].getVariables()[0], arr[i].getVariables()[1]);
			}
		}
	}

	public Node getOrigin() {
	}

	public float addPoint(Function function, int v1, int v2) {
		if (v1 == 0 || v2 == 0)
			return Float.NaN;
		else {
			Node newNode = new Node(function, v1, v2);
	
			if (v1 > 0) {
				Node ptr = origin;
				if (origin.right == null) {
					origin.right = new Node(new V1Axis(), v1, 0);
					origin.right.left = origin;
				}
				else {
					ptr = ptr.right;
					while (ptr.right != null || ptr.getVariables()[0] < v1) {
						ptr = ptr.right;
					}
				}
			}

			return newNode.getValue();
		}
	}

	public Node removePoint(int v1, int v2) {
	}

	public Node getPoint(int v1, int v2) {
	}

	public Node[] toArray() {
	}

	public float calculateValue(Function function, int v1, int v2) {
	}

	public float findMaxValue() {
	}

	public Node findMax() {
	}

	public float findMinValue() {
	}

	public Node findMin() {
	}

	public String printFunctionValues(String functionName) {
	}

	public int removeAllFunctionPoints(String functionName){
	}

	public int countNumberOfPoints(){
	}

	public int[] numPointsPerQuadrant(){
	}

	public void clearAllData(){
	}

	//ADD HELPER FUNCTIONS BELOW
}