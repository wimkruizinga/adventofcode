package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
)

func main() {
	rows := processFile(`input.txt`)
	rowCount := len(rows)
	cols := len(rows[0])
	var gammaBin, epsilonBin string

COLUMNS:
	for i := 0; i < cols; i++ {
		var ones int

		for _, row := range rows {
			if row[i] == '1' {
				ones++
			}
			if float64(ones) > (0.5 * float64(rowCount)) {
				gammaBin += `1`
				epsilonBin += `0`
				continue COLUMNS
			}
		}
		gammaBin += `0`
		epsilonBin += `1`
	}

	gamma := binToDecimal(gammaBin)
	epsilon := binToDecimal(epsilonBin)

	fmt.Println(`Power consumption: `, gamma*epsilon)
}

func processFile(fileName string) []string {
	file, err := os.Open(fileName)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	var nums []string
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		nums = append(nums, scanner.Text())
	}

	return nums
}

func binToDecimal(b string) int64 {
	dec, err := strconv.ParseInt(b, 2, 64)
	if err != nil {
		panic(err)
	}

	return dec
}
