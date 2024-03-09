package android.app.quizit;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView totalQuestionsTextView;
    TextView questionTextView;
    TextView scoreTextView;
    Button ansA, ansB, ansC, ansD;
    Button enterButton;

    int score = 0;
    int totalQuestions = QuizEntry.questions.length;
    int currentQuestion = 0;
    String selection = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        totalQuestionsTextView = findViewById(R.id.total_questions);
        questionTextView = findViewById(R.id.question);
        scoreTextView = findViewById(R.id.score);
        ansA = findViewById(R.id.ans_A);
        ansB = findViewById(R.id.ans_B);
        ansC = findViewById(R.id.ans_C);
        ansD = findViewById(R.id.ans_D);
        enterButton = findViewById(R.id.enter_button);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        enterButton.setOnClickListener(this);

        totalQuestionsTextView.setText("Total Questions: " + totalQuestions);
        scoreTextView.setText("Score: " + score);

        loadQuestion();
    }

    @Override
    public void onClick(View v) {
        resetBackground();

        Button clicked = (Button) v;
        if (clicked.getId() == R.id.enter_button) {
            if (selection.equals(QuizEntry.correctAnswers[currentQuestion])) {
                score++;
                scoreTextView.setText("Score: " + score);
            }
            currentQuestion++;
            loadQuestion();
        } else {
            selection = clicked.getText().toString();
            clicked.setBackgroundColor(getResources().getColor(android.R.color.holo_blue_light));
        }
    }

    void resetBackground() {
        ansA.setBackgroundColor(getResources().getColor(android.R.color.white));
        ansB.setBackgroundColor(getResources().getColor(android.R.color.white));
        ansC.setBackgroundColor(getResources().getColor(android.R.color.white));
        ansD.setBackgroundColor(getResources().getColor(android.R.color.white));
    }

    void loadQuestion() {
        questionTextView.setText(QuizEntry.questions[currentQuestion]);
        ansA.setText(QuizEntry.options[currentQuestion][0]);
        ansB.setText(QuizEntry.options[currentQuestion][1]);
        ansC.setText(QuizEntry.options[currentQuestion][2]);
        ansD.setText(QuizEntry.options[currentQuestion][3]);
    }
}