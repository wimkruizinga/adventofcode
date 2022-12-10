public class Assignment {
    private final int firstSection;
    private final int lastSection;

    private Assignment(int firstSection, int lastSection) {
        this.firstSection = firstSection;
        this.lastSection = lastSection;
    }

    public static Assignment parseAssignment(String assignment) {
        String[] parts = assignment.split("-");
        return new Assignment(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]));
    }

    public int getFirstSection() {
        return firstSection;
    }

    public int getLastSection() {
        return lastSection;
    }
}
