public class SeamCarving {

//	public static void main(String[] args) {
//
//		int disruption[][] = { { 1, 2, 0, 3 }, { 1, 4, 4, 4 }, { 1, 2, 3, 4 }, { 3, 1, 1, 3 } };
//
//		carve_seam(disruption);
//	}

	static int[] carve_seam(int disruption[][]) {

		int[] result = new int[disruption.length];

		int minEnergyMatrix[][] = new int[disruption.length][disruption[0].length];

		// Create Minimum Energy Matrix
		for (int i = 0; i < disruption.length; i++) {
			for (int j = 0; j < disruption[i].length; j++) {

				// Copy first row as it is
				if (i == 0) {
					minEnergyMatrix[i][j] = disruption[i][j];
					continue;
				}

				// Left Edge
				if (j == 0) {
					minEnergyMatrix[i][j] = disruption[i][j]
							+ Math.min(minEnergyMatrix[i - 1][j], minEnergyMatrix[i - 1][j + 1]);
				}

				// Right Edge
				else if (j == disruption[0].length - 1) {
					minEnergyMatrix[i][j] = disruption[i][j]
							+ Math.min(minEnergyMatrix[i - 1][j], minEnergyMatrix[i - 1][j - 1]);
				}

				// Everything else apart from left and right edge
				else {
					int leftUpper = minEnergyMatrix[i - 1][j - 1];
					int upper = minEnergyMatrix[i - 1][j];
					int rightUpper = minEnergyMatrix[i - 1][j + 1];

					minEnergyMatrix[i][j] = disruption[i][j] + Math.min(Math.min(leftUpper, upper), rightUpper);
				}
			}
		}

		int i = minEnergyMatrix.length - 1;
		int j = 0;
		int columnIndex = -1;
		int minimum = Integer.MAX_VALUE;
		while (i >= 0) {

			if (i == minEnergyMatrix.length - 1) {
				while (j < minEnergyMatrix[0].length) {
					if (minEnergyMatrix[i][j] < minimum) {
						minimum = minEnergyMatrix[i][j];
						columnIndex = j;
					}
					j++;
				}
				result[i] = columnIndex;
			}

			else {
				int column = result[i + 1];

				// Left Edge
				if (column == 0) {
					if (minEnergyMatrix[i][column] <= minEnergyMatrix[i][column + 1]) {
						result[i] = column;
					} else {
						result[i] = column + 1;
					}
				}

				// Right Edge
				else if (column == minEnergyMatrix[0].length - 1) {
					if (minEnergyMatrix[i][column] < minEnergyMatrix[i][column - 1]) {
						result[i] = column;
					} else {
						result[i] = column - 1;
					}
				}

				// Remaining all cases
				else {
					result[i] = minEnergyMatrix[i][column - 1] < minEnergyMatrix[i][column]
							? ((minEnergyMatrix[i][column - 1] < minEnergyMatrix[i][column + 1]) ? column - 1
									: column + 1)
							: (minEnergyMatrix[i][column] < minEnergyMatrix[i][column + 1] ? column : column + 1);
				}

			}
			i--;
		}

		return result;
	}
}
