import java.text.DecimalFormat;

public class Interface {

	private Node origin;

	private String floatFormatter(float value) {
		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(value);
	}

	// DO NOT CHANGE THE ABOVE FUNCTION
	// Place your code below

	// ***************************************************************************
	// Interface();
	public Interface() {
		origin = new Node(new Origin(), 0, 0);
		origin.nextVal = null;
		origin.prevVal = null;
	}

	// ***************************************************************************
	// Interface( Node[]arr );
	public Interface(Node[] arr) {
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != null) {
				addPoint(arr[i].getFunction(), arr[i].getVariables()[0], arr[i].getVariables()[1]);
			}
		}
	}

	// ***************************************************************************
	// getOrigin();
	public Node getOrigin() {
		return origin;
	}

	// ***************************************************************************
	// addPoint();
	public float addPoint(Function function, int v1, int v2) {
		if (v1 == 0 || v2 == 0)
			return Float.NaN;
		else {
			Node newNode = new Node(function, v1, v2);
			Node[] axiNodes = new Node[2];
			Node tmp;

			if (v1 > 0 && v2 > 0) {	// --------- first quadrant ---------
				axiNodes[0] = search(v1, 1);
				axiNodes[1] = search(v2, 2);

				if(axiNodes[0] == null) 
					setAxis(v1, 1);
				if (axiNodes[1] == null)
					setAxis(v2, 2);
				
					tmp = getPoint(v1, v2);

					if (tmp == null) {
						Node v2ptr = axiNodes[1], v1ptr = axiNodes[0];

						while (v2ptr.right != null && v2ptr.right.getVariables()[0] < v1) {
							v2ptr = v2ptr.right;
						}
	
						if(v2ptr.right == null) {
							newNode.right = null;
							newNode.left = v2ptr;
							v2ptr.right = newNode;
						}
						else {
							newNode.right = v2ptr.right;
							newNode.left = v2ptr;
							v2ptr.right.left = newNode;
							v2ptr.right = newNode;
						}	

					}

			}

			return newNode.getValue();
		}
	}

	// ***************************************************************************
	// removePoint();
	// public Node removePoint(int v1, int v2) {
	// }

	// ***************************************************************************
	// getPoint();
	public Node getPoint(int v1, int v2) {
		if (v1 == 0 || v2 == 0) {
			return null;
		}

		Node v1Node, v2Node;
		v1Node = v2Node = origin;

		if (v1 > 0 && v2 > 0) { // --------- first quadrant ---------
			v1Node = search(v1, 1);
			v2Node = search(v2, 2);

			if (v1Node == null || v2Node == null)
				return null;
			else {
				Node tmp = v1Node;

				while (tmp != null && tmp.getVariables()[1] != v2Node.getVariables()[1]) {
					tmp = tmp.up;
				}

				if (tmp.getVariables()[1] == v2Node.getVariables()[1])
					return tmp;
				else
					return null;
			}
		} else if (v1 > 0 && v2 < 0) { // --------- second quadrant ---------
			v1Node = search(v1, 1);
			v2Node = search(v2, -2);

			if (v1Node == null || v2Node == null)
				return null;
			else {
				Node tmp = v1Node;

				while (tmp != null && tmp.getVariables()[1] != v2Node.getVariables()[1]) {
					tmp = tmp.down;
				}

				if (tmp.getVariables()[1] == v2Node.getVariables()[1])
					return tmp;
				else
					return null;
			}
		} else if (v1 < 0 && v2 < 0) { // --------- third quadrant ---------
			v1Node = search(v1, -1);
			v2Node = search(v2, -2);

			if (v1Node == null || v2Node == null)
				return null;
			else {
				Node tmp = v1Node;

				while (tmp != null && tmp.getVariables()[1] != v2Node.getVariables()[1]) {
					tmp = tmp.down;
				}

				if (tmp.getVariables()[1] == v2Node.getVariables()[1])
					return tmp;
				else
					return null;
			}
		} else if (v1 < 0 && v2 > 0) { // --------- fourth quadrant ---------
			v1Node = search(v1, -1);
			v2Node = search(v2, 2);

			if (v1Node == null || v2Node == null)
				return null;
			else {
				Node tmp = v1Node;

				while (tmp != null && tmp.getVariables()[1] != v2Node.getVariables()[1]) {
					tmp = tmp.up;
				}

				if (tmp.getVariables()[1] == v2Node.getVariables()[1])
					return tmp;
				else
					return null;
			}
		} else
			return null;
	}

	// ***************************************************************************
	// toArray();
	// public Node[] toArray() {
	// }

	// ***************************************************************************
	// calculateValue();
	// public float calculateValue(Function function, int v1, int v2) {
	// }

	// ***************************************************************************
	// findMaxValue();
	// public float findMaxValue() {
	// }

	// ***************************************************************************
	// findMax();
	// public Node findMax() {
	// }

	// ***************************************************************************
	// findMinValue();
	// public float findMinValue() {
	// }

	// ***************************************************************************
	// findMin()
	// public Node findMin() {
	// }

	// ***************************************************************************
	// printFunctionValues();
	// public String printFunctionValues(String functionName) {
	// }

	// ***************************************************************************
	// removeAllFunctionPoints();
	// public int removeAllFunctionPoints(String functionName){
	// }

	// ***************************************************************************
	// countNumberOfPoints();
	// public int countNumberOfPoints(){
	// }

	// ***************************************************************************
	// numPointsPerQuadrant();
	// public int[] numPointsPerQuadrant(){
	// }

	// ***************************************************************************
	// clearAllData()
	public void clearAllData() {
	}

	// ADD HELPER FUNCTIONS BELOW
	// ***************************************************************************
	// search();
	public Node search(int coord, int axis) {
		Node p = origin;

		switch (axis) {
			case 1:
				if (!IsEmpty(axis)) {
					while (p != null && p.getVariables()[0] != coord) {
						p = p.right;
					}
					if (p.getVariables()[0] == coord)
						return p;
					else
						return null;
				} else
					return null;

			case 2:
				if (!IsEmpty(axis)) {
					while (p != null & p.getVariables()[1] != coord) {
						p = p.up;
					}
					if (p.getVariables()[1] == coord)
						return p;
					else
						return null;
				} else
					return null;

			case -1:
				if (!IsEmpty(axis)) {
					while (p != null & p.getVariables()[0] != coord) {
						p = p.left;
					}

					if (p.getVariables()[0] == coord)
						return p;
					else
						return null;
				} else
					return null;

			case -2:
				if (!IsEmpty(axis)) {
					while (p != null & p.getVariables()[1] != coord) {
						p = p.down;
					}
					if (p.getVariables()[1] == coord)
						return p;
					else
						return null;
				} else
					return null;

			default:
				return null;
		}
	}

	// ***************************************************************************
	// IsEmpty();
	public boolean IsEmpty(int axis) {

		if (origin == null)
			return true;
		else
			switch (axis) {
				case 1:
					if (origin.right == null)
						return true;
					else
						return false;

				case 2:
					if (origin.up == null)
						return true;
					else
						return false;

				case -1:
					if (origin.left == null)
						return true;
					else
						return false;

				case -2:
					if (origin.down == null)
						return true;
					else
						return false;

				default:
					return true;
			}
	}

	// ***************************************************************************
	// setAxis();
	public void setAxis(int v1, int axis) {
		Node ptr = origin, newVAxis;

		switch (axis) {
			case 1: // -------------------- set V1-Axis > 0 --------------------
				newVAxis = new Node(new V1Axis(), v1, 0);
				if (origin.right == null) {
					newVAxis.left = origin;
					newVAxis.right = null;
					origin.right = newVAxis;
				} else {
					while (ptr.right != null && ptr.right.getVariables()[0] < v1) {
						ptr = ptr.right;
					}

					if (ptr.right == null) {
						newVAxis.right = null;
						newVAxis.left = ptr;
						ptr.right = newVAxis;
					} else {
						newVAxis.left = ptr;
						newVAxis.right = ptr.right;
						ptr.right.left = newVAxis;
						ptr.right = newVAxis;
					}
				}
				break;
			case 2: // -------------------- set V2-Axis > 0 --------------------
				newVAxis = new Node(new V2Axis(), 0, v1);
				if (origin.up == null) {
					newVAxis.down = origin;
					newVAxis.up = null;
					origin.up = newVAxis;
				} else {
					while (ptr.up != null && ptr.up.getVariables()[1] < v1) {
						ptr = ptr.up;
					}

					if (ptr.up == null) {
						newVAxis.up = null;
						newVAxis.down = ptr;
						ptr.up = newVAxis;
					} else {
						newVAxis.down = ptr;
						newVAxis.up = ptr.up;
						ptr.up.down = newVAxis;
						ptr.up = newVAxis;
					}
				}
				break;
			case -1: // -------------------- set V1-Axis < 0 --------------------
				newVAxis = new Node(new V1Axis(), v1, 0);
				if (origin.left == null) {
					newVAxis.right = origin;
					newVAxis.left = null;
					origin.left = newVAxis;
				} else {
					while (ptr.left != null && ptr.left.getVariables()[0] < v1) {
						ptr = ptr.left;
					}

					if (ptr.left == null) {
						newVAxis.left = null;
						newVAxis.right = ptr;
						ptr.left = newVAxis;
					} else {
						newVAxis.right = ptr;
						newVAxis.left = ptr.left;
						ptr.left.right = newVAxis;
						ptr.left = newVAxis;
					}
				}
				break;

			case -2: // -------------------- set V2-Axis < 0 --------------------
				newVAxis = new Node(new V2Axis(), 0, v1);
				if (origin.down == null) {
					newVAxis.up = origin;
					newVAxis.down = null;
					origin.down = newVAxis;
				} else {
					while (ptr.down != null && ptr.down.getVariables()[1] < v1) {
						ptr = ptr.down;
					}

					if (ptr.down == null) {
						newVAxis.down = null;
						newVAxis.up = ptr;
						ptr.down = newVAxis;
					} else {
						newVAxis.up = ptr;
						newVAxis.down = ptr.down;
						ptr.down.up = newVAxis;
						ptr.down = newVAxis;
					}
				}

			default:
				break;
		}
	}
}