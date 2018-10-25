package za.co.polymorph.chatference.callbacks;

import za.co.polymorph.chatference.model.Question;

public interface PostQuestionCallback {
    void success(Question question);
    void error(String errorDescription);
}
