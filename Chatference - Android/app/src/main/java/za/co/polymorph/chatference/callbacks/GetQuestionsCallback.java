package za.co.polymorph.chatference.callbacks;

import za.co.polymorph.chatference.model.Question;

public interface GetQuestionsCallback {
    void questionsUpdate(Question [] questions);
    void error(String message);
}
