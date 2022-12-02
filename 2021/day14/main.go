package main

import (
	"bufio"
	"fmt"
	"os"
	"strings"
)

func main() {
	file, err := os.Open(`testinput.txt`)
	if err != nil {
		panic(err)
	}
	defer file.Close()

	polymer, rules := processInput(file)
	for i := 0; i < 40; i++ {
		polymer = polymerize(polymer, rules)
		least, most := getQuantities(polymer)
		fmt.Println(i, `Least:`, least, `Most:`, most, `Result:`, most-least)
	}
}

func polymerize(input string, rules map[string]string) string {
	result := string(input[0])
	for i := 1; i < len(input); i++ {
		substr := input[i-1 : i+1]
		c := rules[substr]
		result = result + string(c) + string(input[i])
	}

	return result
}

func getQuantities(s string) (int64, int64) {
	freqs := make(map[string]int64)
	for _, c := range s {
		freqs[string(c)]++
	}

	var minFreq, maxFreq int64
	for _, value := range freqs {
		if value > maxFreq {
			maxFreq = value
		}
		if value < minFreq || minFreq == 0 {
			minFreq = value
		}
	}

	return minFreq, maxFreq
}

func processInput(file *os.File) (string, map[string]string) {
	scanner := bufio.NewScanner(file)
	scanner.Scan()
	template := scanner.Text()
	rules := make(map[string]string)
	for scanner.Scan() {
		line := scanner.Text()
		if line != `` {
			parts := strings.Split(line, ` -> `)
			rules[parts[0]] = parts[1]
		}
	}

	return template, rules
}
