package main

type Move struct {
	direction string
	units     int
}

type Position struct {
	horizontal int
	depth      int
	aim        int
}

func (p *Position) addMove(m Move) {
	switch m.direction {
	case `forward`:
		p.horizontal += m.units
		p.depth += m.units * p.aim
	case `down`:
		p.aim += m.units
	case `up`:
		p.aim -= m.units
	default:
		panic(1)
	}
}
