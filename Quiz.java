import java.util.Scanner;

public class Quiz {
    private Question[] questions;
    private int score;

    public Quiz(Question[] questions) {
        this.questions = questions;
        this.score = 0;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);

        for (Question q : questions) {
            q.displayQuestion();
            System.out.print("Your answer (1-4): ");
            int ans = scanner.nextInt();
            if (q.isCorrect(ans)) {
                System.out.println("Correct!\n");
                score++;
            } else {
                System.out.println("Wrong!\n");
            }
        }

        System.out.println("Quiz Finished! Your Score: " + score + "/" + questions.length);
    }
}
