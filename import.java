import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean authenticate(String inputUser, String inputPass) {
        return username.equals(inputUser) && password.equals(inputPass);
    }
}

class Question {
    private String question;
    private String[] options;
    private int correctAnswer;

    public Question(String question, String[] options, int correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }

    public void displayQuestion() {
        System.out.println(question);
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }

    public boolean isCorrect(int userAnswer) {
        return userAnswer == correctAnswer;
    }
}

class Quiz {
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

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        User user = new User("admin", "1234");

        System.out.println("=== Online Quiz System ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (user.authenticate(username, password)) {
            System.out.println("\nLogin Successful!\n");

            Question[] questions = new Question[] {
                    new Question("Which language is used for Android development?",
                            new String[] { "Python", "Kotlin", "JavaScript", "PHP" }, 2),
                    new Question("What does JVM stand for?",
                            new String[] { "Java Virtual Machine", "Java Vendor Machine", "Just Virtual Machine",
                                    "None" },
                            1),
                    new Question("Which company developed Java?",
                            new String[] { "Sun Microsystems", "Microsoft", "Oracle", "Google" }, 1)
            };

            Quiz quiz = new Quiz(questions);
            quiz.start();
        } else {
            System.out.println("Invalid Credentials.");
        }
    }
}
