package io.github.team10.escapefromuni;

/**
 * NEW ENUM
 * NPCEventType enum that controls the title text, question text, left text, right text, and correct answer of the NPCEvent class.
 */
public enum NPCEventType {

    LONGBOI("Chat with HIM",
        "I am the long one\nI know very many truths\nWHAT IS IT YOU SEEK?",
        "I seek speed",
        "I seek victory",
        true);

    private final String title;
    private final String question_text;
    private final String left_text;
    private final String right_text;
    private final boolean correct_answer;

    NPCEventType(String title, String question_text, String left_text, String right_text, boolean correct_answer) {
        this.title = title;
        this.question_text =  question_text;
        this.left_text = left_text;
        this.right_text = right_text;
        this.correct_answer = correct_answer;
    }

    // getters and setters
    public String getTitle() { return title; }
    public String getQuestionText() { return question_text; }
    public String getLeftText() { return left_text; }
    public String getRightText() { return right_text; }

}
