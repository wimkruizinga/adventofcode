public class ElfPair {
    private Assignment assignmentA;
    private Assignment assignmentB;

    private ElfPair() {}

    public static ElfPair fromLine(String line) {
        String[] assignments = line.split(",");
        ElfPair ep = new ElfPair();
        ep.assignmentA = Assignment.parseAssignment(assignments[0]);
        ep.assignmentB = Assignment.parseAssignment(assignments[1]);
        return ep;
    }

    public boolean assignmentsOverlap() {
        if (assignmentA.getFirstSection() < assignmentB.getFirstSection()) {
            return assignmentB.getLastSection() <= assignmentA.getLastSection();
        }

        if (assignmentB.getFirstSection() < assignmentA.getFirstSection()) {
            return assignmentA.getLastSection() <= assignmentB.getLastSection();
        }

        return (assignmentA.getFirstSection() == assignmentB.getFirstSection());
    }
}
