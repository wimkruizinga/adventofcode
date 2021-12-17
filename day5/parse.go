package main

func getGridDimensions(lines []line) (int, int) {
	maxX, maxY := 0, 0
	for _, line := range lines {
		if line.start.col > maxX {
			maxX = line.start.col
		}
		if line.end.col > maxX {
			maxX = line.end.col
		}
		if line.start.row > maxY {
			maxY = line.start.row
		}
		if line.end.row > maxY {
			maxY = line.end.row
		}
	}

	return maxX, maxY
}
