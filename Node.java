import java.text.DecimalFormat;

public class Node {

	private int v1; // this is the first variable
	private int v2; // this is the second variable
	public Node left; // this is the node left of this node
	public Node right; // this is the node right of this node
	public Node up; // this is the node up of this node
	public Node down; // this is the node down of this node
	public Node nextVal; // this is the next value of the current node
	public Node prevVal; // this is the prev value of the current node
	private Function nodeFunction; // this is the function associated with the current node
	private String floatFormatter(float value) {
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(value);
	}

	// DO NOT CHANGE THE ABOVE FUNCTION
	// Place your code below

	public Node(Function function, int v1, int v2) {
		this.v1 = v1;
		this.v2 = v2;
		nodeFunction = function.clone();
	}

	public Function setFunction(Function function) {
		Function temp = nodeFunction;
		nodeFunction = function;
		return temp;
	}

	public float getValue() {
		if (nodeFunction == null)
			return Float.NEGATIVE_INFINITY;

		return nodeFunction.calculate(v1, v2);
	}

	public int[] getVariables() {
		int[] varArr = new int[2];
		varArr[0] = v1;
		varArr[1] = v2;

		return varArr;
	}

	public Function getFunction() {
		return nodeFunction;
	}

	public String[] getNodeLinks() {
		String[] result = new String[6];

		if (up != null)
			result[0] = "U[" + up.v1 + "][" + up.v2 + "]{" + up.getValue() + "}";
		else
			result[0] = "U[][]{}";

		if (down != null)
			result[1] = "D[" + down.v1 + "][" + down.v2 + "]{" + down.getValue() + "}";
		else
			result[1] = "D[][]{}";

		if (right != null)
			result[2] = "R[" + right.v1 + "][" + right.v2 + "]{" + right.getValue() + "}";
		else
			result[2] = "R[][]{}";

		if (left != null)
			result[3] = "L[" + left.v1 + "][" + left.v2 + "]{" + left.getValue() + "}";
		else
			result[3] = "L[][]{}";

		if (prevVal != null)
			result[4] = "P[" + prevVal.v1 + "][" + prevVal.v2 + "]{" + prevVal.getValue() + "}";
		else
			result[4] = "P[][]{}";

		if (nextVal != null)
			result[5] = "N[" + nextVal.v1 + "][" + nextVal.v2 + "]{" + nextVal.getValue() + "}";
		else
			result[5] = "N[][]{}";

		return result;
	}

}