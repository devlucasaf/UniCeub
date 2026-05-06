package poo;

public class Assessment {
    private final String    name;
    private final double    maxScore;
    private Double          score;

    public Assessment(String name, double maxScore) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Nome da avaliação inválido");
        }
        
        if (maxScore <= 0) {
            throw new IllegalArgumentException("Nota máxima inválida");
        }
        
        this.name = name.trim();
        this.maxScore = maxScore;
        this.score = null;
    }

    public String getName() {
        return name;
    }

    public double getMaxScore() {
        return maxScore;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(double score) {
        if (score < 0 || score > maxScore) {
            throw new IllegalArgumentException("Nota fora do intervalo");
        }
        
        this.score = score;
    }

    public double normalizedScore() {
        if (score == null) {
            return 0.0;
        }
        
        return (score / maxScore) * 10.0;
    }

    @Override
    public String toString() {
        return "Assessment{" + "name='" + name + '\'' + ", maxScore=" + maxScore + ", score=" + score + '}';
    }
}